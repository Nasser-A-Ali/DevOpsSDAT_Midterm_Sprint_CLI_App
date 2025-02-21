package com.keyin.cli;

import com.keyin.client.RESTClient;
import com.keyin.models.Album;
import com.keyin.models.Artist;
import com.keyin.models.Song;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MainTest {

    @Mock
    private RESTClient mockRestClient;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;

    private Main app;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    void testViewAllSongs() {
        // Prepare test data
        Artist artist = new Artist();
        artist.setName("Test Artist");

        Song song1 = new Song(1L, "Test Song 1", artist, "Rock", 3.45, "2024-01-01");
        Song song2 = new Song(2L, "Test Song 2", artist, "Pop", 4.12, "2024-01-02");
        List<Song> mockSongs = Arrays.asList(song1, song2);

        // Mock REST client behavior
        when(mockRestClient.getAllSongs()).thenReturn(mockSongs);

        // Simulate user input
        provideInput("1\n7\n");
        app = new Main(new Scanner(System.in), mockRestClient);

        // Run the application
        app.run();

        // Verify the output
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Test Artist - Test Song 1"));
        assertTrue(output.contains("Test Artist - Test Song 2"));

        // Verify the REST client method was called
        verify(mockRestClient, times(1)).getAllSongs();
    }

    @Test
    void testViewAllSongsEmpty() {
        // Mock empty song list
        when(mockRestClient.getAllSongs()).thenReturn(Collections.emptyList());

        // Simulate user input
        provideInput("1\n7\n");
        app = new Main(new Scanner(System.in), mockRestClient);

        // Run the application
        app.run();

        // Verify the output
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("No songs available."));

        // Verify the REST client method was called
        verify(mockRestClient, times(1)).getAllSongs();
    }

    @Test
    void testSearchForArtist() {
        // Prepare test data
        Artist mockArtist = new Artist();
        mockArtist.setId(1L);
        mockArtist.setName("Test Artist");

        // Mock REST client behavior
        when(mockRestClient.getArtistByName("Test Artist")).thenReturn(mockArtist);

        // Simulate user input
        provideInput("2\nTest Artist\n7\n");
        app = new Main(new Scanner(System.in), mockRestClient);

        // Run the application
        app.run();

        // Verify the output
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Artist Found: Test Artist"));

        // Verify the REST client method was called
        verify(mockRestClient, times(1)).getArtistByName("Test Artist");
    }

    @Test
    void testSearchForArtistNotFound() {
        // Mock artist not found
        when(mockRestClient.getArtistByName("Nonexistent Artist")).thenReturn(null);

        // Simulate user input
        provideInput("2\nNonexistent Artist\n7\n");
        app = new Main(new Scanner(System.in), mockRestClient);

        // Run the application
        app.run();

        // Verify the output
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Could not find artist. Please try another!"));

        // Verify the REST client method was called
        verify(mockRestClient, times(1)).getArtistByName("Nonexistent Artist");
    }

    @Test
    void testViewAlbumsByArtist() {
        // Prepare test data
        Artist artist = new Artist();
        artist.setId(1L);
        artist.setName("Test Artist");

        Album album1 = new Album();
        album1.setTitle("Test Album 1");
        album1.setReleaseYear(2024);
        album1.setArtist(artist);

        Album album2 = new Album();
        album2.setTitle("Test Album 2");
        album2.setReleaseYear(2023);
        album2.setArtist(artist);

        List<Album> mockAlbums = Arrays.asList(album1, album2);

        // Mock REST client behavior
        when(mockRestClient.getAlbumsByArtistId(1L)).thenReturn(mockAlbums);

        // Simulate user input
        provideInput("3\n1\n7\n");
        app = new Main(new Scanner(System.in), mockRestClient);

        // Run the application
        app.run();

        // Verify the output
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Test Album 1, 2024"));
        assertTrue(output.contains("Test Album 2, 2023"));

        // Verify the REST client method was called
        verify(mockRestClient, times(1)).getAlbumsByArtistId(1L);
    }

    @Test
    void testAddNewSong() {
        // Prepare test data
        Artist artist = new Artist();
        artist.setId(1L);

        // Mock REST client behavior
        doNothing().when(mockRestClient).addSong(any(Song.class));

        // Simulate user input
        provideInput("4\nNew Test Song\n1\nRock\n3.45\n2024-02-21\n7\n");
        app = new Main(new Scanner(System.in), mockRestClient);

        // Run the application
        app.run();

        // Verify the output
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("New song added: New Test Song"));

        // Verify the REST client method was called with correct parameters
        verify(mockRestClient, times(1)).addSong(argThat(song ->
                song.getTitle().equals("New Test Song") &&
                        song.getArtist().getId() == 1L &&
                        song.getGenre().equals("Rock") &&
                        song.getDuration() == 3.45 &&
                        song.getReleaseDate().equals("2024-02-21")
        ));
    }

    @Test
    void testEditSongDetails() {
        // Prepare test data
        Artist artist = new Artist();
        artist.setId(1L);
        artist.setName("Test Artist");

        Song existingSong = new Song(1L, "Original Song", artist, "Rock", 3.45, "2024-01-01");

        // Mock REST client behavior
        when(mockRestClient.getSongById(1L)).thenReturn(existingSong);
        doNothing().when(mockRestClient).updateSong(eq(1L), any(Song.class));

        // Simulate user input
        provideInput("5\n1\nUpdated Song\nPop\n4.0\n2024-02-21\n7\n");
        app = new Main(new Scanner(System.in), mockRestClient);

        // Run the application
        app.run();

        // Verify the output
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Song details updated successfully!"));

        // Verify the REST client methods were called
        verify(mockRestClient, times(1)).getSongById(1L);
        verify(mockRestClient, times(1)).updateSong(eq(1L), argThat(song ->
                song.getTitle().equals("Updated Song") &&
                        song.getGenre().equals("Pop") &&
                        song.getDuration() == 4.0 &&
                        song.getReleaseDate().equals("2024-02-21")
        ));
    }

    @Test
    void testEditSongDetailsNotFound() {
        // Mock song not found
        when(mockRestClient.getSongById(999L)).thenReturn(null);

        // Simulate user input
        provideInput("5\n999\n7\n");
        app = new Main(new Scanner(System.in), mockRestClient);

        // Run the application
        app.run();

        // Verify the output
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("No song found with ID: 999"));

        // Verify the REST client method was called
        verify(mockRestClient, times(1)).getSongById(999L);
        verify(mockRestClient, never()).updateSong(anyLong(), any(Song.class));
    }

    @Test
    void testViewAlbumTracks() {
        // Prepare test data
        Artist artist = new Artist();
        artist.setId(1L);
        artist.setName("Test Artist");

        Song song1 = new Song(1L, "Track 1", artist, "Rock", 3.45, "2024-01-01");
        Song song2 = new Song(2L, "Track 2", artist, "Rock", 3.55, "2024-01-01");

        Album album = new Album();
        album.setTitle("Test Album");
        album.setReleaseYear(2024);
        album.setArtist(artist);
        album.setListOfSongs(Arrays.asList(song1, song2));

        List<Album> mockAlbums = Arrays.asList(album);

        // Mock REST client behavior
        when(mockRestClient.getAllAlbums()).thenReturn(mockAlbums);

        // Simulate user input
        provideInput("6\nTest Album\n7\n");
        app = new Main(new Scanner(System.in), mockRestClient);

        // Run the application
        app.run();

        // Verify the output
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Tracks in Test Album:"));
        assertTrue(output.contains("Track 1"));
        assertTrue(output.contains("Track 2"));

        // Verify the REST client method was called
        verify(mockRestClient, times(1)).getAllAlbums();
    }

    @Test
    void testInvalidChoice() {
        // Simulate user input
        provideInput("8\n7\n");
        app = new Main(new Scanner(System.in), mockRestClient);

        // Run the application
        app.run();

        // Verify the output
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Invalid choice. Please try again."));
    }
}
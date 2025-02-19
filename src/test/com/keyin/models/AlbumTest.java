package com.keyin.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

public class AlbumTest {

    private Album album;

    @Mock
    private Artist mockedArtist;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Mockito.when(mockedArtist.getName()).thenReturn("Billie Eilish");

        List<Song> tracks = new ArrayList<>();
        tracks.add(new Song(1, "Getting Older", mockedArtist, "Pop", 240.0, "2021"));
        tracks.add(new Song(2, "Lost Cause", mockedArtist, "Pop", 180.0, "2021"));

        album = new Album(1, "Happier Than Ever", mockedArtist, 2021, tracks.size(), "Alternative", tracks);
    }

    @Test
    public void testAlbumConstructor() {
        // Assertions for Album object creation
        assertNotNull(album);
        assertEquals("Happier Than Ever", album.getTitle());
        assertEquals("Billie Eilish", album.getArtist().getName());
        assertEquals(2021, album.getReleaseYear());
        assertEquals(2, album.getNumberOfSongs());
        assertEquals("Alternative", album.getGenre());
    }

    @Test
    public void testSettersAndGetters() {
        Artist artist2 = new Artist(2, "Vance Joy", 2014, "Alternative", "Australia");

        album.setArtist(artist2);
        album.setTitle("Nation of Two");
        album.setReleaseYear(2018);
        album.setNumberOfSongs(13);
        album.setGenre("Alternative");

        assertEquals("Vance Joy", album.getArtist().getName());
        assertEquals("Nation of Two", album.getTitle());
        assertEquals(2018, album.getReleaseYear());
        assertEquals(13, album.getNumberOfSongs());
        assertEquals("Alternative", album.getGenre());
    }
}

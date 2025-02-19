package com.keyin.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class SongTest {

    // Mock the Artist dependency
    @Mock
    private Artist mockedArtist;

    // Initialize the mocks before each test
    @BeforeEach
    public void setup() {
        // Initialize the mocks for the test class
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSongConstructor() {
        long expectedId = 1;
        String expectedTitle = "Same Song and Dance";
        Mockito.when(mockedArtist.getId()).thenReturn(101L);
        String expectedGenre = "Rap";
        double expectedDuration = 4.05;
        String expectedReleaseYear = "2009";

        // Pass the mocked Artist to the Song constructor
        Song song = new Song(expectedId, expectedTitle, mockedArtist, expectedGenre, expectedDuration, expectedReleaseYear);

        // Assertions for the Song object
        assertEquals(expectedId, song.getId());
        assertEquals(expectedTitle, song.getTitle());
        assertEquals(mockedArtist, song.getArtist());
        assertEquals(expectedGenre, song.getGenre());
        assertEquals(expectedDuration, song.getDuration());
    }

    @Test
    void testSongConstructorExtraData() {
        long expectedId = 2;
        String expectedTitle = "Vultures";
        // Mock the Artist object
        Mockito.when(mockedArtist.getId()).thenReturn(102L);
        double expectedDuration = 4.09;
        String expectedGenre = "Rap";
        String expectedReleaseYear = "2005";

        // Pass the mocked Artist to the Song constructor
        Song song = new Song(expectedId, expectedTitle, mockedArtist, expectedGenre, expectedDuration, expectedReleaseYear);

        // Assertions for the Song object
        assertEquals(expectedId, song.getId());
        assertEquals(expectedTitle, song.getTitle());
        assertEquals(mockedArtist, song.getArtist()); // Check if the artist is correctly set
        assertEquals(expectedGenre, song.getGenre());
        assertEquals(expectedDuration, song.getDuration());
    }
}

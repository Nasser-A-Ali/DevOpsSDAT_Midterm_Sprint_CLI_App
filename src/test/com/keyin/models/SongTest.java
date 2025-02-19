package com.keyin.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class SongTest {

    @Mock
    private Artist mockedArtist;

    @BeforeEach
    public void setup() {
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

        Song song = new Song(expectedId, expectedTitle, mockedArtist, expectedGenre, expectedDuration, expectedReleaseYear);

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
        Mockito.when(mockedArtist.getId()).thenReturn(102L);
        double expectedDuration = 4.09;
        String expectedGenre = "Rap";
        String expectedReleaseYear = "2005";

        Song song = new Song(expectedId, expectedTitle, mockedArtist, expectedGenre, expectedDuration, expectedReleaseYear);

        assertEquals(expectedId, song.getId());
        assertEquals(expectedTitle, song.getTitle());
        assertEquals(mockedArtist, song.getArtist());
        assertEquals(expectedGenre, song.getGenre());
        assertEquals(expectedDuration, song.getDuration());
    }
}

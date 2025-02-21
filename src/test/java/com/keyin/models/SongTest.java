//package com.keyin.models;
//
//
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class SongTest {
//
//    @Test
//    void testSongConstructor() {
//        int expectedId = 1;
//        String expectedTitle = "Same Song and Dance";
//        int expectedArtistId = 101;
//        String expectedGenre = "Rap";
//        double expectedDuration = 4.05;
//        int expectedReleaseYear = 2009;
//
//        Song song = new Song(expectedId,expectedTitle, expectedArtistId, expectedGenre,expectedDuration, expectedReleaseYear);
//
//        assertEquals(expectedId, song.getId());
//        assertEquals(expectedTitle, song.getTitle());
//        assertEquals(expectedArtistId, song.getArtistId());
//        assertEquals(expectedGenre, song.getGenre());
//        assertEquals(expectedDuration, song.getDuration());
//    }
//
//    @Test
//    void testSongConstructorExtraData() {
//        int expectedId = 2;
//        String expectedTitle = "Vultures";
//        int expectedArtistId = 102;
//        String expectedGenre = "Rock-Blues";
//        double expectedDuration = 4.09;
//        int expectedReleaseYear = 2005;
//
//        Song song = new Song(expectedId,expectedTitle, expectedArtistId, expectedGenre,expectedDuration, expectedReleaseYear);
//
//        assertEquals(expectedId, song.getId());
//        assertEquals(expectedTitle, song.getTitle());
//        assertEquals(expectedArtistId, song.getArtistId());
//        assertEquals(expectedGenre, song.getGenre());
//        assertEquals(expectedDuration, song.getDuration());
//    }
//
//}

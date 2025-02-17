package com.keyin.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

import com.keyin.models.Album;
import com.keyin.models.Artist;
import com.keyin.models.Song;  // Import Song if needed

public class AlbumTest {

    private Album album;
    private Artist artist;

    @BeforeEach  // Use @BeforeEach to initialize before each test
    public void setUp() {

        List<Song> tracks = new ArrayList<>();  // Tracks should be List<Song>

        // Fix Artist instantiation (int ID first)
        artist = new Artist(1, "Billie Eilish", 2015, "Pop", "USA", null);

        // Fix Songs (pass artist ID instead of object, fix releaseYear)
        tracks.add(new Song(1, "Getting Older", 1, "Pop", 240.0, 2021));
        tracks.add(new Song(2, "Lost Cause", 1, "Pop", 180.0, 2021));

        // Fix Album instantiation (int ID first)
        album = new Album(1, "Happier Than Ever", artist, 2021, 16, tracks, "Alternative");
    }



    @Test
    public void testAlbumConstructor() {
        assertNotNull(album);
        assertEquals("Happier Than Ever", album.getTitle());
        assertEquals("Billie Eilish", album.getArtist().getName());
        assertEquals(2021, album.getReleaseYear());
        assertEquals(16, album.getNumberOfSongs());
        assertEquals("Alternative", album.getGenre());
    }

    @Test
    public void testSettersAndGetters() {
        Artist artist2 = new Artist(2, "Vance Joy", 2014, "Alternative", "Australia", null);

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

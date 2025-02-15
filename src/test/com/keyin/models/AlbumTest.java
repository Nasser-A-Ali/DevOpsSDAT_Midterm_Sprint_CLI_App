package test.com.keyin.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.keyin.models.Album;
import com.keyin.models.Artist;

public class AlbumTest {

    private Album album;
    private Artist artist;

    public void setUp() {
        artist = new Artist("Billie Eilish", 2015, "pop", "USA");
        album = new Album("Happier Than Ever", artist, 2021, 16, "Alternative");
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
        Artist artist2 = new Artist("Vance Joy", 2014, "Alternative", "Australia");

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

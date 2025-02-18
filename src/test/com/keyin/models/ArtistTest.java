package com.keyin.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

public class ArtistTest {

    @Test
    void testArtistConstructor() {
        int expectedId = 1;
        String expectedName = "Queen";
        long expectedDebutYear = 1970;
        String expectedGenre = "Rock";
        String expectedCountry = "England";

        // Create a dummy Album object
        Album expectedAlbum = new Album(1, "Greatest Hits", null, 1981, 10, new ArrayList<Song>(), "Rock");

        // Now pass the expectedAlbum to Artist
        List<Song> expectedListOfSongs = List.of();
        Artist artist = new Artist(expectedId, expectedName, (int) expectedDebutYear, expectedGenre, expectedCountry, expectedAlbum, expectedListOfSongs );

        // Assertions
        Assert.assertEquals(expectedName, artist.getName());
        Assert.assertEquals(expectedDebutYear, artist.getDebutYear());
        Assert.assertEquals(expectedGenre, artist.getGenre());
        Assert.assertEquals(expectedCountry, artist.getCountry());
        Assert.assertEquals(expectedListOfSongs, artist.getListOfSongs());
    }

}

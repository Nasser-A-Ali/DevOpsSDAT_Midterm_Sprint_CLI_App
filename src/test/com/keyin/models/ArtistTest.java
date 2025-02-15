package com.keyin.models;

import org.testng.annotations.Test;
import org.testng.Assert;

public class ArtistTest {

    @Test
    void testArtistConstructor() {
        String expectedName = "Queen";
        int expectedDebutYear = 1970;
        String expectedGenre = "Rock";
        String expectedCountry = "England";

        Artist artist = new Artist(expectedName, expectedDebutYear, expectedGenre,expectedCountry);

        Assert.assertEquals(expectedName, artist.getName());
        Assert.assertEquals(expectedDebutYear, artist.getDebutYear());
        Assert.assertEquals(expectedGenre, artist.getGenre());
        Assert.assertEquals(expectedCountry, artist.getCountry());
    }
}

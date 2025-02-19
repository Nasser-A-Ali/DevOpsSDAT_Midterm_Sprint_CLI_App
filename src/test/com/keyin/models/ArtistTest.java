package com.keyin.models;

import org.mockito.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.ArrayList;

public class ArtistTest {

    @Mock
    private Album mockedAlbum;

    private Artist artist;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);

        Mockito.when(mockedAlbum.getTitle()).thenReturn("Greatest Hits");

        artist = new Artist(1, "Queen", 1970, "Rock", "England");
    }

    @Test
    void testArtistConstructor() {
        int expectedId = 1;
        String expectedName = "Queen";
        long expectedDebutYear = 1970;
        String expectedGenre = "Rock";
        String expectedCountry = "England";

        Assert.assertEquals(artist.getName(), expectedName);
        Assert.assertEquals(artist.getDebutYear(), expectedDebutYear);
        Assert.assertEquals(artist.getGenre(), expectedGenre);
        Assert.assertEquals(artist.getCountry(), expectedCountry);

        Assert.assertEquals(artist.getName(), "Queen");
    }
}

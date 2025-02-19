package com.keyin.models;

import org.mockito.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.ArrayList;

public class ArtistTest {

    // Mock the Album dependency
    @Mock
    private Album mockedAlbum;

    // Create an Artist instance
    private Artist artist;

    // Initialize the mocks before each test
    @BeforeMethod
    public void setup() {
        // Initialize mocks for the test class
        MockitoAnnotations.initMocks(this);

        // Prepare mock behavior for Album (if needed, like when we need to stub methods)
        Mockito.when(mockedAlbum.getTitle()).thenReturn("Greatest Hits");

        // Now create the Artist instance with the mocked Album
        artist = new Artist(1, "Queen", 1970, "Rock", "England");
    }

    @Test
    void testArtistConstructor() {
        int expectedId = 1;
        String expectedName = "Queen";
        long expectedDebutYear = 1970;
        String expectedGenre = "Rock";
        String expectedCountry = "England";

        // Assertions for the Artist object
        Assert.assertEquals(artist.getName(), expectedName);
        Assert.assertEquals(artist.getDebutYear(), expectedDebutYear);
        Assert.assertEquals(artist.getGenre(), expectedGenre);
        Assert.assertEquals(artist.getCountry(), expectedCountry);

        // Additional mock checks, if needed
        Assert.assertEquals(artist.getName(), "Queen");
    }
}

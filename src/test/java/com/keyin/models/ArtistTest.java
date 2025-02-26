package com.keyin.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

public class ArtistTest {

    @Mock
    private ObjectMapper objectMapper;

    private Artist artist;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        artist = new Artist(1, "Queen", 1970, "Rock", "England");
    }

    @Test
    void testArtistConstructor() {
        assertEquals(1, artist.getId());
        assertEquals("Queen", artist.getName());
        assertEquals(1970, artist.getDebutYear());
        assertEquals("Rock", artist.getGenre());
        assertEquals("England", artist.getCountry());
    }

    @Test
    void testArtistSerialization() throws IOException {
        String json = "{\"id\":1,\"name\":\"Queen\",\"debutYear\":1970,\"genre\":\"Rock\",\"country\":\"England\"}";

        when(objectMapper.readValue(json, Artist.class)).thenReturn(artist);

        Artist deserializedArtist = objectMapper.readValue(json, Artist.class);

        assertEquals(artist.getId(), deserializedArtist.getId());
        assertEquals(artist.getName(), deserializedArtist.getName());
        assertEquals(artist.getDebutYear(), deserializedArtist.getDebutYear());
        assertEquals(artist.getGenre(), deserializedArtist.getGenre());
        assertEquals(artist.getCountry(), deserializedArtist.getCountry());
    }
}
package com.keyin.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.models.Album;
import com.keyin.models.Artist;
import com.keyin.models.Song;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class RESTClient {
    private String serverURL;
    private HttpClient client;

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public HttpClient getClient() {
        if (client == null) {
            client = HttpClient.newHttpClient();
        }
        return client;
    }

    public String getResponseFromHTTPRequest() {
        String responseBody = "";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(serverURL)).build();

        try {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Status Code: " + response.statusCode());
            }
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return responseBody;
    }

    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(serverURL)).build();

        try {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                albums = buildAlbumListFromResponse(response.body());
            } else {
                System.out.println("Error Status Code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return albums;
    }

    public List<Artist> getAllArtists() {
        List<Artist> artists = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(serverURL)).build();

        try {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                artists = buildArtistListFromResponse(response.body());
            } else {
                System.out.println("Error Status Code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return artists;
    }

    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serverURL + "/songs")) // Directly hitting the correct endpoint
                .build();

        try {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                songs = buildSongListFromResponse(response.body());
            } else {
                System.out.println("Error Status Code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return songs;
    }

    public Song getSongById(long id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serverURL + "/songs/" + id))
                .build();

        try {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

                return mapper.readValue(response.body(), Song.class);
            } else {
                System.out.println("Failed to fetch song. Error: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }


    public Artist getArtistById(long id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serverURL + "/artists/" + id))
                .build();

        try {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

                return mapper.readValue(response.body(), Artist.class);
            } else {
                System.out.println("Failed to fetch artist. Error: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }



    public List<Album> buildAlbumListFromResponse(String response) throws JsonProcessingException {
        List<Album> albums = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        List<Album> albumList = mapper.readValue(response, new TypeReference<List<Album>>() {});

        for (Album album : albumList) {
            System.out.println("Album ID: " + album.getId());
            albums.add(album);
        }

        return albums;
    }

    public List<Artist> buildArtistListFromResponse(String response) throws JsonProcessingException {
        List<Artist> artists = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        List<Artist> artistList = mapper.readValue(response, new TypeReference<List<Artist>>() {});

        for (Artist artist : artistList) {
            System.out.println("Artist ID: " + artist.getId());
            artists.add(artist);
        }

        return artists;
    }

    public List<Song> buildSongListFromResponse(String response) throws JsonProcessingException {
        List<Song> songs = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        JsonNode rootNode = mapper.readTree(response);

        if (rootNode.isArray()) {
            return mapper.readValue(response, new TypeReference<List<Song>>() {});
        }

        JsonNode embeddedNode = rootNode.get("_embedded");

        if (embeddedNode != null && embeddedNode.has("songs")) {
            JsonNode songsNode = embeddedNode.get("songs");
            return mapper.readValue(songsNode.toString(), new TypeReference<List<Song>>() {});
        }

        System.out.println("Warning: No songs found in API response.");
        return songs;
    }





    public void addSong(Song song) {
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = "";

        try {
            requestBody = mapper.writeValueAsString(song);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serverURL + "/song"))  
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 201) {
                System.out.println("Song successfully added to API!");
            } else {
                System.out.println("Error: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateSong(long id, Song song) {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = "";

        try {
            requestBody = objectMapper.writeValueAsString(song);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serverURL + "/songs/" + id))  // Ensure this URL is correct
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("Song successfully updated!");
            } else {
//                System.out.println("Failed to update song. Error: " + response.statusCode());
//                System.out.println("Response Body: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void deleteSong(long id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serverURL + "/songs/" + id))
                .DELETE()
                .build();

        try {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 204) {
                System.out.println("Song deleted successfully.");
            } else {
                System.out.println("Failed to delete song. Error: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.keyin.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Song {
    @JsonProperty("id")
    private long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("artist") // Now an Artist object
    private Artist artist;

    @JsonProperty("genre")
    private String genre;

    @JsonProperty("duration")
    private double duration;

    @JsonProperty("releaseDate") // String instead of int releaseYear
    private String releaseDate;

    // Default constructor
    public Song() {}

    // Parameterized constructor
    public Song(long id, String title, Artist artist, String genre, double duration, String releaseDate) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
        this.releaseDate = releaseDate;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public double getDuration() {
        return duration;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    // toString() method
    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist=" + (artist != null ? artist.getName() : "Unknown Artist") +
                ", genre='" + genre + '\'' +
                ", duration=" + duration +
                ", releaseDate=" + releaseDate +
                '}';
    }
}

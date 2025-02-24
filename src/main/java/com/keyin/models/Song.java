package com.keyin.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Song {
    @JsonProperty("id")
    private long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("artist")
    private Artist artist;

    @JsonProperty("genre")
    private String genre;

    @JsonProperty("duration")
    private double duration;

    @JsonProperty("releaseYear")
    private int releaseYear;

    public Song() {
    }

    public Song(long id, String title, Artist artist, String genre, double duration, int releaseYear) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
        this.releaseYear = releaseYear;
    }

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

    public int getReleaseYear() {
        return releaseYear;
    }

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

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist=" + (artist != null ? artist.getName() : "Unknown Artist") +
                ", genre='" + genre + '\'' +
                ", duration=" + duration +
                ", releaseYear=" + releaseYear +
                '}';
    }
}

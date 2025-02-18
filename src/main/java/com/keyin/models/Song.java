package com.keyin.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Song {
    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("artistId")
    private int artistId;

    @JsonProperty("genre")
    private String genre;

    @JsonProperty("duration")
    private double duration;

    @JsonProperty("releaseYear")
    private int releaseYear;

    public Song() {
    }

    public Song(int id, String title, int artistId, String genre, double duration) {
        this(id, title, artistId, genre, duration, -1);
    }

    public Song(int id, String title, int artistId, String genre, double duration, int releaseYear) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
        this.genre = genre;
        this.duration = duration;
        this.releaseYear = releaseYear;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getArtistId() {
        return artistId;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
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
                ", artistId=" + artistId +
                ", genre='" + genre + '\'' +
                ", duration=" + duration +
                ", releaseYear=" + releaseYear +
                '}';
    }
}

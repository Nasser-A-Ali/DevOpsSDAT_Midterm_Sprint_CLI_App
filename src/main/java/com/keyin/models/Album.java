package com.keyin.models;

import java.util.List;

public class Album {
    private String title;
    private Artist artist;
    private long releaseYear;
    private int numberOfSongs;
    private List<String> tracks;
    private String genre;

    public Album(String title, Artist artist, long releaseYear, int numberOfSongs, List<String> tracks, String genre) {
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.numberOfSongs = numberOfSongs;
        this.tracks = tracks;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public long getReleaseYear() {
        return releaseYear;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public List<String> getTracks() {
        return tracks;
    }

    public String getGenre() {
        return genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setReleaseYear(long releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Album{" +
                "Title= " + getTitle() +
                ", Artist= " + getArtist() +
                ", Release Year= " + getReleaseYear() +
                ", Number of Songs= " + getNumberOfSongs() +
                ", Tracks= " + getTracks() +
                ", Genre= " + getGenre() +
                "}";
    }
}

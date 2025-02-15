package com.keyin.models;

public class Album {
    private String artist;
    private long releaseYear;
    private int numberOfSongs;
    private String genre;

    public Album(String artist, long releaseYear, int numberOfSongs, String genre) {
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.numberOfSongs = numberOfSongs;
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public long getReleaseYear() {
        return releaseYear;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public String getGenre() {
        return genre;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setReleaseYear(long releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Album{" +
                "Artist= " + getArtist() +
                ", Release Year= " + getReleaseYear() +
                ", Number of Songs= " + getNumberOfSongs() +
                ", Genre= " + getGenre() +
                "}";
    }
}

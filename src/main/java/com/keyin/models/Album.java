package com.keyin.models;

import java.util.List;

public class Album {
    private int id;
    private String title;
    private Artist artist;
    private long releaseYear;
    private int numberOfSongs;
    private List<Song> listOfSongs;
    private String genre;

    public Album(int id, String title, Artist artist, long releaseYear, int numberOfSongs, List<Song> listOfSongs,
            String genre) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.numberOfSongs = numberOfSongs;
        this.listOfSongs = listOfSongs;
        this.genre = genre;
    }

    public int getId() {
        return id;
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

    public List<Song> getListOfSongs() {
        return listOfSongs;
    }

    public String getGenre() {
        return genre;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setTracks(List<Song> listOfSongs) {
        this.listOfSongs = listOfSongs;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Album{" +
                "Id= " + getId() +
                ", Title= " + getTitle() +
                ", Artist= " + getArtist() +
                ", Release Year= " + getReleaseYear() +
                ", Number of Songs= " + getNumberOfSongs() +
                ", Tracks= " + getListOfSongs() +
                ", Genre= " + getGenre() +
                "}";
    }
}

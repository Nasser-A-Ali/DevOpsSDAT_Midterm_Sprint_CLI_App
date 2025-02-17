package com.keyin.models;

public class Artist {
    private int id;
    private String name;
    private long debutYear;
    private String genre;
    private String country;
    private Album album;

    public Artist (int id, String name, long debutYear, String genre, String country, Album album) {
        this.id = id;
        this.name = name;
        this.debutYear = debutYear;
        this.genre = genre;
        this.country = country;
        this.album = album;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Add ID, and Albums
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDebutYear() {
        return debutYear;
    }

    public void setDebutYear(long debutYear) {
        this.debutYear = debutYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", debutYear=" + debutYear +
                ", genre='" + genre + '\'' +
                ", country='" + country + '\'' +
                ", album=" + album +
                '}';
    }
}
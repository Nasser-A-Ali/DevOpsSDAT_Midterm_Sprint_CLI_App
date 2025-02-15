package com.keyin.models;

public class Artist {
    private String name;
    private long debutYear;
    private String genre;
    private String country;

    public Artist (String name, long debutYear, String genre, String country) {
        this.name = name;
        this.debutYear = debutYear;
        this.genre = genre;
        this.country = country;
    }

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
                "name='" + name + '\'' +
                ", debutYear=" + debutYear +
                ", genre='" + genre + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
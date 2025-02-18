package com.keyin.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Artist {
    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("debutYear")
    private int debutYear;

    @JsonProperty("genre")
    private String genre;

    @JsonProperty("country")
    private String country;

    // ✅ Add a no-args constructor for Jackson
    public Artist() {
    }

    public Artist(long id, String name, int debutYear, String genre, String country) {
        this.id = id;
        this.name = name;
        this.debutYear = debutYear;
        this.genre = genre;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDebutYear() {
        return debutYear;
    }

    public String getGenre() {
        return genre;
    }

    public String getCountry() {
        return country;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDebutYear(int debutYear) {
        this.debutYear = debutYear;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

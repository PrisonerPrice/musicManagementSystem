package com.prisonerprice.model;

import java.util.UUID;

public class Album {
    private int id;
    private String name;
    private int releaseYear;
    private String artist;
    private String genre;
    private String description;
    private String serialNumber;

    public Album(int id, String name, int releaseYear, String artist, String genre, String description, String serialNumber) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.artist = artist;
        this.genre = genre;
        this.description = description;
        this.serialNumber = serialNumber;
    }

    public Album(String name, int release_year, String artist, String genre, String description) {
        this.name = name;
        this.releaseYear = release_year;
        this.artist = artist;
        this.genre = genre;
        this.description = description;
        this.serialNumber = UUID.randomUUID().toString();
    }

    public Album(){
        this.id = 0;
        this.name = "NULL";
        this.releaseYear = 0;
        this.artist = "NULL";
        this.genre = "NULL";
        this.description = "NULL";
        this.serialNumber = UUID.randomUUID().toString();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseYear(int release_year) {
        this.releaseYear = release_year;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSerialNumber(String serial_num) {
        this.serialNumber = serial_num;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", release_year=" + releaseYear +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", serial_num=" + serialNumber +
                '}';
    }
}

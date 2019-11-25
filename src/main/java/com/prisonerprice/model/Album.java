package com.prisonerprice.model;

public class Album {
    private int id;
    private String name;
    private int release_year;
    private String artist;
    private String genre;
    private String description;
    private int serial_num;

    public Album(String name, int release_year, String artist, String genre, String description, int serial_num) {
        this.id = 0;
        this.name = name;
        this.release_year = release_year;
        this.artist = artist;
        this.genre = genre;
        this.description = description;
        this.serial_num = serial_num;
    }

    public Album(int id, String name, int release_year, String artist, String genre, String description, int serial_num){
        this.id = 0;
        this.name = "NULL";
        this.release_year = 0;
        this.artist = "NULL";
        this.genre = "NULL";
        this.description = "NULL";
        this.serial_num = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRelease_year() {
        return release_year;
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

    public int getSerial_num() {
        return serial_num;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
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

    public void setSerial_num(int serial_num) {
        this.serial_num = serial_num;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", release_year=" + release_year +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

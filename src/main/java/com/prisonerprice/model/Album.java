package com.prisonerprice.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "album")
public class Album {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column(name = "release_year")
    private int releaseYear;

    @Column
    private String artist;

    @Column
    private String genre;

    @Column
    private String description;

    @Id
    @Column(name = "serial_num")
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

    public Album(Album album){
        this.id = album.getId();
        this.name = album.getName();
        this.releaseYear = album.getReleaseYear();
        this.artist = album.getArtist();
        this.genre = album.getGenre();
        this.description = album.getDescription();
        this.serialNumber = album.getSerialNumber();
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

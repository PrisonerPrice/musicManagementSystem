package com.prisonerprice.model;

import org.hibernate.query.criteria.internal.expression.NullLiteralExpression;

import javax.persistence.*;
import java.util.Comparator;
import java.util.UUID;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "release_year")
    private int releaseYear;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id")
    //select * from album al left join artist art on al.artist_serial_num = art.serial_num;
    private Artist artist;

    @Column
    private String genre;

    @Column(name = "description")
    private String description;

    @OneToOne(mappedBy = "album", cascade = CascadeType.ALL)
    private Stock stock;


    public Album(int id, String name, int releaseYear, Artist artist, String genre, String description) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.artist = artist;
        this.genre = genre;
        this.description = description;
    }

    public Album(String name, int release_year, Artist artist, String genre, String description) {
        this.name = name;
        this.releaseYear = release_year;
        this.artist = artist;
        this.genre = genre;
        this.description = description;
        //this.serialNumber = UUID.randomUUID().toString();
    }

    public Album(){
        this.id = 0;
        this.name = "NULL";
        this.releaseYear = 0;
        this.artist = null;
        this.genre = "NULL";
        this.description = "NULL";
        //this.serialNumber = UUID.randomUUID().toString();
    }

    public Album(Album album){
        this.id = album.getId();
        this.name = album.getName();
        this.releaseYear = album.getReleaseYear();
        this.artist = album.getArtist();
        this.genre = album.getGenre();
        this.description = album.getDescription();
        //this.serialNumber = album.getSerialNumber();
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

    public Artist getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
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

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", artist=" + artist +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Album)) return false;
        Album o = (Album) obj;
        if(this.getId() == o.getId() &&
            this.getReleaseYear() == o.getReleaseYear() &&
            this.getArtist() == o.getArtist() &&
            this.getName() == o.getName() &&
            this.getGenre() == o.getGenre() &&
            this.getDescription() == o.getDescription()) return true;
        return super.equals(obj);
    }
}

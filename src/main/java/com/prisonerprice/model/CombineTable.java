package com.prisonerprice.model;

public class CombineTable {
    private int id;
    private String name;
    private int release_year;
    private String artist;
    private String genre;
    private String album_desc;
    private int start_year;
    private int end_year;
    private String artist_desc;
    private int stock_NY_01;
    private int stock_NY_02;
    private int stock_DC_01;
    private int stock_VA_01;
    private int stock_MD_01;

    public CombineTable(int id, String name, int release_year, String artist, String genre, String album_desc,
                        int start_year, int end_year, String artist_desc,
                        int stock_NY_01, int stock_NY_02, int stock_DC_01, int stock_VA_01, int stock_MD_01) {
        this.id = id;
        this.name = name;
        this.release_year = release_year;
        this.artist = artist;
        this.genre = genre;
        this.album_desc = album_desc;
        this.start_year = start_year;
        this.end_year = end_year;
        this.artist_desc = artist_desc;
        this.stock_NY_01 = stock_NY_01;
        this.stock_NY_02 = stock_NY_02;
        this.stock_DC_01 = stock_DC_01;
        this.stock_VA_01 = stock_VA_01;
        this.stock_MD_01 = stock_MD_01;
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

    public String getAlbum_desc() {
        return album_desc;
    }

    public int getStart_year() {
        return start_year;
    }

    public int getEnd_year() {
        return end_year;
    }

    public String getArtist_desc() {
        return artist_desc;
    }

    public int getStock_NY_01() {
        return stock_NY_01;
    }

    public int getStock_NY_02() {
        return stock_NY_02;
    }

    public int getStock_DC_01() {
        return stock_DC_01;
    }

    public int getStock_VA_01() {
        return stock_VA_01;
    }

    public int getStock_MD_01() {
        return stock_MD_01;
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

    public void setAlbum_desc(String album_desc) {
        this.album_desc = album_desc;
    }

    public void setStart_year(int start_year) {
        this.start_year = start_year;
    }

    public void setEnd_year(int end_year) {
        this.end_year = end_year;
    }

    public void setArtist_desc(String artist_desc) {
        this.artist_desc = artist_desc;
    }

    public void setStock_NY_01(int stock_NY_01) {
        this.stock_NY_01 = stock_NY_01;
    }

    public void setStock_NY_02(int stock_NY_02) {
        this.stock_NY_02 = stock_NY_02;
    }

    public void setStock_DC_01(int stock_DC_01) {
        this.stock_DC_01 = stock_DC_01;
    }

    public void setStock_VA_01(int stock_VA_01) {
        this.stock_VA_01 = stock_VA_01;
    }

    public void setStock_MD_01(int stock_MD_01) {
        this.stock_MD_01 = stock_MD_01;
    }

    @Override
    public String toString() {
        return "CombineTable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", release_year=" + release_year +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                ", album_desc='" + album_desc + '\'' +
                ", start_year=" + start_year +
                ", end_year=" + end_year +
                ", artist_desc='" + artist_desc + '\'' +
                ", stock_NY_01=" + stock_NY_01 +
                ", stock_NY_02=" + stock_NY_02 +
                ", stock_DC_01=" + stock_DC_01 +
                ", stock_VA_01=" + stock_VA_01 +
                ", stock_MD_01=" + stock_MD_01 +
                '}';
    }
}

package com.prisonerprice.model;

public class Stock {
    private int id;
    private String album_name;
    private int stock_NY_01;
    private int stock_NY_02;
    private int stock_DC_01;
    private int stock_VA_01;
    private int stock_MD_01;

    public Stock(String album_name, int stock_NY_01, int stock_NY_02, int stock_DC_01, int stock_VA_01, int stock_MD_01) {
        this.album_name = album_name;
        this.stock_NY_01 = stock_NY_01;
        this.stock_NY_02 = stock_NY_02;
        this.stock_DC_01 = stock_DC_01;
        this.stock_VA_01 = stock_VA_01;
        this.stock_MD_01 = stock_MD_01;
    }
    public Stock(){
        this.id = 0;
        this.album_name = "NULL";
        this.stock_NY_01 = 0;
        this.stock_NY_02 = 0;
        this.stock_DC_01 = 0;
        this.stock_VA_01 = 0;
        this.stock_MD_01 = 0;
    }

    public int getId() {
        return id;
    }

    public String getAlbum_name() {
        return album_name;
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

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
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
        return "Stock{" +
                "id=" + id +
                ", album_name='" + album_name + '\'' +
                ", stock_NY_01=" + stock_NY_01 +
                ", stock_NY_02=" + stock_NY_02 +
                ", stock_DC_01=" + stock_DC_01 +
                ", stock_VA_01=" + stock_VA_01 +
                ", stock_MD_01=" + stock_MD_01 +
                '}';
    }
}

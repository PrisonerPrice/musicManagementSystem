package com.prisonerprice.model;

import java.util.UUID;

public class Stock {
    private int id;
    private String albumName;
    private int stock_NY_01;
    private int stock_NY_02;
    private int stock_DC_01;
    private int stock_VA_01;
    private int stock_MD_01;
    private String serialNumber;

    public Stock(int id, String albumName, int stock_NY_01, int stock_NY_02, int stock_DC_01, int stock_VA_01, int stock_MD_01, String serialNumber) {
        this.id = id;
        this.albumName = albumName;
        this.stock_NY_01 = stock_NY_01;
        this.stock_NY_02 = stock_NY_02;
        this.stock_DC_01 = stock_DC_01;
        this.stock_VA_01 = stock_VA_01;
        this.stock_MD_01 = stock_MD_01;
        this.serialNumber = serialNumber;
    }

    public Stock(String albumName, int stock_NY_01, int stock_NY_02, int stock_DC_01, int stock_VA_01, int stock_MD_01) {
        this.albumName = albumName;
        this.stock_NY_01 = stock_NY_01;
        this.stock_NY_02 = stock_NY_02;
        this.stock_DC_01 = stock_DC_01;
        this.stock_VA_01 = stock_VA_01;
        this.stock_MD_01 = stock_MD_01;
        this.serialNumber = UUID.randomUUID().toString();
    }
    public Stock(){
        this.id = 0;
        this.albumName = "NULL";
        this.stock_NY_01 = 0;
        this.stock_NY_02 = 0;
        this.stock_DC_01 = 0;
        this.stock_VA_01 = 0;
        this.stock_MD_01 = 0;
        this.serialNumber = UUID.randomUUID().toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getStock_NY_01() {
        return stock_NY_01;
    }

    public void setStock_NY_01(int stock_NY_01) {
        this.stock_NY_01 = stock_NY_01;
    }

    public int getStock_NY_02() {
        return stock_NY_02;
    }

    public void setStock_NY_02(int stock_NY_02) {
        this.stock_NY_02 = stock_NY_02;
    }

    public int getStock_DC_01() {
        return stock_DC_01;
    }

    public void setStock_DC_01(int stock_DC_01) {
        this.stock_DC_01 = stock_DC_01;
    }

    public int getStock_VA_01() {
        return stock_VA_01;
    }

    public void setStock_VA_01(int stock_VA_01) {
        this.stock_VA_01 = stock_VA_01;
    }

    public int getStock_MD_01() {
        return stock_MD_01;
    }

    public void setStock_MD_01(int stock_MD_01) {
        this.stock_MD_01 = stock_MD_01;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", album_name='" + albumName + '\'' +
                ", stock_NY_01=" + stock_NY_01 +
                ", stock_NY_02=" + stock_NY_02 +
                ", stock_DC_01=" + stock_DC_01 +
                ", stock_VA_01=" + stock_VA_01 +
                ", stock_MD_01=" + stock_MD_01 +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}

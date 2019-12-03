package com.prisonerprice.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "stock")
public class Stock {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "album_name")
    private String albumName;

    @Column
    private int stock_NY_01;

    @Column
    private int stock_NY_02;

    @Column
    private int stock_DC_01;

    @Column
    private int stock_VA_01;

    @Column
    private int stock_MD_01;

    @Id
    @Column(name = "serial_num")
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

    public Stock(Stock stock){
        this.id = stock.getId();
        this.albumName = stock.getAlbumName();
        this.stock_NY_01 = stock.getStock_NY_01();
        this.stock_NY_02 = stock.getStock_NY_02();
        this.stock_DC_01 = stock.getStock_DC_01();
        this.stock_VA_01 = stock.getStock_VA_01();
        this.stock_MD_01 = stock.getStock_MD_01();
        this.serialNumber = stock.getSerialNumber();
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

package com.prisonerprice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "stock")
public class Stock implements Serializable {
    public interface Brief{};

    @JsonView({Brief.class, Artist.WithChildren.class})
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private int id;

    @JsonView({Brief.class, Artist.WithChildren.class, Album.WithChildren.class})
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    private Album album;

    @JsonView({Brief.class, Artist.WithChildren.class, Album.WithChildren.class})
    @Column
    private int stock_NY_01;

    @JsonView({Brief.class, Artist.WithChildren.class, Album.WithChildren.class})
    @Column
    private int stock_NY_02;

    @JsonView({Brief.class, Artist.WithChildren.class, Album.WithChildren.class})
    @Column
    private int stock_DC_01;

    @JsonView({Brief.class, Artist.WithChildren.class, Album.WithChildren.class})
    @Column
    private int stock_VA_01;

    @JsonView({Brief.class, Artist.WithChildren.class, Album.WithChildren.class})
    @Column
    private int stock_MD_01;

    public Stock(int id, Album album, int stock_NY_01, int stock_NY_02, int stock_DC_01, int stock_VA_01, int stock_MD_01) {
        this.id = id;
        this.album = album;
        this.stock_NY_01 = stock_NY_01;
        this.stock_NY_02 = stock_NY_02;
        this.stock_DC_01 = stock_DC_01;
        this.stock_VA_01 = stock_VA_01;
        this.stock_MD_01 = stock_MD_01;
    }

    public Stock(Album album, int stock_NY_01, int stock_NY_02, int stock_DC_01, int stock_VA_01, int stock_MD_01) {
        this.album = album;
        this.stock_NY_01 = stock_NY_01;
        this.stock_NY_02 = stock_NY_02;
        this.stock_DC_01 = stock_DC_01;
        this.stock_VA_01 = stock_VA_01;
        this.stock_MD_01 = stock_MD_01;
    }
    public Stock(){
        this.id = 0;
        this.album = null;
        this.stock_NY_01 = 0;
        this.stock_NY_02 = 0;
        this.stock_DC_01 = 0;
        this.stock_VA_01 = 0;
        this.stock_MD_01 = 0;
    }

    public Stock(Stock stock){
        this.id = stock.getId();
        this.album = stock.getAlbum();
        this.stock_NY_01 = stock.getStock_NY_01();
        this.stock_NY_02 = stock.getStock_NY_02();
        this.stock_DC_01 = stock.getStock_DC_01();
        this.stock_VA_01 = stock.getStock_VA_01();
        this.stock_MD_01 = stock.getStock_MD_01();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
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

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", album=" + album +
                ", stock_NY_01=" + stock_NY_01 +
                ", stock_NY_02=" + stock_NY_02 +
                ", stock_DC_01=" + stock_DC_01 +
                ", stock_VA_01=" + stock_VA_01 +
                ", stock_MD_01=" + stock_MD_01 +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Stock)) return false;
        Stock s = (Stock) obj;
        if(s.getAlbum() == this.getAlbum() &&
            s.getId() == this.getId() &&
            s.getStock_NY_01() == this.getStock_NY_01() &&
            s.getStock_NY_02() == this.getStock_NY_02() &&
            s.getStock_DC_01() == this.getStock_DC_01() &&
            s.getStock_VA_01() == this.getStock_VA_01() &&
            s.getStock_MD_01() == this.getStock_MD_01()) return true;
        else return super.equals(obj);
    }
}

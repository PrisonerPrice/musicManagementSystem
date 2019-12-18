package com.prisonerprice.controller;

import com.prisonerprice.model.Album;
import com.prisonerprice.model.Artist;
import com.prisonerprice.model.Stock;
import com.prisonerprice.repository.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class InsertSomeDataToTheDatabase {

    private static ArtistDaoImpl artistDao;
    private static AlbumDao albumDao;
    private static StockDao stockDao;
    private Artist newArtist, newArtist2;
    private Album newAlbum;
    private Stock newStock;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void insertData(){
        artistDao = new ArtistDaoImpl();
        albumDao = new AlbumDaoImpl();
        stockDao = new StockDaoImpl();
        newArtist = new Artist(
                "New_Pants",
                1998,
                0,
                "xxxxxxx");
        newAlbum = new Album(
                "untitled album",
                2020,
                newArtist,
                "Punk",
                "No description"
        );
        newStock = new Stock(
                newAlbum,
                12,
                12,
                12,
                13,
                12
        );
        artistDao.save(newArtist);
        albumDao.save(newAlbum);
        stockDao.save(newStock);
    }

    @Test
    public void start(){
        Assert.assertTrue(true);
    }

    @Test
    public void tearDown(){
        List<Artist> artists = artistDao.getArtistList();
        for(Artist artist : artists){
            artistDao.delete(artist);
        }
        List<Album> albums = albumDao.getAlbums();
        for(Album album : albums){
            albumDao.delete(album);
        }
        List<Stock> stocks = stockDao.getStocks();
        for(Stock stock : stocks){
            stockDao.delete(stock);
        }
        Assert.assertTrue(true);
    }

}

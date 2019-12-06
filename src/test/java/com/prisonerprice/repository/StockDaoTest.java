package com.prisonerprice.repository;

import com.prisonerprice.model.Album;
import com.prisonerprice.model.Artist;
import com.prisonerprice.model.Stock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StockDaoTest {

    private static StockDao stockDao;
    private static AlbumDao albumDao;
    private static ArtistDao artistDao;
    private Stock newStock, newStock2;
    private Album EveryDayLife;
    private Artist Coldplay;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void init(){
        stockDao = new StockDaoImpl();
        albumDao = new AlbumDaoImpl();
        artistDao = new ArtistDaoImpl();
        Coldplay = new Artist(
            0,
            "Coldplay",
            1999,
            0,
            "A Britsh band"
        );
        EveryDayLife = new Album(
                0,
                "Everyday Life",
                2019,
                Coldplay,
                "Rock",
                "A new hit by Coldplay"
        );
        newStock = new Stock(
                0,
                EveryDayLife,
                234,
                234,
                123,
                123,
                99
        );
        artistDao.save(Coldplay);
        albumDao.save(EveryDayLife);
        stockDao.save(newStock);
    }

    @After
    public void teardown(){
        List<Stock> stocks = stockDao.getStocks();
        for(Stock stock : stocks){
            stockDao.delete(stock);
        }
        List<Album> albums = albumDao.getAlbums();
        for(Album album : albums){
            albumDao.delete(album);
        }
        List<Artist> artists = artistDao.getArtists();
        for(Artist artist : artists){
            artistDao.delete(artist);
        }
    }

    @Test
    public void getStocks() {
        List<Stock> stocks = stockDao.getStocks();
        for(Stock stock : stocks){
            logger.info(stock.toString());
        }
        int expectedNumOfDept = 1;
        Assert.assertEquals(expectedNumOfDept, stocks.size());
    }

    @Test
    public void updateStocks() {
        int expectStockInDC01 = 999;
        newStock2 = new Stock(stockDao.getStocks().get(0));
        newStock2.setStock_DC_01(999);
        stockDao.update(newStock2);
        Assert.assertEquals(expectStockInDC01, stockDao.getStocks().get(0).getStock_DC_01());
    }

    @Test
    public void deleteStocks(){
        List<Stock> stocks = stockDao.getStocks();
        for(Stock stock : stocks){
            stockDao.delete(stock);
        }
        stocks = stockDao.getStocks();
        int expectedNumOfDept = 0;
        Assert.assertEquals(expectedNumOfDept, stocks.size());
    }
}

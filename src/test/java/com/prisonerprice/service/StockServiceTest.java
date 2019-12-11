package com.prisonerprice.service;

import com.prisonerprice.init.AppInitializer;
import com.prisonerprice.model.Album;
import com.prisonerprice.model.Artist;
import com.prisonerprice.model.Stock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class StockServiceTest {
    @Autowired private ArtistService artistService;
    @Autowired private AlbumService albumService;
    @Autowired private StockService stockService;
    @Autowired private Logger logger;
    private Stock newStock, newStock2;
    private Album EveryDayLife;
    private Artist Coldplay;

    @Before
    public void init(){
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
        artistService.save(Coldplay);
        albumService.save(EveryDayLife);
        stockService.save(newStock);
    }

    @After
    public void tearDown(){
        List<Stock> stocks = stockService.getAllStocks();
        for(Stock stock : stocks){
            stockService.deleteByName(stock.getAlbum().getName());
        }
        List<Album> albums = albumService.getAllAlbums();
        for(Album album : albums){
            albumService.deleteByName(album.getName());
        }
        List<Artist> artists = artistService.getAllArtists();
        for(Artist artist : artists){
            artistService.deleteByName(artist.getName());
        }
    }

    @Test
    public void getStocksTest() {
        List<Stock> stocks = stockService.getAllStocks();
        for(Stock stock : stocks){
            logger.info(stock.toString());
        }
        int expectedNumOfDept = 1;
        Assert.assertEquals(expectedNumOfDept, stocks.size());
    }

    @Test
    public void updateStockTest() {
        int expectStockInDC01 = 999;
        newStock2 = new Stock(stockService.getAllStocks().get(0));
        newStock2.setStock_DC_01(999);
        stockService.update(newStock2);
        Assert.assertEquals(expectStockInDC01, stockService.getAllStocks().get(0).getStock_DC_01());
    }

    @Test
    public void deleteByNameTest(){
        Stock deletedStock = stockService.getAllStocks().get(0);
        int expectedNumberOfStocks = stockService.getAllStocks().size() - 1;
        String name = deletedStock.getAlbum().getName();
        stockService.deleteByName(name);
        Assert.assertEquals(expectedNumberOfStocks, stockService.getAllStocks().size());
    }

    @Test
    public void getByNameTest(){
        String name = newStock.getAlbum().getName();
        Stock testStock = stockService.getStockByName(name);
        Assert.assertTrue(name.equals(testStock.getAlbum().getName()));
    }
}

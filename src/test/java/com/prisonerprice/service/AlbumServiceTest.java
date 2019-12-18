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
public class AlbumServiceTest {
    @Autowired private ArtistService artistService;
    @Autowired private AlbumService albumService;
    @Autowired private StockService stockService;
    @Autowired private Logger logger;
    private Artist newArtist;
    private Album newAlbum, newAlbum2;
    private Stock newStock;

    @Before
    public void init(){
        newArtist = new Artist(
                0,
                "Alvvays",
                2011,
                0,
                "xxxx"
        );
        newAlbum = new Album(
                0,
                "Antisocialities",
                2017,
                newArtist,
                "Alternative",
                "xxxxx");
        newStock = new Stock(
                0,
                newAlbum,
                15,
                23,
                44,
                32,
                1
        );
        artistService.save(newArtist);
        albumService.save(newAlbum);
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
        List<Artist> artists = artistService.getArtistList();
        for(Artist artist : artists){
            artistService.deleteByName(artist.getName());
        }
    }

    @Test
    public void getAllAlbumsTest() {
        List<Album> albums = albumService.getAllAlbums();
        for(Album album : albums){
            logger.info(album.toString());
        }
        int expectedNumOfDept = 1;
        Assert.assertEquals(expectedNumOfDept, albums.size());
    }

    @Test
    public void updateAlbumTest(){
        newAlbum2 = new Album(albumService.getAllAlbums().get(0));
        newAlbum2.setDescription("A Whole new Description");
        String originalDesc = newAlbum.getDescription();
        albumService.update(newAlbum2);
        Assert.assertTrue(!originalDesc.equals(albumService.getAllAlbums().get(0).getDescription()));
    }

    @Test
    public void deleteAlbumByNameTest(){
        Album deletedAlbum = albumService.getAllAlbums().get(0);
        int expectedNumberOfAlbums = albumService.getAllAlbums().size() - 1;
        String albumName = deletedAlbum.getName();
        albumService.deleteByName(albumName);
        Assert.assertEquals(expectedNumberOfAlbums, albumService.getAllAlbums().size());
    }

    @Test
    public void getAlbumByNameTest(){
        String name = newAlbum.getName();
        Album testAlbum = albumService.getAlbumByName(name);
        Assert.assertTrue(name.equals(testAlbum.getName()));
    }

    @Test
    public void getAlbumAndStockTest(){
        String name = newAlbum.getName();
        int expectedElementsNumbers = 2;
        List<Object[]> list = albumService.getAlbumAndStock(name);
        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.get(i).length; j++){
                logger.debug(list.get(i)[j].toString());
            }
        }
        Assert.assertEquals(expectedElementsNumbers, list.size() * list.get(0).length);
    }
}

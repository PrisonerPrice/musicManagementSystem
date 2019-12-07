package com.prisonerprice.repository;

import com.prisonerprice.model.Album;
import com.prisonerprice.model.Artist;
import com.prisonerprice.model.Stock;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Set;

public class AlbumDaoTest {

    private static ArtistDaoImpl artistDao;
    private static AlbumDaoImpl albumDao;
    private static StockDaoImpl stockDao;
    private Stock newStock;
    private Album newAlbum, newAlbum2;
    private Artist Alvvays;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void init(){
        stockDao = new StockDaoImpl();
        albumDao = new AlbumDaoImpl();
        artistDao = new ArtistDaoImpl();
        Alvvays = new Artist(
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
                Alvvays,
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
        artistDao.save(Alvvays);
        albumDao.save(newAlbum);
        stockDao.save(newStock);
    }

    @After
    public void tearDown(){
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
    public void getAlbums() {
        List<Album> albums = albumDao.getAlbums();
        for(Album album : albums){
            logger.info(album.toString());
        }
        int expectedNumOfDept = 1;
        Assert.assertEquals(expectedNumOfDept, albums.size());
    }

    @Test
    public void updateAlbums(){
        newAlbum2 = new Album(albumDao.getAlbums().get(0));
        newAlbum2.setDescription("A Whole new Description");
        String originalDesc = newAlbum.getDescription();
        albumDao.update(newAlbum2);
        Assert.assertTrue(!originalDesc.equals(albumDao.getAlbums().get(0).getDescription()));
    }

    @Test
    public void deleteAlbums(){
        List<Album> albums = albumDao.getAlbums();
        for(Album album : albums){
            albumDao.delete(album);
        }
        albums = albumDao.getAlbums();
        int expectedNumOfDept = 0;
        Assert.assertEquals(expectedNumOfDept, albums.size());
    }

    @Test
    public void getAlbumByNameTest(){
        String name = newAlbum.getName();
        Album testAlbum = albumDao.getAlbumByName(name);
        Assert.assertTrue(name.equals(testAlbum.getName()));
    }
}

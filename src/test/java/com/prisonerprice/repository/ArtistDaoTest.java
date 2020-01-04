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

import java.util.Arrays;
import java.util.List;

public class ArtistDaoTest {

    private static ArtistDaoImpl artistDao;
    private static AlbumDao albumDao;
    private static StockDao stockDao;
    private Artist newArtist, newArtist2;
    private Album newAlbum;
    private Stock newStock;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void init(){
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

    @After
    public void tearDown(){
        stockDao.getStockList().forEach(stock -> stockDao.delete(stock));
        albumDao.getAlbumList().forEach(album -> albumDao.delete(album));
        artistDao.getArtistList().forEach(artist -> artistDao.delete(artist));
    }

    @Test
    public void getArtistsTest() {
        List<Artist> artists = artistDao.getArtistList();
        artists.forEach(artist -> logger.info(artist.toString()));
        int expectedNumOfArtist = 1;
        Assert.assertEquals(expectedNumOfArtist, artists.size());
    }

    @Test
    public void getArtistsWithChildrenTest(){
        List<Artist> artists = artistDao.getArtistListWithChildren();
        artists.forEach(artist -> logger.info(artists.toString()));
        int expectedNumOfArtist = 1;
        Assert.assertEquals(expectedNumOfArtist, artists.get(0).getAlbums().size());
    }

    @Test
    public void updateArtistTest(){
        newArtist2 = new Artist(artistDao.getArtistList().get(0));
        newArtist2.setDescription("A Whole new Description");
        String originalDesc = newArtist.getDescription();
        artistDao.update(newArtist2);
        Assert.assertTrue(!originalDesc.equals(artistDao.getArtistList().get(0).getDescription()));
    }

    @Test
    public void deleteArtistTest(){
        List<Artist> artists = artistDao.getArtistList();
        artists.forEach(artist -> artistDao.delete(artist));
        artists = artistDao.getArtistList();
        int expectedNumOfDept = 0;
        Assert.assertEquals(expectedNumOfDept, artists.size());
    }

    @Test
    public void deleteArtistByNameTest(){
        Artist deletedArtist = artistDao.getArtistList().get(0);
        int expectedNumberOfArtists = artistDao.getArtistList().size() - 1;
        String artistName = deletedArtist.getName();
        artistDao.deleteArtistByName(artistName);
        Assert.assertEquals(expectedNumberOfArtists, artistDao.getArtistList().size());
    }

    @Test
    public void getArtistByNameTest(){
        String name = newArtist.getName();
        Artist testArtist = artistDao.getArtistByName(name);
        Assert.assertTrue(name.equals(testArtist.getName()));
    }

    @Test
    public void getArtistAndAlbumsTest(){
        String name = newArtist.getName();
        int expectedElementsNumbers = 2;
        List<Object[]> list = artistDao.getArtistAndAlbums(name);
        list.stream().flatMap(objects -> Arrays.stream(objects)).forEach(obj -> logger.debug(obj.toString()));
        Assert.assertEquals(expectedElementsNumbers, list.size() * list.get(0).length);
    }

    @Test
    public void getArtistAndAlbumsAndStocksTest(){
        String name = newArtist.getName();
        int expectedElementsNumbers = 3;
        List<Object[]> list = artistDao.getArtistAndAlbumsAndStocks(name);
        list.stream().flatMap(objects -> Arrays.stream(objects)).forEach(obj -> logger.debug(obj.toString()));
        Assert.assertEquals(expectedElementsNumbers, list.size() * list.get(0).length);
    }
}

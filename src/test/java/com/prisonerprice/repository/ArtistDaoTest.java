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
        List<Artist> artists = artistDao.getArtists();
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
    }

    @Test
    public void getArtistsTest() {
        List<Artist> artists = artistDao.getArtists();
        for(Artist artist : artists){
            logger.info(artist.toString());
        }
        int expectedNumOfDept = 1;
        Assert.assertEquals(expectedNumOfDept, artists.size());
    }

    @Test
    public void updateArtistTest(){
        newArtist2 = new Artist(artistDao.getArtists().get(0));
        newArtist2.setDescription("A Whole new Description");
        String originalDesc = newArtist.getDescription();
        artistDao.update(newArtist2);
        Assert.assertTrue(!originalDesc.equals(artistDao.getArtists().get(0).getDescription()));
    }

    @Test
    public void deleteArtistTest(){
        List<Artist> artists = artistDao.getArtists();
        for(Artist artist : artists){
            artistDao.delete(artist);
        }
        artists = artistDao.getArtists();
        int expectedNumOfDept = 0;
        Assert.assertEquals(expectedNumOfDept, artists.size());
    }

    @Test
    public void deleteArtistByNameTest(){
        Artist deletedArtist = artistDao.getArtists().get(0);
        int expectedNumberOfArtists = artistDao.getArtists().size() - 1;
        String artistName = deletedArtist.getName();
        artistDao.deleteArtistByName(artistName);
        Assert.assertEquals(expectedNumberOfArtists, artistDao.getArtists().size());
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
        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.get(i).length; j++){
                logger.debug(list.get(i)[j].toString());
            }
        }
        Assert.assertEquals(expectedElementsNumbers, list.size() * list.get(0).length);
    }

    @Test
    public void getArtistAndAlbumsAndStocksTest(){
        String name = newArtist.getName();
        int expectedElementsNumbers = 3;
        List<Object[]> list = artistDao.getArtistAndAlbumsAndStocks(name);
        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.get(i).length; j++){
                logger.debug(list.get(i)[j].toString());
            }
        }
        Assert.assertEquals(expectedElementsNumbers, list.size() * list.get(0).length);
    }
}

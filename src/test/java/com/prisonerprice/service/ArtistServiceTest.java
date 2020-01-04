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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class ArtistServiceTest {

    @Autowired private ArtistService artistService;
    @Autowired private AlbumService albumService;
    @Autowired private StockService stockService;
    @Autowired private Logger logger;
    private Artist newArtist, newArtist2;
    private Album newAlbum;
    private Stock newStock;

    @Before
    public void init(){
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
        artistService.save(newArtist);
        albumService.save(newAlbum);
        stockService.save(newStock);
    }

    @After
    public void tearDown(){
        stockService.getStockList().forEach(stock -> stockService.deleteByName(stock.getAlbum().getName()));
        albumService.getAlbumList().forEach(album -> albumService.deleteByName(album.getName()));
        artistService.getArtistList().forEach(artist -> artistService.deleteByName(artist.getName()));
    }

    @Test
    public void getAllArtists() {
        List<Artist> artists = artistService.getArtistList();
        artists.forEach(artist -> logger.info(artist.toString()));
        int expectedNumOfDept = 1;
        Assert.assertEquals(expectedNumOfDept, artists.size());
    }

    @Test
    public void updateArtistTest(){
        newArtist2 = new Artist(artistService.getArtistList().get(0));
        newArtist2.setDescription("A Whole new Description");
        String originalDesc = newArtist.getDescription();
        artistService.update(newArtist2);
        Assert.assertTrue(!originalDesc.equals(artistService.getArtistList().get(0).getDescription()));
    }

    @Test
    public void deleteArtistByNameTest(){
        Artist deletedArtist = artistService.getArtistList().get(0);
        int expectedNumberOfArtists = artistService.getArtistList().size() - 1;
        String artistName = deletedArtist.getName();
        artistService.deleteByName(artistName);
        Assert.assertEquals(expectedNumberOfArtists, artistService.getArtistList().size());
    }

    @Test
    public void getArtistByNameTest(){
        String name = newArtist.getName();
        Artist testArtist = artistService.getArtistByName(name);
        Assert.assertTrue(name.equals(testArtist.getName()));
    }

    @Test
    public void getArtistAndAlbumsTest(){
        String name = newArtist.getName();
        int expectedElementsNumbers = 2;
        List<Object[]> list = artistService.getArtistAndAlbums(name);
        list.stream().flatMap(objects -> Arrays.stream(objects)).forEach(obj -> logger.debug(obj.toString()));
        Assert.assertEquals(expectedElementsNumbers, list.size() * list.get(0).length);
    }

    @Test
    public void getArtistAndAlbumsAndStocksTest(){
        String name = newArtist.getName();
        int expectedElementsNumbers = 3;
        List<Object[]> list = artistService.getArtistAndAlbumsAndStocks(name);
        list.stream().flatMap(objects -> Arrays.stream(objects)).forEach(obj -> logger.debug(obj.toString()));
        Assert.assertEquals(expectedElementsNumbers, list.size() * list.get(0).length);
    }
}

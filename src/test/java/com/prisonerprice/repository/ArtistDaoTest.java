package com.prisonerprice.repository;

import com.prisonerprice.model.Artist;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ArtistDaoTest {

    private static ArtistDaoImpl artistDao;
    private Artist newArtist, newArtist2;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void init(){
        artistDao = new ArtistDaoImpl();
        newArtist = new Artist(
                "New Pants",
                1998,
                0,
                "xxxxxxx");
        newArtist2 = new Artist(newArtist);
        artistDao.save(newArtist);
    }

    @After
    public void tearDown(){
        artistDao.delete(newArtist.getSerialNumber());
        artistDao.delete(newArtist2.getSerialNumber());
    }

    @Test
    public void getArtists() {
        List<Artist> artists = artistDao.getArtists();
        for(Artist artist : artists){
            logger.info(artist.toString());
        }
        int expectedNumOfDept = 1;
        Assert.assertEquals(expectedNumOfDept, artists.size());
    }

    @Test
    public void updateArtists(){
        newArtist2.setDescription("A Whole New Artist");
        String originalDesc = newArtist.getDescription();
        String serialNumber = newArtist.getSerialNumber();
        artistDao.update(newArtist2);
        List<Artist> artists = artistDao.getArtists();
        for(Artist artist : artists){
            logger.info(artist.toString());
            if(artist.getSerialNumber().equals(serialNumber)){
                Assert.assertTrue(!originalDesc.equals(artist.getDescription()));
                return;
            }
        }
        Assert.assertTrue(false);
    }

    @Test
    public void deleteArtists(){
        artistDao.delete(newArtist.getSerialNumber());
        List<Artist> artists = artistDao.getArtists();
        int expectedNumOfDept = 0;
        Assert.assertEquals(expectedNumOfDept, artists.size());
    }
}

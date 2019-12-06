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
        artistDao.save(newArtist);
    }

    @After
    public void tearDown(){
        List<Artist> artists = artistDao.getArtists();
        for(Artist artist : artists){
            artistDao.delete(artist);
        }
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
        newArtist2 = new Artist(artistDao.getArtists().get(0));
        newArtist2.setDescription("A Whole new Description");
        String originalDesc = newArtist.getDescription();
        artistDao.update(newArtist2);
        Assert.assertTrue(!originalDesc.equals(artistDao.getArtists().get(0).getDescription()));
    }

    @Test
    public void deleteArtists(){
        List<Artist> artists = artistDao.getArtists();
        for(Artist artist : artists){
            artistDao.delete(artist);
        }
        artists = artistDao.getArtists();
        int expectedNumOfDept = 0;
        Assert.assertEquals(expectedNumOfDept, artists.size());
    }
}

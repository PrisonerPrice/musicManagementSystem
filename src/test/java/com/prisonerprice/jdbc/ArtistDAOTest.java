package com.prisonerprice.jdbc;

import com.prisonerprice.model.Artist;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ArtistDAOTest {
    private ArtistDAO artistDAO;
    private Artist newArtist;

    @Before
    public void init(){
        artistDAO = new ArtistDAO();
        newArtist = new Artist(
                0,
                "New Pants",
                1998,
                0,
                "xxxxxxx",
                8);
        artistDAO.insertArtists(newArtist);
    }

    @After
    public void tearDown(){
        artistDAO.deleteArtists(newArtist.getSerial_num());
    }

    @Test
    public void getArtistsTest(){
        List<Artist> artists = artistDAO.getArtists();
        int exceptionNumOfArtists = 7 + 1;
        for(Artist artist : artists){
            System.out.println(artist);
        }
        Assert.assertEquals(exceptionNumOfArtists, artists.size());
    }

    @Test
    public void insertArtistsTest(){
        List<Artist> artists = artistDAO.getArtists();
        int exceptionNumOfArtists = 7 + 1;
        for(Artist artist : artists){
            System.out.println(artist);
        }
        Assert.assertEquals(exceptionNumOfArtists, artists.size());
    }

    @Test
    public void deleteArtistsTest(){
        artistDAO.deleteArtists(newArtist.getSerial_num());
        List<Artist> artists = artistDAO.getArtists();
        int exceptionNumOfArtists = 7;
        for(Artist artist : artists){
            System.out.println(artist);
        }
        Assert.assertEquals(exceptionNumOfArtists, artists.size());
    }

    @Test
    public void updateArtistsTest(){
        String exceptionDesc = "Don''t ask me what is Disco";
        artistDAO.updateArtists(newArtist.getSerial_num(), exceptionDesc);
        exceptionDesc = "Don't ask me what is Disco";
        List<Artist> artists = artistDAO.getArtists();
        for(Artist artist : artists){
            System.out.println(artist);
            String desc = artist.getDescription();
            if(desc.equals(exceptionDesc)) {
                Assert.assertTrue(true);
                return;
            }
        }
        Assert.assertTrue(false);
    }
}

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
                "New Pants",
                1998,
                0,
                "xxxxxxx");
        artistDAO.insertArtists(newArtist);
    }

    @After
    public void tearDown(){
        artistDAO.deleteArtists(newArtist.getSerialNumber());
    }

    @Test
    public void getArtistsTest(){
        List<Artist> artists = artistDAO.getArtists();
        int exceptionNumOfArtists = artists.size() + 1;
        artistDAO.insertArtists(newArtist);
        artists = artistDAO.getArtists();
        for(Artist artist : artists){
            System.out.println(artist);
        }
        Assert.assertEquals(exceptionNumOfArtists, artists.size());
    }

    @Test
    public void insertArtistsTest(){
        List<Artist> artists = artistDAO.getArtists();
        int exceptionNumOfArtists = artists.size() + 1;
        artistDAO.insertArtists(newArtist);
        artists = artistDAO.getArtists();
        for(Artist artist : artists){
            System.out.println(artist);
        }
        Assert.assertEquals(exceptionNumOfArtists, artists.size());
    }

    @Test
    public void deleteArtistsTest(){
        List<Artist> artists = artistDAO.getArtists();
        int originalNumOfArtists = artists.size();
        artistDAO.deleteArtists(newArtist.getSerialNumber());
        artists = artistDAO.getArtists();
        for(Artist artist : artists){
            System.out.println(artist);
        }
        Assert.assertTrue(originalNumOfArtists > artists.size());
    }

    @Test
    public void updateArtistsTest(){
        String exceptionDesc = "Don''t ask me what is Disco";
        artistDAO.updateArtists(newArtist.getSerialNumber(), exceptionDesc);
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

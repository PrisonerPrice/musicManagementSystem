package com.prisonerprice.jdbc;

import com.prisonerprice.model.Artist;
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
        newArtist = new Artist("New Pants",
                1998,
                0,
                "Don''t ask me what is Disco");
    }

    @Test
    public void getArtistsTest(){
        List<Artist> artists = artistDAO.getArtists();
        int exceptionNumOfArtists = 7;

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
        artistDAO.deleteArtists();
        List<Artist> artists = artistDAO.getArtists();
        int exceptionNumOfArtists = 7;

        for(Artist artist : artists){
            System.out.println(artist);
        }

        Assert.assertEquals(exceptionNumOfArtists, artists.size());
    }

    @Test
    public void updateArtistsTest(){
        artistDAO.updateArtists();
        List<Artist> artists = artistDAO.getArtists();
        String exceptionDesc = "Need to update";

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

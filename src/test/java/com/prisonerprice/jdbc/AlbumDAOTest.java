package com.prisonerprice.jdbc;

import com.prisonerprice.model.Album;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AlbumDAOTest {
    private AlbumDao albumDao;
    private Album newAlbum;

    @Before
    public void init(){
        albumDao = new AlbumDao();
        newAlbum = new Album("Antisocialities",
                2017,
                "Alvvays",
                "Alternative",
                "secend album of Alvvays");
        albumDao.insertAlbums(newAlbum);
    }

    @After
    public void tearDown(){
        albumDao.deleteAlbums();
    }

    @Test
    public void getAlbumsTest(){
        List<Album> albums = albumDao.getAlbums();
        int exceptionNumOfAlbums = 9;

        for(Album album : albums){
            System.out.println(album);
        }

        Assert.assertEquals(exceptionNumOfAlbums, albums.size());
    }

    @Test
    public void insertAlbumsTest(){
        List<Album> albums = albumDao.getAlbums();
        int exceptionNumOfAlbums = albums.size() + 1;
        albumDao.insertAlbums(newAlbum);
        albums = albumDao.getAlbums();

        for(Album album : albums){
            System.out.println(album);
        }

        Assert.assertEquals(exceptionNumOfAlbums, albums.size());
    }

    @Test
    public void deleteAlbumsTest(){
        albumDao.deleteAlbums();
        List<Album> albums = albumDao.getAlbums();
        int exceptionNumOfAlbums = 9;

        for(Album album : albums){
            System.out.println(album);
        }

        Assert.assertEquals(exceptionNumOfAlbums, albums.size());
    }

    @Test
    public void updateAlbumsTest(){
        albumDao.updateAlbums();
        List<Album> albums = albumDao.getAlbums();
        String exceptionDesc = "Need to update";

        for(Album album : albums){
            System.out.println(album);
            String desc = album.getDescription();
            if(desc.equals(exceptionDesc)) {
                Assert.assertTrue(true);
                return;
            }
        }

        Assert.assertTrue(false);
    }
}

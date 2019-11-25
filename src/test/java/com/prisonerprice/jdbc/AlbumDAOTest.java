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
        newAlbum = new Album(
                0,
                "Antisocialities",
                2017,
                "Alvvays",
                "Alternative",
                "xxxxx",
                10);
        albumDao.insertAlbums(newAlbum);
    }

    @After
    public void tearDown(){
        albumDao.deleteAlbums(newAlbum.getSerial_num());
    }

    @Test
    public void getAlbumsTest(){
        List<Album> albums = albumDao.getAlbums();
        int exceptionNumOfAlbums = 9 + 1;
        for(Album album : albums){
            System.out.println(album);
        }
        Assert.assertEquals(exceptionNumOfAlbums, albums.size());
    }

    @Test
    public void insertAlbumsTest(){
        List<Album> albums = albumDao.getAlbums();
        int exceptionNumOfAlbums = 9 + 1;
        for(Album album : albums){
            System.out.println(album);
        }
        Assert.assertEquals(exceptionNumOfAlbums, albums.size());
    }

    @Test
    public void deleteAlbumsTest(){
        albumDao.deleteAlbums(newAlbum.getSerial_num());
        List<Album> albums = albumDao.getAlbums();
        int exceptionNumOfAlbums = 9;
        for(Album album : albums){
            System.out.println(album);
        }
        Assert.assertEquals(exceptionNumOfAlbums, albums.size());
    }

    @Test
    public void updateAlbumsTest(){
        String exceptionDesc = "Their 2nd album";
        albumDao.updateAlbums(10, exceptionDesc);
        List<Album> albums = albumDao.getAlbums();
        for(Album album : albums){
            System.out.println(album.toString());
            if(album.getDescription().equals(exceptionDesc)){
                Assert.assertTrue(true);
                return;
            }
        }
        Assert.assertTrue(false);
    }
}

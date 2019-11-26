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
                "Antisocialities",
                2017,
                "Alvvays",
                "Alternative",
                "xxxxx");
        albumDao.insertAlbums(newAlbum);
    }

    @After
    public void tearDown(){
        albumDao.deleteAlbums(newAlbum.getSerialNumber());
    }

    @Test
    public void getAlbumsTest(){
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
        List<Album> albums = albumDao.getAlbums();
        int originalNumOfAlbums = albums.size();
        albumDao.deleteAlbums(newAlbum.getSerialNumber());
        albums = albumDao.getAlbums();
        Assert.assertTrue(originalNumOfAlbums > albums.size());
    }

    @Test
    public void updateAlbumsTest(){
        String exceptionDesc = "Their 2nd album";
        albumDao.updateAlbums(newAlbum.getSerialNumber(), exceptionDesc);
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

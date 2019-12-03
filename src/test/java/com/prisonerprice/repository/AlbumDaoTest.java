package com.prisonerprice.repository;

import com.prisonerprice.jdbc.AlbumDAO;
import com.prisonerprice.model.Album;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Set;

public class AlbumDaoTest {

    private static AlbumDao albumDao;
    private Album newAlbum, newAlbum2;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void init(){
        albumDao = new AlbumDaoImpl();
        newAlbum = new Album(
                "Antisocialities",
                2017,
                "Alvvays",
                "Alternative",
                "xxxxx");
        albumDao.save(newAlbum);
        newAlbum2 = new Album(newAlbum);
    }

    @After
    public void tearDown(){
        albumDao.delete(newAlbum.getSerialNumber());
        albumDao.delete(newAlbum2.getSerialNumber());
    }

    @Test
    public void getAlbums() {
        List<Album> albums = albumDao.getAlbums();
        for(Album album : albums){
            logger.info(album.toString());
        }
        int expectedNumOfDept = 1;
        Assert.assertEquals(expectedNumOfDept, albums.size());
    }

    @Test
    public void updateAlbums(){
        newAlbum2.setDescription("A Whole new Description");
        String originalDesc = newAlbum.getDescription();
        String serialNumber = newAlbum.getSerialNumber();
        albumDao.update(newAlbum2);
        List<Album> albums = albumDao.getAlbums();
        for(Album album : albums){
            logger.info(album.toString());
            if(album.getSerialNumber().equals(serialNumber)){
                Assert.assertTrue(!originalDesc.equals(album.getDescription()));
                return;
            }
        }
        Assert.assertTrue(false);
    }

    @Test
    public void deleteAlbums(){
        albumDao.delete(newAlbum.getSerialNumber());
        List<Album> albums = albumDao.getAlbums();
        int expectedNumOfDept = 0;
        Assert.assertEquals(expectedNumOfDept, albums.size());
    }
}

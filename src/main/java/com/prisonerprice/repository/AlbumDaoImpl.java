package com.prisonerprice.repository;

import com.prisonerprice.model.Album;
import com.prisonerprice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

public class AlbumDaoImpl implements AlbumDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Connection<Album> albumConnection = new Connection<>();

    @Override
    public boolean save(Album album) {
        return albumConnection.save(album);
    }

    @Override
    public boolean update(Album album) {
        return albumConnection.update(album);
    }

    @Override
    public boolean delete(Album album) {
        return albumConnection.delete(album);
    }

    public boolean deleteAlbumByName(Album album){
        return true;
    }

    @Override
    public List<Album> getAlbums() {
        return albumConnection.getObjectList("Album");
    }

    public Album getAlbumByName(String albumName){
        String hql = "FROM Album as album left join fetch album.stock " +
                "where lower(album.name) = :param";
        return albumConnection.getObjectByName(hql, albumName);
    }

    public Album getAlbumAndStock(String albumName){
        String hql = "";
        return null;
    }
}

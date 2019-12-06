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

    @Override
    public List<Album> getAlbums() {
        return albumConnection.getAll("Album");
    }
}

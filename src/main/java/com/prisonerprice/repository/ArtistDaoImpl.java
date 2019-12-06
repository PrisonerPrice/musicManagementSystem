package com.prisonerprice.repository;

import com.prisonerprice.model.Album;
import com.prisonerprice.model.Artist;
import com.prisonerprice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ArtistDaoImpl implements ArtistDao{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Connection<Artist> artistConnection = new Connection<>();

    @Override
    public boolean save(Artist artist) {
        return artistConnection.save(artist);
    }

    @Override
    public boolean update(Artist artist) {
        return artistConnection.update(artist);
    }

    @Override
    public boolean delete(Artist artist) {
        return artistConnection.delete(artist);
    }

    @Override
    public List<Artist> getArtists() {
        return artistConnection.getAll("Artist");
    }
}

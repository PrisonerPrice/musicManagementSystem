package com.prisonerprice.repository;

import com.prisonerprice.model.Artist;

import java.util.List;

public interface ArtistDao {
    boolean save(Artist artist);
    boolean update(Artist artist);
    boolean delete(Artist artist);
    List<Artist> getArtists();
}

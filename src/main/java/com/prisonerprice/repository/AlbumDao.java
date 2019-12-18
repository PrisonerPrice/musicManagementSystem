package com.prisonerprice.repository;

import com.prisonerprice.model.Album;

import java.util.List;

public interface AlbumDao {
    boolean save(Album album);
    boolean update(Album album);
    boolean delete(Album album);
    List<Album> getAlbumList();
}

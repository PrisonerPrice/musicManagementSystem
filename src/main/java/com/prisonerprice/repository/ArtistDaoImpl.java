package com.prisonerprice.repository;

import com.prisonerprice.model.Artist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ArtistDaoImpl implements ArtistDao{

    //@Autowired
    private Logger logger= LoggerFactory.getLogger(getClass());

    private Connection<Artist> artistConnection = new Connection<>();

    @Override
    public boolean save(Artist artist) {
        //logger.info(artist.getAlbums().toString());
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

    public boolean deleteArtistByName(String artistName){
        return delete(getArtistByName(artistName));
    }

    @Override
    public List<Artist> getArtistList() {
        return artistConnection.getObjectList("Artist");
    }

    public List<Artist> getArtistListWithChildren(){
        //logger.debug("INTO the method getArtistsWithChildren");
        String hql = "FROM Artist as artist left join fetch artist.albums as albums left join fetch albums.stock";
        return artistConnection.getObjectListWithChildren(hql);
    }

    public Artist getArtistByName(String artistName){
        logger.debug("INTO the method getArtistByName");
        String hql = "FROM Artist as artist left join fetch artist.albums as albums left join " +
                "fetch albums.stock where lower(artist.name) = :param";
        return artistConnection.getObjectByName(hql, artistName);
    }

    public List<Object[]> getArtistAndAlbums(String artistName){
        String hql = "FROM Artist as artist left join artist.albums where lower(artist.name) = :param";
        return artistConnection.getCombinedObjects(hql, artistName);
    }

    public List<Object[]> getArtistAndAlbumsAndStocks(String artistName){
        String hql = "FROM Artist as artist left join artist.albums as albums left join " +
                "albums.stock as stocks where lower(artist.name) = :param";
        return artistConnection.getCombinedObjects(hql, artistName);
    }
}

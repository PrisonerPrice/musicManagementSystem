package com.prisonerprice.controller;

import com.prisonerprice.model.Album;
import com.prisonerprice.model.Artist;
import com.prisonerprice.service.ArtistService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = {"/artists"})
public class ArtistController {

    private Logger logger;
    private ArtistService artistService;

    @Autowired
    public ArtistController(Logger logger, ArtistService artistService){
        this.logger = logger;
        this.artistService = artistService;
    }

    // error
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Artist> getArtists(){
        List<Artist> artists = artistService.getArtistList();
        return artists;
    }

    @RequestMapping(value = "/with-children", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Artist> getArtistsWithChildren(){
        List<Artist> artists = artistService.getArtistListWithChildren();
        return artists;
    }

    @RequestMapping(value = "/{artistName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Artist getArtistByName(@PathVariable String artistName){
        Artist artist = artistService.getArtistByName(artistName);
        return artist;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createArtist(@RequestBody Artist artist){
        String msg = "The artist was created";
        Set<Album> albums = artist.getAlbums();
        for(Album album : albums){
            album.setArtist(artist);
            album.getStock().setAlbum(album);
        }
        boolean isSuccess = artistService.save(artist);
        if (!isSuccess) msg = "The artist was not created";
        return msg;
    }

    // have to provide the id
    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateArtist(@RequestBody Artist artist){
        String msg = "The artist was updated";
        Set<Album> albums = artist.getAlbums();
        for(Album album : albums){
            album.setArtist(artist);
            album.getStock().setAlbum(album);
        }
        boolean isSuccess = artistService.update(artist);
        if(!isSuccess) msg = "The artist was not updated";
        return msg;
    }

    @RequestMapping(value = "/{artistName}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteArtist(@PathVariable String artistName){
        String msg = "The artist was deleted";
        boolean isSucces = artistService.deleteByName(artistName);
        if(!isSucces) msg = "The artist was not deleted";
        return msg;
    }

}

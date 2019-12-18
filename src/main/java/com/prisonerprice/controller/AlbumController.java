package com.prisonerprice.controller;

import com.prisonerprice.model.Album;
import com.prisonerprice.model.Artist;
import com.prisonerprice.service.AlbumService;
import com.prisonerprice.service.ArtistService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/albums"})
public class AlbumController {
    private Logger logger;
    private ArtistService artistService;
    private AlbumService albumService;

    @Autowired
    public AlbumController(Logger logger, ArtistService artistService, AlbumService albumService){
        this.logger = logger;
        this.artistService = artistService;
        this.albumService = albumService;
    }

    // error: has children
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Album> getAlbums(){
        List<Album> albums = albumService.getAlbumList();
        return albums;
    }

    @RequestMapping(value = "/with-children", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Album> getAlbumsWithChildren(){
        List<Album> albums = albumService.getAlbumListWithChildren();
        return albums;
    }

    @RequestMapping(value = "/{albumName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Album getAlbumByName(@PathVariable String albumName){
        Album album = albumService.getAlbumByName(albumName);
        return album;
    }

    // error
    @RequestMapping(value = "/{artistName}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createArtist(@RequestBody Album album, @PathVariable String artistName){
        String msg = "The album was created";
        if(artistService.getArtistByName(artistName) != null){
            album.setArtist(artistService.getArtistByName(artistName));
        }
        else{
            artistService.save(new Artist(artistName));
            album.setArtist(artistService.getArtistByName(artistName));
        }
        album.getStock().setAlbum(album);
        boolean isSuccess = albumService.save(album);
        if (!isSuccess) msg = "The album was not created";
        return msg;
    }
}

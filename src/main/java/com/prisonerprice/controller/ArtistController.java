package com.prisonerprice.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
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

    //@JsonView({Artist.Full.class})
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Artist> getArtists(){
        List<Artist> artists = artistService.getArtistList();
        return artists;
    }

    //@JsonView({Artist.WithChildren.class})
    @RequestMapping(value = "/with-children", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Artist> getArtistsWithChildren(){
        List<Artist> artists = artistService.getArtistListWithChildren();
        return artists;
    }

    //@JsonView({Artist.WithChildren.class})
    @RequestMapping(value = "/{artistName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Artist getArtistByName(@PathVariable String artistName){
        Artist artist = artistService.getArtistByName(artistName);
        return artist;
    }

    //@JsonView({Artist.WithChildren.class})
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createArtist(@RequestBody Artist artist){
        String msg = "The artist was created";
        Set<Album> albums = artist.getAlbums();
        if (albums != null){
            for(Album album : albums){
                album.setArtist(artist);
                album.getStock().setAlbum(album);
            }
        }
        boolean isSuccess = artistService.save(artist);
        if (!isSuccess) msg = "The artist was not created";
        return msg;
    }

    // have to provide the id
    //@JsonView({Artist.WithChildren.class})
    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateArtist(@RequestBody Artist artist){
        String msg = "The artist was updated";
        boolean isSuccess = artistService.update(artist);
        if(!isSuccess) msg = "The artist was not updated";
        return msg;
    }

    //@JsonView({Artist.WithChildren.class})
    @RequestMapping(value = "/{artistName}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteArtist(@PathVariable String artistName){
        String msg = "The artist was deleted";
        boolean isSuccess = artistService.deleteByName(artistName);
        if(!isSuccess) msg = "The artist was not deleted";
        return msg;
    }

    /*
    Test cases using Postman:
    passed 1: getAll artists - without children
    passed 2: getAll artists - with children
    passed 3: get an artist by name - with children
    Passed 4: post an artist - with album, with stock
        {
            "name": "testArtist",
            "startYear": 1999,
            "endYear": 0,
            "albums": [
                {
                    "name": "testAlbum",
                    "releaseYear": 2020,
                    "genre": "Punk",
                    "description": "Not provided",
                    "stock": {
                        "stock_NY_01": 1,
                        "stock_NY_02": 2,
                        "stock_DC_01": 3,
                        "stock_VA_01": 4,
                        "stock_MD_01": 5
                    }
                }
            ]
        }
    passed 5: post an artist - with album, unique name
     {
        "name": "testArtist2",
        "startYear": 1999,
        "endYear": 0,
        "albums": [
             {
                "name": "testAlbum2",
                "releaseYear": 2020,
                "genre": "Punk",
                "description": "Not provided",
                "stock": {
                }
             }
         ]
     }
    passed 6: post an artist - with album, repeated name
     {
        "name": "testArtist3",
        "startYear": 1999,
        "endYear": 0,
        "albums": [
             {
                "name": "testAlbum2",
                "releaseYear": 2020,
                "genre": "Punk",
                "description": "Not provided",
                "stock": {
                }
             }
         ]
     }
    passed 7: post an artist
     {
         "name": "testArtist4",
         "startYear": 1999,
         "endYear": 0,
         "album": {
         }
     }
    passed 8: put an artist (have to provide the id)
     {
        "id": 23,
        "name": "testArtist",
        "startYear": 1999,
        "endYear": 2020
     }
    passed 9: delete an artist and its children
     DELETE http://localhost:8080/artists/testArtist11pro
     */

}

package com.prisonerprice.controller;

import com.prisonerprice.model.Artist;
import com.prisonerprice.service.ArtistService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Artist> getArtists(){
        List<Artist> artists = artistService.getAllArtists();
        return artists;
    }

    @RequestMapping(value = "/with-children", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Artist> getArtistsWithChildren(){
        return null;
    }
}

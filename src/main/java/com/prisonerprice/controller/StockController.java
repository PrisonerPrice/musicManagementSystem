package com.prisonerprice.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.prisonerprice.model.Album;
import com.prisonerprice.model.Stock;
import com.prisonerprice.service.AlbumService;
import com.prisonerprice.service.ArtistService;
import com.prisonerprice.service.StockService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/stocks"})
public class StockController {

    private ArtistService artistService;
    private AlbumService albumService;
    private StockService stockService;
    private Logger logger;

    @Autowired
    public StockController(ArtistService artistService, AlbumService albumService, StockService stockService, Logger logger){
        this.artistService = artistService;
        this.albumService = albumService;
        this.stockService = stockService;
        this.logger = logger;
    }

    //@JsonView({Stock.Brief.class})
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Stock> getStocks(){
        List<Stock> stocks = stockService.getStockList();
        return stocks;
    }

    //@JsonView({Stock.Brief.class})
    @RequestMapping(value = "/{albumName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Stock getStockByAlbumName(@PathVariable String albumName){
        Stock stock = stockService.getStockByName(albumName);
        return stock;
    }

    //@JsonView({Stock.Brief.class})
    @RequestMapping(value = "/{albumName}/{value1}/{value2}/{value3}/{value4}/{value5}", method = RequestMethod.PATCH, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Stock updateStock(@PathVariable int value1,
                             @PathVariable int value2,
                             @PathVariable int value3,
                             @PathVariable int value4,
                             @PathVariable int value5,
                             @PathVariable String albumName){
        Stock stock = stockService.getStockByName(albumName);
        stock.setStock_NY_01(value1);
        stock.setStock_NY_02(value2);
        stock.setStock_DC_01(value3);
        stock.setStock_VA_01(value4);
        stock.setStock_MD_01(value5);
        return stock;
    }

}

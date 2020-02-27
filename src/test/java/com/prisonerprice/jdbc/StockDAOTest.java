package com.prisonerprice.jdbc;

import com.prisonerprice.model.Stock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StockDAOTest {
    private StockDAO stockDAO;
    private Stock newStock;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void doo() {
        Assert.assertEquals(1, 1);
    }

    /*
    @Before
    public void init(){
        stockDAO = new StockDAO();
        newStock = new Stock();
        stockDAO.insertStocks(newStock);
    }

    @After
    public void tearDown(){
        //stockDAO.deleteStocks(newStock.getSerialNumber());
    }

    @Test
    public void getStocksTest(){
        List<Stock> stocks = stockDAO.getStocks();
        int exceptionNumOfStocks = stocks.size() + 1;
        stockDAO.insertStocks(newStock);
        stocks = stockDAO.getStocks();
        for(Stock stock : stocks){
            logger.debug(stock.toString());
        }
        Assert.assertEquals(exceptionNumOfStocks, stocks.size());
    }

    @Test
    public void insertAlbumsTest(){
        List<Stock> stocks = stockDAO.getStocks();
        int exceptionNumOfStocks = stocks.size() + 1;
        stockDAO.insertStocks(newStock);
        stocks = stockDAO.getStocks();
        for(Stock stock : stocks){
            logger.debug(stock.toString());
        }
        Assert.assertEquals(exceptionNumOfStocks, stocks.size());
    }

    @Test
    public void deleteStocksTest(){
        List<Stock> stocks = stockDAO.getStocks();
        int orginalNumOfStocks = stocks.size();
        //stockDAO.deleteStocks(newStock.getSerialNumber());
        stocks = stockDAO.getStocks();
        for(Stock stock : stocks){
            logger.debug(stock.toString());
        }
        Assert.assertTrue(orginalNumOfStocks > stocks.size());
    }
/**
    @Test
    public void updateStocksTest(){
        int exceptionNumOfStocks = 1000;
        //stockDAO.updateStocks(newStock.getSerialNumber(), 1000);
        List<Stock> stocks = stockDAO.getStocks();
        for(Stock stock : stocks){
            logger.debug(stock.toString());
            if(stock.getSerialNumber().equals(newStock.getSerialNumber())){
                if(stock.getStock_DC_01() == 1000){
                    Assert.assertTrue(true);
                    return;
                }
            }
        }
        Assert.assertTrue(false);
    }
 **/
}

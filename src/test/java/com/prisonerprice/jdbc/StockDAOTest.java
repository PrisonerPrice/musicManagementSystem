package com.prisonerprice.jdbc;

import com.prisonerprice.model.Stock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class StockDAOTest {
    private StockDAO stockDAO;
    private Stock newStock;

    @Before
    public void init(){
        stockDAO = new StockDAO();
        newStock = new Stock(
                "Antisocialities",
                2,
                3,
                1,
                2,
                1);
        stockDAO.insertStocks(newStock);
    }

    @After
    public void tearDown(){
        stockDAO.deleteStocks(newStock.getSerialNumber());
    }

    @Test
    public void getStocksTest(){
        List<Stock> stocks = stockDAO.getStocks();
        int exceptionNumOfStocks = stocks.size() + 1;
        stockDAO.insertStocks(newStock);
        stocks = stockDAO.getStocks();
        for(Stock stock : stocks){
            System.out.println(stock);
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
            System.out.println(stock);
        }
        Assert.assertEquals(exceptionNumOfStocks, stocks.size());
    }

    @Test
    public void deleteStocksTest(){
        List<Stock> stocks = stockDAO.getStocks();
        int orginalNumOfStocks = stocks.size();
        stockDAO.deleteStocks(newStock.getSerialNumber());
        stocks = stockDAO.getStocks();
        for(Stock stock : stocks){
            System.out.println(stock);
        }
        Assert.assertTrue(orginalNumOfStocks > stocks.size());
    }

    @Test
    public void updateStocksTest(){
        int exceptionNumOfStocks = 1000;
        stockDAO.updateStocks(newStock.getSerialNumber(), 1000);
        List<Stock> stocks = stockDAO.getStocks();
        for(Stock stock : stocks){
            System.out.println(stock.getStock_DC_01());
            if(stock.getSerialNumber().equals(newStock.getSerialNumber())){
                if(stock.getStock_DC_01() == 1000){
                    Assert.assertTrue(true);
                    return;
                }
            }
        }
        Assert.assertTrue(false);
    }
}

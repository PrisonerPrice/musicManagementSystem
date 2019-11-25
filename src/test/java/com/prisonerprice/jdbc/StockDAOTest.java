package com.prisonerprice.jdbc;

import com.prisonerprice.model.Stock;
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
        newStock = new Stock("Antisocialities", 2, 3, 1, 2, 1);
    }

    @Test
    public void getStocksTest(){
        List<Stock> stocks = stockDAO.getStocks();
        int exceptionNumOfStocks = 9;

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
        stockDAO.deleteStocks();
        List<Stock> stocks = stockDAO.getStocks();
        int exceptionNumOfStocks = 9;

        for(Stock stock : stocks){
            System.out.println(stock);
        }

        Assert.assertEquals(exceptionNumOfStocks, stocks.size());
    }

    @Test
    public void updateStocksTest(){
        stockDAO.updateStocks();
        List<Stock> stocks = stockDAO.getStocks();

        for(Stock stock : stocks){
            System.out.println(stock);
            int num_DC_01 = stock.getStock_DC_01();
            if(num_DC_01 != 0) {
                Assert.assertTrue(false);
                return;
            }
        }

        Assert.assertTrue(true);
    }

}

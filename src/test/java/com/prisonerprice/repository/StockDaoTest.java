package com.prisonerprice.repository;

import com.prisonerprice.model.Artist;
import com.prisonerprice.model.Stock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StockDaoTest {

    private static StockDaoImpl stockDao;
    private static Stock newStock, newStock2;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void init(){
        stockDao = new StockDaoImpl();
        newStock = new Stock(
                "Antisocialities",
                2,
                3,
                1,
                2,
                1);
        newStock2 = new Stock(newStock);
        stockDao.save(newStock);
    }

    @After
    public void teardown(){
        stockDao.delete(newStock.getSerialNumber());
        stockDao.delete(newStock2.getSerialNumber());
    }

    @Test
    public void getStocks() {
        List<Stock> stocks = stockDao.getStocks();
        for(Stock stock : stocks){
            logger.info(stock.toString());
        }
        int expectedNumOfDept = 1;
        Assert.assertEquals(expectedNumOfDept, stocks.size());
    }

    @Test
    public void updateStocks() {
        newStock2.setStock_DC_01(999);
        int originalStockInDC01 = newStock.getStock_DC_01();
        String serialNumber = newStock.getSerialNumber();
        stockDao.update(newStock2);
        List<Stock> stocks = stockDao.getStocks();
        for(Stock stock : stocks){
            logger.info(stock.toString());
            if(stock.getSerialNumber().equals(serialNumber)){
                Assert.assertTrue(stock.getStock_DC_01() != originalStockInDC01);
                return;
            }
        }
        Assert.assertTrue(false);
    }

    @Test
    public void deleteStocks(){
        stockDao.delete(newStock.getSerialNumber());
        List<Stock> stocks = stockDao.getStocks();
        int expectedNumOfDept = 0;
        Assert.assertEquals(expectedNumOfDept, stocks.size());
    }
}

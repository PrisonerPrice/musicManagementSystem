package com.prisonerprice.service;

import com.prisonerprice.model.Stock;
import com.prisonerprice.repository.StockDaoImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class StockService {
    private Logger logger;
    private StockDaoImpl stockDao;

    @Autowired
    public StockService(Logger logger, StockDaoImpl stockDao){
        this.logger = logger;
        this.stockDao = stockDao;
    }

    public boolean save(Stock stock){
        return stockDao.save(stock);
    }

    public boolean update(Stock stock){
        return stockDao.update(stock);
    }

    public boolean deleteByName(String name){
        return stockDao.deleteStockByName(name);
    }

    public Stock getStockByName(String name){
        return stockDao.getStockByName(name);
    }

    public List<Stock> getAllStocks(){
        return stockDao.getStocks();
    }
}

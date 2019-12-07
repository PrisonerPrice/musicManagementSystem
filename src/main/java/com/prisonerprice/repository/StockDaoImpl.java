package com.prisonerprice.repository;

import com.prisonerprice.model.Album;
import com.prisonerprice.model.Artist;
import com.prisonerprice.model.Stock;
import com.prisonerprice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StockDaoImpl implements StockDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Connection<Stock> stockConnection = new Connection<>();

    @Override
    public boolean save(Stock stock) {
        return stockConnection.save(stock);
    }

    @Override
    public boolean update(Stock stock) {
        return stockConnection.update(stock);
    }

    @Override
    public boolean delete(Stock stock) {
        return stockConnection.delete(stock);
    }

    public boolean deleteStockByName(String stockName){
        return delete(getStockByName(stockName));
    }

    @Override
    public List<Stock> getStocks() {
        return stockConnection.getObjectList("Stock");
    }

    public Stock getStockByName(String albumName){
        String hql = "FROM Stock as stock " +
                "where lower(stock.album.name) = :param";
        return stockConnection.getObjectByName(hql, albumName);
    }

}

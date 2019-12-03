package com.prisonerprice.repository;

import com.prisonerprice.model.Stock;

import java.util.List;

public interface StockDao {
    boolean save(Stock stock);
    boolean update(Stock stock);
    boolean delete(String stockNumber);
    List<Stock> getStocks();
}

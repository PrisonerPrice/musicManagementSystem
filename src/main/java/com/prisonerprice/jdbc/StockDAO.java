package com.prisonerprice.jdbc;

import com.prisonerprice.model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private MyConnection myConnection = new MyConnection();
    private ResultSet rs = null;

    public List<Stock> getStocks(){
        logger.info("Enter the method getStocks");
        List<Stock> stocks = new ArrayList<>();

        try {
            rs = myConnection.connectAndFetchResult("SELECT * FROM stock");

            while(rs.next()){
                stocks.add(new Stock(
                        rs.getString("album_name"),
                        rs.getInt("stock_NY_01"),
                        rs.getInt("stock_NY_02"),
                        rs.getInt("stock_DC_01"),
                        rs.getInt("stock_VA_01"),
                        rs.getInt("stock_MD_01")
                ));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                myConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        logger.info("Exit the method getStocks");
        return stocks;
    }

    public void insertStocks(Stock stock){
        logger.info("Enter the method insertStocks");
        String sqlQuery = "INSERT INTO stock (album_name, stock_NY_01, stock_NY_02, stock_DC_01, stock_VA_01, stock_MD_01) " +
                "VALUES (" +
                "'" + stock.getAlbum_name() + "', " +
                stock.getStock_NY_01() + ", " +
                stock.getStock_NY_02() + ", " +
                stock.getStock_DC_01() + ", " +
                stock.getStock_VA_01() + ", " +
                stock.getStock_MD_01() + ");";
        doAQuery(sqlQuery);
    }

    public void deleteStocks(){
        logger.info("Enter the method deleteStocks");
        String sqlQuery = "DELETE FROM stock WHERE id > 9";
        doAQuery(sqlQuery);
    }

    public void updateStocks(){
        logger.info("Enter the method updateStocks");
        String sqlQuery = "UPDATE stock SET stock_DC_01 = 0;";
        doAQuery(sqlQuery);
    }

    public void doAQuery(String sqlQuery){
        try {
            myConnection.connectAndFetchResult(sqlQuery);
        } catch (SQLException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                myConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.prisonerprice.jdbc;

import com.prisonerprice.model.CombineTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CombineTables {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private MyConnection myConnection = new MyConnection();
    private ResultSet rs = null;

    public List<CombineTable> getCombineTables(){
        logger.info("Enter the method getCombineTables");
        List<CombineTable> combineTables = new ArrayList<>();

        try {
            rs = myConnection.connectAndFetchResult(
                    "SELECT ALBUM.ID AS ID, ALBUM.NAME AS NAME, RELEASE_YEAR, ARTIST, GENRE, ALBUM.DESCRIPTION AS ALBUM_DESC, " +
                    "START_YEAR, END_YEAR, ARTIST.DESCRIPTION AS ARTIST_DESC, " +
                    "STOCK_NY_01 AS NY_01, STOCK_NY_02 AS NY_02, STOCK_DC_01 AS DC_01, STOCK_VA_01 AS VA_01, STOCK_MD_01 AS MD_01 " +
                    "FROM ALBUM " +
                    "LEFT JOIN ARTIST ON ALBUM.ARTIST = ARTIST.NAME " +
                    "INNER JOIN STOCK ON ALBUM.NAME = STOCK.ALBUM_NAME " +
                    "ORDER BY NAME;");

            while(rs.next()){
                combineTables.add(new CombineTable(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("release_year"),
                        rs.getString("artist"),
                        rs.getString("genre"),
                        rs.getString("album_desc"),
                        rs.getInt("start_year"),
                        rs.getInt("end_year"),
                        rs.getString("artist_desc"),
                        rs.getInt("ny_01"),
                        rs.getInt("ny_02"),
                        rs.getInt("dc_01"),
                        rs.getInt("va_01"),
                        rs.getInt("md_01")
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

        logger.info("Exit the method getAlbums");
        return combineTables;
    }
}

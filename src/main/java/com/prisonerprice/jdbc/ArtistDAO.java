package com.prisonerprice.jdbc;

import com.prisonerprice.model.Artist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private MyConnection myConnection = new MyConnection();
    private ResultSet rs = null;

    public List<Artist> getArtists(){
        logger.info("Enter the method getArtists");
        List<Artist> artists = new ArrayList<>();
        try {
            rs = myConnection.connectAndFetchResult("SELECT * FROM artist");
            while(rs.next()){
                artists.add(new Artist(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("start_year"),
                        rs.getInt("end_year"),
                        rs.getString("description"),
                        rs.getString("serial_num")
                ));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                myConnection.closeConnection();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        logger.info("Exit the method getArtists");
        return artists;
    }

    public void insertArtists(Artist artist){
        logger.info("Enter the method insertArtists");
        String sqlQuery = "INSERT INTO artist (name, start_year, end_year, description, serial_num) " +
                "VALUES (" +
                "'" + artist.getName() + "', " +
                artist.getStartYear() + ", " +
                artist.getEndYear() + ", " +
                "'" + artist.getDescription() + "', " +
                "'" + artist.getSerialNumber() + "');";
        doAQuery(sqlQuery);
    }

    public void deleteArtists(String serialNumber){
        logger.info("Enter the method deleteArtists");
        String sqlQuery = "DELETE FROM artist WHERE serial_num = '" + serialNumber + "';";
        doAQuery(sqlQuery);
    }

    public void updateArtists(String serialNumber, String desc){
        logger.info("Enter the method updateArtists");
        String sqlQuery = "UPDATE artist SET description = '" + desc + "' " +
                "WHERE serial_num = '" + serialNumber + "';";
        doAQuery(sqlQuery);
    }

    public void doAQuery(String sqlQuery){
        try {
            myConnection.connectAndFetchResult(sqlQuery);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                myConnection.closeConnection();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

package com.prisonerprice.jdbc;

import com.prisonerprice.model.Album;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private MyConnection myConnection = new MyConnection();
    private ResultSet rs = null;

    public List<Album> getAlbums(){
        logger.info("Enter the method getAlbums");
        List<Album> albums = new ArrayList<>();
        try {
            rs = myConnection.connectAndFetchResult("SELECT * FROM album;");
            while(rs.next()){
                albums.add(new Album(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("release_year"),
                        rs.getString("artist"),
                        rs.getString("genre"),
                        rs.getString("description"),
                        rs.getInt("serial_num")
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
        return albums;
    }

    public void insertAlbums(Album album){
        logger.info("Enter the method insertAlbums");
        String sqlQuery = "INSERT INTO album (name, release_year, artist, genre, description, serial_num) " +
                "VALUES " +
                "('" + album.getName() + "', " +
                album.getRelease_year() + ", " +
                "'" + album.getArtist() + "', " +
                "'" + album.getGenre() + "', " +
                "'" + album.getDescription() + "', " +
                album.getSerial_num() + ");";
        doAQuery(sqlQuery);
    }

    public void deleteAlbums(int serial_num){
        logger.info("Enter the method deleteAlbums");
        String sqlQuery = "DELETE FROM album WHERE serial_num = " + serial_num + ";";
        doAQuery(sqlQuery);
    }

    public void updateAlbums(int serial_num, String desc){
        logger.info("Enter the method updateAlbums");
        String sqlQuery = "UPDATE album SET description = '" + desc + "' " +
                "WHERE serial_num = " + serial_num + ";";
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

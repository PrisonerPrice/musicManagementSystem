package com.prisonerprice.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class MyConnection {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/music_db";
    private static final String USER = "admin";
    private static final String PASS = "1234";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public ResultSet connectAndFetchResult(String sqlQuery) throws SQLException {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sqlQuery);
        return rs;
    }

    public void closeConnection() throws SQLException {
        if(rs != null) rs.close();
        if(stmt != null) stmt.close();
        if(conn != null) conn.close();
    }

}

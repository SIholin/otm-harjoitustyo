/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanakirja.dao;

/**
 *
 * @author ihqsanna
 */
import java.sql.*;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) {
        this.databaseAddress = databaseAddress;

    }

    public Connection getConnection() throws SQLException {
        try{
            return DriverManager.getConnection(databaseAddress);
        } catch (SQLException ex){
            System.out.println(ex.toString());
            return null;
        }
    }

}

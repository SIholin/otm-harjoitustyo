package sanakirja.dao;

import java.sql.*;

/**
 * Luokka joka huolehtii tietokannan osoitteen löytämisestä.
 */
public class Database {

    private String databaseAddress;

    /**
     * Ottaa vastaan tietokannan osoitteen.
     *
     * @param databaseAddress tietokannan löytymisosoite.
     */
    public Database(String databaseAddress) {
        this.databaseAddress = databaseAddress;

    }

    /**
     * Luo DriverManager yhteyden tietokantaan.
     *
     * @return yhteys tai null jos yhteys epäonnistuu.
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(databaseAddress);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

}

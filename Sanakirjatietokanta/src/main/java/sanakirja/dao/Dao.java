package sanakirja.dao;

import java.sql.*;
import java.util.*;

/**
 * Rajapinta jonka sovelluksessa olevat UserDao ja WordDao toteuttavat, joka
 * päättää mitä tietokanta toiminnallisuuksia oliot tarvitsevat.
 *
 * @param <T> Olio esim tässä tapauksessa Word tai User.
 * @param <K> Uniikki id.
 */
public interface Dao<T, K> {
    /**
     * Etsii halutun olion.
     * @param key
     * @return
     * @throws SQLException 
     */
    T findOne(K key) throws SQLException;

    List<T> findAll() throws SQLException;

    T saveOrUpdate(T object) throws SQLException;

    void delete(K key) throws SQLException;
}

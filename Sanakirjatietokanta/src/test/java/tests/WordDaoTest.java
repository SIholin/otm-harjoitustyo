/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import sanakirja.dao.Database;
import sanakirja.dao.UserDao;
import sanakirja.dao.WordDao;
import sanakirja.domain.Word;

/**
 *
 * @author ihqsanna
 */
public class WordDaoTest {

    Database database;
    WordDao wordDao;
    Connection connection;
    Word word;

    @Before
    public void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:test.db");

        PreparedStatement stmt = connection.prepareStatement("SELECT 1");
        ResultSet resultSet = stmt.executeQuery();

        database = new Database("jdbc:sqlite:test.db");

        wordDao = new WordDao(database);
        word = new Word(null, "kissa", "cat");
        wordDao.saveOrUpdate(word);
    }

    @Test
    public void saveWorks() throws SQLException {

        assertTrue(word.getForm().equals("kissa"));
    }

    @Test
    public void findAllWorks() throws SQLException {

        Word word2 = new Word(null, "koira", "dog");
        wordDao.saveOrUpdate(word2);

        assertTrue(wordDao.findAll().size() == 2);
    }

    @Test
    public void findOneWorks() throws SQLException {
        assertTrue(wordDao.findOne(1).form.equals("kissa"));
    }

    @After
    public void clean() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Word");
        stmt.executeUpdate();
    }
}

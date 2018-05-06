/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanakirja.dao;

import sanakirja.domain.User;
import sanakirja.domain.Word;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ihqsanna
 */
public class WordDao implements Dao<Word, Integer> {

    private Database database;

    public WordDao(Database db) {
        this.database = db;
    }

    @Override
    public Word findOne(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Word WHERE id = ?");
        stmt.setInt(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Word word = new Word(rs.getInt("id"), rs.getString("form"), rs.getString("translation"));

        stmt.close();
        rs.close();

        conn.close();
        return word;
    }

    @Override
    public ArrayList<Word> findAll() throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Word");
        ArrayList<Word> words = new ArrayList();

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Word word = new Word(rs.getInt("id"), rs.getString("form"), rs.getString("translation"));
            words.add(word);
        }

        stmt.close();
        rs.close();

        conn.close();

        return words;
    }

    @Override
    public Word saveOrUpdate(Word object) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Word" + "(form, translation)" + "VALUES (?, ?)");

        stmt.setString(1, object.getForm());
        stmt.setString(2, object.getTransaltion());
        stmt.executeUpdate();
        stmt.close();

        stmt = conn.prepareStatement("SELECT * FROM Word WHERE form = ? AND translation = ?");
        stmt.setString(1, object.getForm());
        stmt.setString(2, object.getTransaltion());

        ResultSet rs = stmt.executeQuery();
        rs.next();

        Word word = new Word(rs.getInt("id"), rs.getString("form"), rs.getString("translation"));

        stmt.close();
        rs.close();
        conn.close();
        return word;
    }

    @Override
    public void delete(Integer key) throws SQLException {

    }
}

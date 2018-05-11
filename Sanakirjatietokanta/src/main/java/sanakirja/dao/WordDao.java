package sanakirja.dao;

import sanakirja.domain.Word;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Sanojen yhdistämisestä tieokantaan huolehtiva Dao luokka, jonka rajapintana
 * on Dao.
 *
 */
public class WordDao implements Dao<Word, Integer> {

    private Database database;

    /**
     * Ottaa databasen käyttöönsä.
     *
     * @param db tietokanta.
     */
    public WordDao(Database db) {
        this.database = db;
    }

    /**
     * Etsii sanojen joukosta tietyn id:n omaavan sanan.
     *
     * @param key etstittävän sanan id.
     * @return Etsitty sana tai null jos sitä ei löydy.
     * @throws SQLException
     */
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

    /**
     * Hakee kaikki sanat ja tekee niistä ArrayListan.
     *
     * @return Kaikkien sanojen lista.
     * @throws SQLException
     */
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

    /**
     * Lisää sanakirja tietokantaan uuden sanan.
     *
     * @param object Lisättävä sana.
     * @return Lisätty sana.
     * @throws SQLException
     */
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

    /**
     * Metodin tarkoituksena on poistaa sana tietokannasta mutta sen touteutsta
     * ei vielä tässä vaiheessa ole tarpeen tehdä.
     *
     * @param key poistettavan sanan uniikki id.
     * @throws SQLException
     */
    @Override
    public void delete(Integer key) throws SQLException {

    }
}

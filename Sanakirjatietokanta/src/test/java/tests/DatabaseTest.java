package tests;

import sanakirja.dao.Database;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testaa tietokannan toimivuuden.
 */
public class DatabaseTest {

    Database database;

    /**
     * Antaa tietokannalle ositteen.
     */
    @Before
    public void setUp() {
        database = new Database("jdbc:sqlite:Sanakirja.db");

    }

    /**
     * Testaa toimiiko tietokantayhteys.
     *
     * @throws SQLException
     */
    @Test
    public void getConnectionWorks() throws SQLException {
        Boolean success = null;
        try {
            Connection conn = database.getConnection();
            success = true;
        } catch (SQLException ex) {
            success = false;

        }

        assertTrue(success);
    }

    /**
     * Testaa että haku epäaitoon osoitteeseen epäonnistuu.
     *
     * @throws SQLException
     */
    @Test
    public void connectionWithStrangeAddress() throws SQLException {

        Database db = new Database("wrong");
        Connection conn = db.getConnection();

        assertNull(conn);
    }

}

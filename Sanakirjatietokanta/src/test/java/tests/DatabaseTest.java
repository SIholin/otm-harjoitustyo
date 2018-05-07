package tests;

import sanakirja.dao.Database;
import sanakirja.dao.UserDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseTest {

    UserDao userdao;
    Database database;

    @Before
    public void setUp() {
        database = new Database("jdbc:sqlite:Sanakirja.db");
        userdao = new UserDao(database);
    }
    
    @Test
    public void getConnectionWorks() throws SQLException {
        Boolean success = null;
        try {
        Connection conn = database.getConnection();
        success = true;
        } catch (SQLException ex){ 
            success = false;
            
        }
        
        assertTrue(success);
    }
 
}

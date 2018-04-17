
import Sanakirja.Dao.Database;
import Sanakirja.Dao.UserDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;



public class DatabaseTest {
    UserDao userdao;
    
//    @Before
//    public void setUp() {
//        database = new Database("jdbc:sqlite:Sanakirja.db");
//    }
//    
//    @Test
//    public void getConnectionWorks() throws SQLException {
//        Connection conn = database.getConnection();
//        Connection con = DriverManager.getConnection("jdbc:sqlite:Sanakirja.db");
//        
//        assertTrue(conn.getSchema().equals(con.getSchema()));
//    }
//    
}

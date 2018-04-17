package Tests;

import Sanakirja.Dao.Database;
import Sanakirja.Dao.UserDao;
import Sanakirja.Domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {

    Database database;
    UserDao userDao;
    Connection connection;

    @Before
    public void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:test.db");

        PreparedStatement stmt = connection.prepareStatement("SELECT 1");
        ResultSet resultSet = stmt.executeQuery();

        database = new Database("jdbc:sqlite:test.db");

        userDao = new UserDao(database);
    }

    @Test
    public void saveWorks() throws SQLException {

        User user = new User(null, "lava");
        assertTrue(userDao.save(user).getUsername().equals(user.getUsername()));
    }

    @Test
    public void findAllWorks() throws SQLException {
        userDao.save(new User(null, "lava"));
        ArrayList<User> users = userDao.findAll();
        assertTrue(users.size() == 1);
    }

    @Test
    public void deleteWorks() throws SQLException {
        User user = new User(null, "lava");
        userDao.save(user);
        userDao.save(new User(2, "kjarkko"));
        userDao.delete(1);
        assertNull(userDao.findOne(1));
    }

    @Test
    public void findOneWorks() throws SQLException {
        User user = new User(null, "lava");
        userDao.save(user);

        User user1 = userDao.findOne(1);
        assertNotNull(user1);
        assertTrue(user1.getUsername().equals(user.getUsername()));
    }

    @After
    public void clean() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM User");
        stmt.executeUpdate();
    }

}

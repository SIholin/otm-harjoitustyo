package tests;

import sanakirja.dao.Database;
import sanakirja.dao.UserDao;
import sanakirja.domain.User;
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

        User user = new User(null, "lava", "kukka", 10, "", 3);
        assertTrue(userDao.saveOrUpdate(user).getUsername().equals(user.getUsername()));
    }

    @Test
    public void findAllWorks() throws SQLException {
        userDao.saveOrUpdate(new User(null, "lava", "kukka", 10, "", 3));
        ArrayList<User> users = userDao.findAll();
        assertTrue(users.size() == 1);
    }

    @Test
    public void deleteWorks() throws SQLException {
        User user = new User(null, "lava", "kakku", 10, "", 3);
        userDao.saveOrUpdate(user);
        userDao.saveOrUpdate(new User(2, "kjarkko", "kukka", 5, "", 3));
        userDao.delete(2);
        assertNull(userDao.findOne(2));
    }

    @Test
    public void findOneWorks() throws SQLException {
        User user = new User(null, "lava", "kakku", 10, "", 3);
        userDao.saveOrUpdate(user);

        assertTrue(userDao.findOne(1).getUsername().equals("lava"));
    }

    @After
    public void clean() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM User");
        stmt.executeUpdate();
    }

}

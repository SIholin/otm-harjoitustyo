/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanakirja.dao;

/**
 *
 * @author ihqsanna
 */
import sanakirja.domain.User;
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
public class UserDao implements Dao<User, Integer> {

    private Database database;

    public UserDao(Database database) {
        this.database = database;
    }

    @Override
    public User findOne(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE id = ?");
        stmt.setInt(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        User user = new User(
                rs.getInt("id"), rs.getString("username"),
                rs.getString("password"), rs.getInt("failNumber"), 
                rs.getString("fails"), rs.getInt("allAttempts")
        );

        stmt.close();
        rs.close();

        conn.close();
        return user;

    }

    @Override
    public ArrayList<User> findAll() throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User");
        ArrayList<User> users = new ArrayList();

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            User user = new User(
                    rs.getInt("id"), rs.getString("username"), 
                    rs.getString("password"), rs.getInt("failNumber"), 
                    rs.getString("fails"), rs.getInt("allAttempts")
            );
            users.add(user);
        }

        stmt.close();
        rs.close();

        conn.close();

        return users;

    }

    @Override
    public User saveOrUpdate(User object) throws SQLException {
        if (object.getId() == null) {
            return save(object);
        } else {
            return update(object);
        }

    }

    private User save(User user) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO User" + "(username, password, failNumber, fails, allAttempts)" + "VALUES (?, ?, ?, ?, ?)");

        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setInt(3, user.getFailNumber());
        stmt.setString(4, user.getFails());
        stmt.setInt(5, user.getAllAttempts());
        stmt.executeUpdate();
        stmt.close();

        stmt = conn.prepareStatement("SELECT * FROM User WHERE username = ? AND password = ? AND failNumber = ? AND fails = ? AND allAttempts = ?");
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setInt(3, user.getFailNumber());
        stmt.setString(4, user.getFails());
        stmt.setInt(5, user.getAllAttempts());

        ResultSet rs = stmt.executeQuery();
        rs.next();

        User newuser = new User(
                rs.getInt("id"), rs.getString("username"), 
                rs.getString("password"), rs.getInt("failNumber"), 
                rs.getString("fails"), rs.getInt("allAttempts")
        );

        stmt.close();
        rs.close();
        conn.close();
        return newuser;
    }

    private User update(User user) throws SQLException {
        Connection conn = database.getConnection();

        PreparedStatement stmt = conn.prepareStatement("UPDATE User SET"
                + " username = ?, password = ?, failNumber = ?, fails = ?, allAttempts = ? WHERE id = ?");

        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setInt(3, user.getFailNumber());
        stmt.setString(4, user.getFails());
        stmt.setInt(5, user.getAllAttempts());
        stmt.setInt(6, user.getId());

        stmt.executeUpdate();

        stmt.close();
        conn.close();

        return user;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM User WHERE id = ?");

        stmt.setInt(1, key);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

}

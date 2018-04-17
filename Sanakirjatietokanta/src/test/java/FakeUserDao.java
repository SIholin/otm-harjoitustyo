
import Sanakirja.Dao.Dao;
import Sanakirja.Dao.UserDao;
import Sanakirja.Domain.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ihqsanna
 */
public class FakeUserDao implements Dao<User, Integer>{
     List<User> users = new ArrayList<>();

    public FakeUserDao() {
        users.add(new User(100, "kjarkko"));
        users.add(new User(10, "ihqsanna"));
    }

   

    @Override
    public List findAll() throws SQLException {
       return users;
    }

    @Override
    public User findOne(Integer key) throws SQLException {
       for (int i = 0; i < users.size(); i ++) {
            if (key.equals(users.get(i).getId())) {
                return users.get(i);
            }
        }
        return null;
    }

    @Override
    public User save(User object) throws SQLException {
        users.add(object);
        return object;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        for (int i = 0; i < users.size(); i ++) {
           if (key.equals(users.get(i).getUsername())) {
               users.remove(i);
           }
       }
    }
    
   
}

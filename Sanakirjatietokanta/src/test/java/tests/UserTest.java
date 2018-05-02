package tests;

import sanakirja.domain.User;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

    User user;

    @Before
    public void setUp() {
        user = new User(1, "ihqsanna", "ruoka", 0, "", 3);
    }

    @Test
    public void userExsists() {
        assertTrue(user != null);
    }
    
    @Test
    public void idIsRight() {
        assertTrue(user.getId() == 1);
    }

    @Test
    public void rightUser() {
        assertTrue(user.getUsername().equals("ihqsanna"));
    }

    @Test
    public void equalswWorks() {
        User user2 = new User(2, "palleroinen", "herkku", 0, "", 3);
        User user3 = new User(3, "ihqsanna", "ruoka", 0, "", 3);
        
        assertFalse(user.equals("kukka"));
        assertTrue(user.equals(user3));
        assertFalse(user.equals(user2));
        assertTrue(user.equals(user));

    }
    @Test
    public void setFailNumberWorks() {
        user.setFailNumber(10);
        assertTrue(user.getFailNumber() == 10);
    }

    @Test
    public void setAllAttemptsWorks() {
        user.setAllAttempts(11);
        assertTrue(user.getAllAttempts() == 11);
    }
}

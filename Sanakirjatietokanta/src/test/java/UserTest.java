import Sanakirja.Domain.User;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;



public class UserTest {
    
    User user;
    
    @Before
    public void setUp() {
        user = new User(1, "ihqsanna");
    }
    
    @Test
    public void kayttajaOlemassa(){
        assertTrue(user != null);
    }
    
    @Test
    public void oikeaKayttaja(){
        assertTrue(user.getUsername().equals("ihqsanna"));
    }
    
    @Test
    public void equalsToimii() {
        User user2 = new User(2, "palleroinen");
        User user3 = new User(3, "ihqsanna");
        
        assertTrue(user.equals(user3));
        assertFalse(user.equals(user2));
        assertTrue(user.equals(user));
        
    }
    
    
}

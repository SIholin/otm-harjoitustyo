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
        assertTrue(user!=null);
    }
    
    
}

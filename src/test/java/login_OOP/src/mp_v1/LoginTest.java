package login_OOP.src.mp_v1;

import org.junit.Assert;
import org.junit.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

public class LoginTest {

    //To check for exceptions being thrown
   /* @Test//(expected = SQLException.class)
    public void checkException() throws SQLException {
        List<String> loginDetails = new ArrayList<String>();
        loginDetails.add("gmail.com");
        loginDetails.add("1");
        Login.checkLogin(loginDetails);
    }*/

   /*//Testing private function
    @Test
    public void testCheckPasswordTrue() throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {

        Method method = Login.class.getDeclaredMethod("checkPassword", ResultSet.class, String.class);
        method.setAccessible(true);
        Login login = new Login();
        ResultSet rs = Login.makeConnection("a@gmail.com");
        boolean result = false;
        if (rs.next())
        {
            result = (boolean) method.invoke(login, rs, "123");
        }
        assertEquals(true, result);
    }

    @Test
    public void testCheckPasswordFalse() throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {

        Method method = Login.class.getDeclaredMethod("checkPassword", ResultSet.class, String.class);
        method.setAccessible(true);
        Login login = new Login();
        ResultSet rs = Login.makeConnection("a@gmail.com");
        boolean result = false;
        if (rs.next())
        {
            result = (boolean) method.invoke(login, rs, "1");
        }
        assertEquals(false, result);
    }

    @Test
    public void testCheckPasswordEmailFalse() throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {

        Method method = Login.class.getDeclaredMethod("checkPassword", ResultSet.class, String.class);
        method.setAccessible(true);
        Login login = new Login();
        ResultSet rs = Login.makeConnection("gmail.com");
        boolean result = false;
        if (rs.next())
        {
            result = (boolean) method.invoke(login, rs, "123");
        }
        assertEquals(false, result);
    }*/

    @Test
    public void makeConnection() throws SQLException {
        assertEquals(true, Login.makeConnection("a@gmail.com") instanceof ResultSet);
    }
}
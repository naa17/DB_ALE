package login;

import login.Login;
import login.Verification;
import login.verificationDAO;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class verificationDAOTest {

    //Testing private function
    @Test
    public void testCreateVerification() throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {

        Method method = verificationDAO.class.getDeclaredMethod("createVerification", ResultSet.class);
        method.setAccessible(true);
        verificationDAO verif = new verificationDAO();
        ResultSet rs = Login.makeConnection("a@gmail.com");
        boolean result = false;
        if (rs.next())
        {
            assertEquals(true, (Verification)method.invoke(verif, rs) instanceof Verification);
        }
    }

    @Test
    public void getDetailsForEmail()
    {
        assertEquals(true, verificationDAO.getDetailsForEmail("a@gmail.com") instanceof List);
    }
}
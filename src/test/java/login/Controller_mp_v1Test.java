package login;

import login.Controller_mp_v1;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class Controller_mp_v1Test {

    //Testing private function
    @Test
    public void testMakeList() throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {

        Method method = Controller_mp_v1.class.getDeclaredMethod("MakeList", String.class, String.class);
        method.setAccessible(true);
        Controller_mp_v1 c = new Controller_mp_v1();
        List result = (List) method.invoke(c, "a@gmail.com", "123");

        assertEquals(true, result instanceof List);
    }


}
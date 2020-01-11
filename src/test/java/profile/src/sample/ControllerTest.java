package profile.src.sample;

import org.junit.Test;
import static org.junit.Assert.*;


public class ControllerTest {

    Controller ctrl = new Controller();

    @Test
    public void TestisNullOrEmptyTrue(){
        String str = null;
        assertEquals(true, ctrl.isNullOrEmpty(str));
    }

    @Test
    public void TestisNullOrEmptyFalse(){
        String str = "test";
        assertEquals(false, ctrl.isNullOrEmpty(str));
    }



}

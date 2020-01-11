package registrationFX.src.sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class Registration_ControllerTest {

    Registration_Controller rgc = new Registration_Controller();

    @Test
    public void testIsValidEmailAddressTrue() throws Exception {
        System.out.println("Test2");
        assertEquals(true, rgc.isValidEmailAddress("krithika.balaji@18gmail.com"));
    }

    @Test
    public void testIsValidEmailAddressFalse() throws Exception {
        System.out.println("Test1");
        assertEquals(false, rgc.isValidEmailAddress("krithika.balaji18@gmail"));
    }


}
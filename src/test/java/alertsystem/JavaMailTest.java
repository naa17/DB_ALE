
package alertsystem;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class JavaMailTest {

    //Testing with assertions
    //Checks to see if it is able to spot a valid email address or not - this test should pass as email address is invalid

/*   @Test
    public void testIsValidEmailAddressFalse() throws Exception {
        System.out.println("Test1");
        assertEquals(false, JavaMail.isValidEmailAddress("krithika.balaji18@gmail"));
    }*//*


    //Checks to see if it is able to spot a valid email address or not - this test should pass as email address is valid
 */
/*   @Test
    public void testIsValidEmailAddressTrue() throws Exception {
        System.out.println("Test2");
        assertEquals(true, JavaMail.isValidEmailAddress("krithika.balaji@18gmail.com"));
    }*/

    //This checks to see if values are properly being extracted from the database - look at ways to add and remove a table value - for better unit testing
    @Test
    public void testCheckValuesTrue() throws Exception {
        ArrayList<Double> glucoseList = new ArrayList<>();
        glucoseList.add((double) 10);
        glucoseList.add((double) 12);
        glucoseList.add((double) 90);
        assertEquals(true, JavaMail.checkValues(glucoseList));
    }

    @Test
    public void testCheckValuesFalse() throws Exception {
        ArrayList<Double> glucoseList = new ArrayList<>();
        glucoseList.add((double) 10);
        glucoseList.add((double) 12);
        assertEquals(false, JavaMail.checkValues(glucoseList));
    }

    //The below tests seem like integration testing...
    /*//The sendEmail function shouldn't run as email address is invalid
    @Test
    public void testSendEmailFalse() throws Exception {
        System.out.println("Test4");
        //Initializing parameters
        String recipient = "krithika.balaji17@imperial";
        String recipient_name = "Krithika";
        String str = "SELECT postbglu from testvalues WHERE id = (SELECT MAX(id) FROM testvalues)";

        assertEquals(false, JavaMail.sendEmail(recipient, recipient_name, str));
    }

    //This function should run
    @Test
    public void testSendEmailTrue() throws Exception {
        System.out.println("Test5");
        //Initializing parameters
        String recipient = "krithika.balaji17@imperial.ac.uk";
        String recipient_name = "Krithika";
        String str = "SELECT postbglu from testvalues WHERE id = (SELECT MAX(id) FROM testvalues)";

        assertEquals(true, JavaMail.sendEmail(recipient, recipient_name, str));
    }*/
}


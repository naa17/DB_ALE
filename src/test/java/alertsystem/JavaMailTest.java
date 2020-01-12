package alertsystem;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class JavaMailTest {

    ArrayList<Double> gl = null;
    Connection conn = null;
    JavaMail javamail = new JavaMail();


    //This checks to see if values are properly being extracted from the database - look at ways to add and remove a table value - for better unit testing
    @Test
    public void testCheckValuesTrue() {
        gl = new ArrayList<>();

        gl.add((double) 99999);
        gl.add((double) 171);
        gl.add((double) 200);
        assertEquals(true, javamail.checkValues(gl));
        javamail = null;
    }


    @Test
    public void testCheckValuesFalse() {

        gl = new ArrayList<>();

        gl.add((double) 121);
        gl.add((double) 122);

        assertEquals(false, javamail.checkValues(gl));
        gl = null;
        javamail = null;
    }



    @Test
    public void testExtractGlucoseListTrue() throws SQLException {
        String getGlu = "SELECT glucose from krithikalogbook WHERE timesofday LIKE 'po%'";

        assertEquals(1, javamail.extractGlucoseList(getGlu).size());
    }

    //Exception testing for the database
    @Test(expected = SQLException.class)
    public void testExtractGlucoseListExc() throws SQLException {
        String getGlu = "SELECT glucose from ithikalogbook WHERE timesofday LIKE 'po%'";

        javamail.extractGlucoseList(getGlu);
    }

    @Test(expected = SQLException.class)
    public void testExtractDoctorTrueExc() throws SQLException {
        String getDoc = "SELECT doctorcontact, doctorname from krithikaregister WHERE email like k@gmail.com";

        javamail.extractDoctor(getDoc);
    }

    //Exception testing for the database
    @Test()
    public void testExtractDoctorTrue() throws SQLException {
        String getDoc = "SELECT doctorcontact, doctorname from krithikaregister WHERE email like 'k@gmail.com'";

        assertEquals(2, javamail.extractDoctor(getDoc).size());
    }

    //Testing with assertions
    //Checks to see if it is able to spot a valid email address or not - this test should pass as email address is invalid


/*   @Test
    public void testIsValidEmailAddressFalse() throws Exception {
        System.out.println("Test1");
        assertEquals(false, JavaMail.isValidEmailAddress("krithika.balaji18@gmail"));
    }*//*
*/
/*


    //Checks to see if it is able to spot a valid email address or not - this test should pass as email address is valid
 *//*

*/
/*   @Test
    public void testIsValidEmailAddressTrue() throws Exception {
        System.out.println("Test2");
        assertEquals(true, JavaMail.isValidEmailAddress("krithika.balaji@18gmail.com"));
    }*//*


    //The below tests seem like integration testing... there are multiple functions being called
    */
/*//*
/The sendEmail function shouldn't run as email address is invalid
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



package alertsystem;

import org.junit.Before;
import org.junit.Test;

import javax.mail.Message;
import javax.mail.Session;

import java.util.Properties;

import static org.junit.Assert.*;

public class EmailSenderTest {

    private Session sessi;

    //This test checks the return type of the function
    @Test
    public void doPasswordAuthenticationTest()
    {
        String myAccountEmail = "krithika.balaji18@gmail.com";
        String password = "!gr@du@t3d!!";
        assertEquals(true, EmailSender.doPasswordAuthentication(myAccountEmail, password) instanceof Session);
    }

    @Before
    public void initObject()
    {
        Properties props = new Properties();
        sessi = Session.getInstance(props);
    }

    @Test
    public void prepareMessageTest()
    {
        String myAccountEmail = "krithika.balaji18@gmail.com";
        String recipient = "krithika.balaji17@imperial.ac.uk";
        String recipient_name = "Krithika";
        String patient_name = "NotK";
        assertEquals(true, EmailSender.prepareMessage(sessi, myAccountEmail, recipient, recipient_name, patient_name) instanceof Message);
    }
}


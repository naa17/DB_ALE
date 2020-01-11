
package alertsystem;

import org.junit.Test;

import javax.mail.Session;

import java.sql.Connection;

import static org.junit.Assert.*;

public class ConnectionFactoryTest {

    //There isn't total test coverage because, in order to test for an exception to be thrown so that the return
    //type is null, the machine must have a connection problem that can't be simulated. While the URI
    //Could be sent as input into the function each time it is called to check for the exception, it will be
    //inefficient to have the same chunk of text being inputted into the same function each time it is called.
    //Hence, it was decided not to test the exception.

    //This test checks if the connection is made properly
    @Test
    public void getConnection() {
        assertEquals(true, ConnectionFactory.getConnection() instanceof Connection);
    }
}
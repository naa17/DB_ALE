package comprehensive_logbook.src.sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class compBackendTest {

    compBackend cbe = new compBackend();

    @Test
    public void testGetDate()
    {
        assertEquals(true, cbe.getDate() instanceof String);
    }

    //This test only runs for the day specified - hence it is commented out - to test this out, uncomment and change date

    @Test
    public void testGetDateCheck()
    {
        assertEquals("2020-01-11", cbe.getDate());
    }

}
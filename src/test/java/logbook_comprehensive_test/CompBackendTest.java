package logbook_comprehensive_test;

import logbook_comprehensive.CompBackend;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompBackendTest {

    CompBackend cbe = new CompBackend();

    @Test
    public void testGetDate()
    {
        assertEquals(true, cbe.getDate() instanceof String);
    }

    //This test only runs for the day specified - hence it is commented out - to test this out, uncomment and change date

    /*@Test
<<<<<<< HEAD:src/test/java/logbook_comprehensive/CompBackendTest.java
   public void testGetDateCheck()
    {
        assertEquals("2020-01-11", cbe.getDate());
    }
*/
  public void testGetDateCheck()
    {
        assertEquals("2020-01-11", cbe.getDate());
    }


}
package logbook_simple_test;

import logbook_simple.logbookBackend;
import org.junit.Test;

import static org.junit.Assert.*;

public class logbookBackendTest {

    logbookBackend logb = new logbookBackend();

    @Test
    public void testGetDate()
    {
        assertEquals(true, logb.getDate() instanceof String);
    }
}
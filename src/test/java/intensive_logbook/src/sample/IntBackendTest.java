package intensive_logbook.src.sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class IntBackendTest {

    IntBackend intb = new IntBackend();

    @Test
    public void testGetDate()
    {
        assertEquals(true, intb.getDate() instanceof String);
    }
}
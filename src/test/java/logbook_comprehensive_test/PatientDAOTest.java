package logbook_comprehensive_test;

import DB_ALE.Patient;
import logbook_comprehensive.PatientDAO;
import org.junit.Test;

import static org.junit.Assert.*;

public class PatientDAOTest {

    PatientDAO p;

    //Testing to see if the right type of class is returned
    @Test
    public void testgetDetailsForEmailClass() {
        assertEquals(true, p.getDetailsForEmail("mc@gmail.com") instanceof Patient);
    }

    //Testing to see if the right name is stored in the patient object when a valid email is put in.
    @Test
    public void testgetDetailsForEmailName() {
        assertEquals("Michael Faraday", p.getDetailsForEmail("mf@gmail.com").getName());
    }

    //Testing to see if null is stored for the patient name when an invalid email address is inputted
    @Test
    public void testgetDetailsForEmailFalse() {
        assertEquals(null, p.getDetailsForEmail("bydbydufefecom").getName());
    }


}
package intensive_logbook.src.sample;

import javafx.event.ActionEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Controller_insTest {

    Controller_ins ctlbi;

    @Mock()
    ActionEvent event;

    @Before
    public void setUp() {
        ctlbi = new Controller_ins();
        MockitoAnnotations.initMocks(this);
    }

    //This should throw a NullPointerException as event isn't properly initialized
    @Test(expected = NullPointerException.class)
    public void testBtnAddExc() throws Exception {
        ctlbi.btnAdd(event);
    }

    @Test(expected = NullPointerException.class)
    public void testLoadAddExc(){
        ctlbi.loadAdd(event);
    }

    @Test()
    public void testFindTableNull() {
        assertEquals(null, ctlbi.findTable("riyanama@gmail.com"));
    }

    @Test()
    public void testFindTable() {
        assertEquals(true, ctlbi.findTable("p@gmail.com") instanceof ArrayList);
    }

    @Test(expected = NullPointerException.class)
    public void testplotTodayExc(){
        ctlbi.plotToday(event);
    }

    /*@Test(expected = ExceptionInInitializerError.class)
    public void testaccessProfileExc() throws Exception {
        ctlb.accessProfile(event);
    }*/

    @Test()
    public void testisNullOrEmptyTrue() {
        assertEquals(true, ctlbi.isNullOrEmpty(""));
    }

    @Test()
    public void testisNullOrEmptyFalse() {
        assertEquals(false, ctlbi.isNullOrEmpty("gshb"));
    }


}
package simple_logbook;

import javafx.event.ActionEvent;
import logbook_simple.Controller_lb_v1_2;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Controller_lb_v1_2Test {

    Controller_lb_v1_2 ctlbs;

    @Mock()
    ActionEvent event;

    @Before
    public void setUp() {
        ctlbs = new Controller_lb_v1_2();
        MockitoAnnotations.initMocks(this);
    }

    //This should throw a NullPointerException as event isn't properly initialized
    @Test(expected = NullPointerException.class)
    public void testBtnAddExc() throws Exception {
        ctlbs.btnAdd(event);
    }

    @Test(expected = NullPointerException.class)
    public void testLoadAddExc(){
        ctlbs.loadAdd(event);
    }

    @Test()
    public void testFindTableNull() {
        assertEquals(null, ctlbs.findTable("riyanama@gmail.com"));
    }

    @Test()
    public void testFindTable() {
        assertEquals(true, ctlbs.findTable("p@gmail.com") instanceof ArrayList);
    }

    @Test(expected = NullPointerException.class)
    public void testplotTodayExc(){
        ctlbs.plotToday(event);
    }

    /*@Test(expected = ExceptionInInitializerError.class)
    public void testaccessProfileExc() throws Exception {
        ctlb.accessProfile(event);
    }*/

    @Test()
    public void testisNullOrEmptyTrue() {
        assertEquals(true, ctlbs.isNullOrEmpty(""));
    }

    @Test()
    public void testisNullOrEmptyFalse() {
        assertEquals(false, ctlbs.isNullOrEmpty("gshb"));
    }


}
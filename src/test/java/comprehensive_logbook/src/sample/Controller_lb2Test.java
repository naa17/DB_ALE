package comprehensive_logbook.src.sample;

import javafx.event.ActionEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Controller_lb2Test {


    Controller_lb2 ctlb;

    @Mock()
    ActionEvent event;

    @Before
    public void setUp() {
        ctlb = new Controller_lb2();
        MockitoAnnotations.initMocks(this);
    }

    //This should throw a NullPointerException as event isn't properly initialized
    @Test(expected = NullPointerException.class)
    public void testBtnAddExc() {
        ctlb.btnAdd(event);
    }

    @Test(expected = NullPointerException.class)
    public void testLoadAddExc(){
       ctlb.loadAdd(event);
    }

    @Test()
    public void testFindTableNull() {
        assertEquals(null, ctlb.findTable("riyanama@gmail.com"));
    }

    @Test()
    public void testFindTable() {
        assertEquals(true, ctlb.findTable("p@gmail.com") instanceof ArrayList);
    }

    @Test(expected = NullPointerException.class)
    public void testplotTodayExc(){
        ctlb.plotToday(event);
    }

    @Test(expected = ExceptionInInitializerError.class)
    public void testaccessProfileExc() throws Exception {
        ctlb.accessProfile(event);
    }

    @Test()
    public void testisNullOrEmptyTrue() {
        assertEquals(true, ctlb.isNullOrEmpty(""));
    }

    @Test()
    public void testisNullOrEmptyFalse() {
        assertEquals(false, ctlb.isNullOrEmpty("gshb"));
    }


}
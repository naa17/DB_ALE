package simple_logbook.src.sample;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Today_v1_2Test {

    Today_v1_2 today;

    @Test
    public void TestisEmptyFalse()
    {
        today = new Today_v1_2("2", "2", "2", "2");
        assertEquals(false, today.isEmpty());
    }

    //Check this out later
    /*@Test
    public void TestisEmptyTrue() {
        today.setCarb("");
        today.setGluc("");
        System.out.println(today.getGluc());
        System.out.println(today.getCarb());
        assertEquals(true, today.isEmpty());
    }*/
/*
    //Testing getters and setters
    public Today_v1_2Test(){
        today = new Today_v1_2();
        String str = "test";
        today.setGluc(str);
        assertEquals(str, today.getGluc());
    }*/
}
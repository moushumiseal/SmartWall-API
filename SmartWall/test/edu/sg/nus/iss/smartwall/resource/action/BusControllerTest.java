package edu.sg.nus.iss.smartwall.resource.action;

import edu.sg.nus.iss.smartwall.resource.helper.ApiResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Moushumi Seal
 */
public class BusControllerTest {

    BusController busService;
    @Before
    public void setUp() {
        busService = new BusController();
    }
    
    @After
    public void tearDown() {
        busService = null;
    }
    
    /**
     * Tests of process method, of class BusService.
     */
    
    /**
     * With Invalid data
     * @throws Exception 
     */
    @Test
    public void testProcessInValidData() throws Exception {
        System.out.println("BusService: process()");
        busService.setBusstopName("library");
        String expResult = "I didn't get that. Can you say it again?";
        ApiResponse result = busService.process();
        assertEquals(expResult, result.getSpeech());
    }
    
    /**
     * With Invalid data
     * @throws Exception 
     */
    @Test
    public void testProcessEmptyData() throws Exception {
        System.out.println("BusService: process()");
        busService.setBusstopName("");
        String expResult = "I didn't get that. Can you say it again?";
        ApiResponse result = busService.process();
        assertEquals(expResult, result.getSpeech());
    }
    
    /**
     * With Invalid data
     * @throws Exception 
     */
    @Test
    public void testProcessBlankData() throws Exception {
        System.out.println("BusService: process()");
        busService.setBusstopName("   ");
        String expResult = "I didn't get that. Can you say it again?";
        ApiResponse result = busService.process();
        assertEquals(expResult, result.getSpeech());
    }
    
    /**
     * With Invalid data
     * @throws Exception 
     */
    @Test
    public void testProcessNumberData() throws Exception {
        System.out.println("BusService: process()");
        busService.setBusstopName("1233");
        String expResult = "I didn't get that. Can you say it again?";
        ApiResponse result = busService.process();
        assertEquals(expResult, result.getSpeech());
    }
    
    /**
     * With Invalid data
     * @throws Exception 
     */
    @Test
    public void testProcessAlphaNumericeData() throws Exception {
        System.out.println("BusService: process()");
        busService.setBusstopName("assd1233");
        String expResult = "I didn't get that. Can you say it again?";
        ApiResponse result = busService.process();
        assertEquals(expResult, result.getSpeech());
    }
    
    /**
     * With Invalid data
     * @throws Exception 
     */
    @Test
    public void testProcessNullData() throws Exception {
        System.out.println("BusService: process()");
        busService.setBusstopName("Null");
        String expResult = "I didn't get that. Can you say it again?";
        ApiResponse result = busService.process();
        assertEquals(expResult, result.getSpeech());
    }
    
    /**
     * With valid data
     * @throws Exception 
     */
    @Test
    public void testProcessvalidData(){
        System.out.println("BusService: process()");
        busService.setBusstopName("kent ridge");
        String expResult = "speech: To go to kent ridge you can take A2.";
        ApiResponse resultResponse = busService.process();
        String result[] = resultResponse.getSpeech().split(",");
        assertEquals(expResult, result[0]);
        assertEquals("busstop", resultResponse.getSource());
    }
    
}

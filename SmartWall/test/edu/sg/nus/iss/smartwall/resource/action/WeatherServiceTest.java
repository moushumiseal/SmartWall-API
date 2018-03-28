/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.resource.action;

import edu.sg.nus.iss.smartwall.resource.helper.ApiResponse;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Moushumi Seal
 */
public class WeatherServiceTest {
    
    WeatherService weatherService;
    
    @Before
    public void setUp() {
        weatherService = new WeatherService();
    }
    
    @After
    public void tearDown() {
        weatherService = null;
    }

    /**
     * Test of process method, of class WeatherService.
     */
   @Test
    public void testProcessValidCity() throws Exception {
        System.out.println("testProcessValidCity");
        weatherService.setGeocity("kolkata");
        String expResult = "speech:The temperature at kolkata ";
        ApiResponse resultResponse = weatherService.process();
        String result[] = resultResponse.getSpeech().split("is");
        assertEquals(expResult, result[0]);
        assertEquals("weather", resultResponse.getSource());
    }
    
    /**
     * Test of process method, of class WeatherService.
     */
    @Test
    public void testProcessInvalidCity() throws Exception {
        System.out.println("testProcessInvalidCity");
        weatherService.setGeocity("India");
        String expResult = "speech:I didn't get that. Can you say it again?";
        ApiResponse resultResponse = weatherService.process();
        String result[] = resultResponse.getSpeech().split(",");
        assertEquals(expResult, result[0]);
        assertEquals("weather", resultResponse.getSource());
    }
    
}

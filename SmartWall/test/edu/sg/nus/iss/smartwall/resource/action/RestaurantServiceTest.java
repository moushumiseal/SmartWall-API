/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class RestaurantServiceTest {
    
    RestaurantService restaurantService;
    
    @Before
    public void setUp() {
        restaurantService = new RestaurantService();
    }
    
    @After
    public void tearDown() {
        restaurantService = null;
    }

    /**
     * Test of process method, of class RestaurantService.
     */
    //TODO
    @Test
    public void testProcess() throws Exception {
        System.out.println("RestaurantServiceTest: process()");
        String expResult = "speech: Restaurants at NUS ";
        
        //TODO
       /* ApiResponse resultResponse = restaurantService.process();
        String results[] = resultResponse.getSpeech().split("are");
        assertEquals(expResult, results[0]);*/
    }
    
}

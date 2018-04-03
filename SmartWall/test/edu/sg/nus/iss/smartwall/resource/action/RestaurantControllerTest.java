/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.resource.action;

import edu.sg.nus.iss.smartwall.resource.helper.ApiResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Moushumi Seal
 */
public class RestaurantControllerTest {
    
    RestaurantController restaurantService;
    
    @Before
    public void setUp() {
        restaurantService = new RestaurantController();
    }
    
    @After
    public void tearDown() {
        restaurantService = null;
    }

    /**
     * Test of process method, of class RestaurantController.
     */
    @Test
    public void testProcess() throws Exception {
        System.out.println("RestaurantServiceTest: process()");
        String expResult = "speech: Restaurants at NUS ";
        
       
        ApiResponse resultResponse = restaurantService.process();
        String results[] = resultResponse.getSpeech().split("are");
        Assert.assertEquals(expResult, results[0]);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.resource.action;

import edu.sg.nus.iss.smartwall.resource.helper.Service;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Moushumi Seal
 */
public class NewsControllerTest {

    NewsController newsService;
    
    @Before
    public void setUp() {
        newsService = new NewsController();
    }
    
    @After
    public void tearDown() {
        newsService = null;
    }

    /**
     * Test of process method, of class NewsController.
     */
    @Test
    public void testProcess() throws Exception {
        System.out.println("NewsService: process()");
        String expResult = "speech:First Headlines ";
        Service result = newsService.process();
        String results[] = result.getSpeech().split("-");
        assertEquals(expResult, results[0]);
        assertEquals("news", result.getSource());
    }
    
}

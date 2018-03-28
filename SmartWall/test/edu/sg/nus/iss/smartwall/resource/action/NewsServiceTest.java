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
public class NewsServiceTest {

    NewsService newsService;
    
    @Before
    public void setUp() {
        newsService = new NewsService();
    }
    
    @After
    public void tearDown() {
        newsService = null;
    }

    /**
     * Test of process method, of class NewsService.
     */
    @Test
    public void testProcess() throws Exception {
        System.out.println("NewsService: process()");
        String expResult = "speech:First Headlines ";
        ApiResponse result = newsService.process();
        String results[] = result.getSpeech().split("-");
        assertEquals(expResult, results[0]);
        assertEquals("news", result.getSource());
    }
    
}

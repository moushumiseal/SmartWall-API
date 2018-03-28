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
 * @author mseal
 */
public class DictionaryServiceTest {
    
    DictionaryService dictionaryService;
    @Before
    public void setUp() {
        dictionaryService = new DictionaryService();
        dictionaryService.setWord("smart");
    }
    
    @After
    public void tearDown() {
        dictionaryService = null;
    }

    /**
     * Test of process method, of class DictionaryService.
     */
    @Test
    public void testProcess() throws Exception {
        System.out.println("DictionaryService: process()");
        String expResult = "speech: According to the Oxford Dictionary, the meaning of smart is (of a person) clean, tidy, and well dressed";
        ApiResponse result = dictionaryService.process();
        assertEquals(expResult, result.getSpeech());
        assertEquals("meaning", result.getSource());
    }
    
}

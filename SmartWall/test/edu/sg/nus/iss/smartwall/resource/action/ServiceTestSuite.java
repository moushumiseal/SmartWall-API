/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.resource.action;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Moushumi Seal
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({edu.sg.nus.iss.smartwall.resource.action.BusServiceTest.class, edu.sg.nus.iss.smartwall.resource.action.DictionaryServiceTest.class, edu.sg.nus.iss.smartwall.resource.action.NewsServiceTest.class, edu.sg.nus.iss.smartwall.resource.action.WeatherServiceTest.class, edu.sg.nus.iss.smartwall.resource.action.RestaurantServiceTest.class})
public class ServiceTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}

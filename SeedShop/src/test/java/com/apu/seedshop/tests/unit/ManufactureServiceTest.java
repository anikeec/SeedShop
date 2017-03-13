/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.tests.unit;

import com.apu.seedshop.jpa.Manufacture;
import com.apu.seedshop.services.ManufactureService;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ManufactureServiceTest {
    private static final Logger logger =  LoggerFactory.getLogger(ManufactureServiceTest.class);
    Integer testId = 100;
    
    @Autowired
    ManufactureService manufactureService;
    
    public ManufactureServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllManufactures method, of class ManufactureService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllManufactures() throws Exception {
        logger.debug("Test - getAllManufactures");
        int expResult = 2;
        int result = manufactureService.getAllManufactures().size();
        assert(expResult <= result);
    }

    /**
     * Test of getManufactureById method, of class ManufactureService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetManufactureById() throws Exception {
        logger.debug("Test - getManufactureById");
        Integer manufactId = 1;
        Manufacture expResult = null;
        Manufacture result = manufactureService.getManufactureById(manufactId);
        assert(expResult != result);
    }

    /**
     * Test of addManufacture method, of class ManufactureService.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddManufacture() throws Exception {
        logger.debug("Test - addManufacture");
        Manufacture m = new Manufacture();
        m.setManufactId(testId);
        m.setName("Test name");
        m.setAddress("Test address.");
        Manufacture expResult = m;
        manufactureService.addManufacture(m);
        Manufacture result = manufactureService.getManufactureById(testId);        
        assertEquals(expResult, result);
        manufactureService.delManufacture(testId);
        result = manufactureService.getManufactureById(testId);
        expResult = null;
        assertEquals(expResult, result);
    }
    
}

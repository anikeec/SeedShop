/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.tests.unit;

import com.apu.seedshop.jpa.Packing;
import com.apu.seedshop.services.PackService;
import com.apu.seedshop.services.PackingService;
import java.math.BigDecimal;
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
public class PackingServiceTest {
    private static final Logger logger =  LoggerFactory.getLogger(PackingServiceTest.class);
    Integer testIdRead = 1;
    Integer testId = 100;
    
    @Autowired
    PackingService packingService;
    
    @Autowired
    PackService packService;
    
    public PackingServiceTest() {
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
     * Test of getAllPackings method, of class PackingService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllPackings() throws Exception {
        logger.debug("Test - getAllPackings");
        int expResult = 2;
        int result = packingService.getAllPackings().size();
        assert(expResult <= result);
    }

    /**
     * Test of getPackingById method, of class PackingService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPackingById() throws Exception {
        logger.debug("Test - getPackingById");
        Packing expResult = null;
        Packing result = packingService.getPackingById(testIdRead);
        assert(expResult != result);
    }

    /**
     * Test of addPacking method, of class PackingService.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddPacking() throws Exception {
        logger.debug("Test - addPacking");
        Packing p = new Packing();        
        p.setPackingId(testId);
        p.setWeight(new BigDecimal(20.0));
        p.setAmount(10);
        p.setPackId(packService.getPackById(testIdRead));
        Packing expResult = p;
        packingService.addPacking(p);
        Packing result = packingService.getPackingById(testId);
        assertEquals(expResult, result);
        packingService.delPacking(testId);
        result = packingService.getPackingById(testId);
        if(result.getUsed() == false) {
            result = null;
        }
        expResult = null;
        assertEquals(expResult, result);
        packingService.delTestPacking(testId);
        result = packingService.getPackingById(testId);
        expResult = null;
        assertEquals(expResult, result);
    }
    
}

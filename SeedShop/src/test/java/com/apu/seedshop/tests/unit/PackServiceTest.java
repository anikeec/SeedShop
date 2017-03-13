/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.tests.unit;

import com.apu.seedshop.jpa.Pack;
import com.apu.seedshop.services.PackService;
import java.util.List;
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
public class PackServiceTest {
    private static final Logger logger =  LoggerFactory.getLogger(PackServiceTest.class);
    Integer testId = 100;
    
    @Autowired
    PackService packService;
    
    public PackServiceTest() {
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
     * Test of getAllPacks method, of class PackService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllPacks() throws Exception {
        logger.debug("Test - getAllPacks");
        int expResult = 2;
        int result = packService.getAllPacks().size();
        assert(expResult <= result);
    }

    /**
     * Test of getPackById method, of class PackService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPackById() throws Exception {
        logger.debug("Test - getPackById");
        Integer packId = 1;
        Pack expResult = null;
        Pack result = packService.getPackById(packId);
        assert(expResult != result);
    }

    /**
     * Test of addPack method, of class PackService.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddPack() throws Exception {
        logger.debug("Test - addPack");
        Pack p = new Pack();
        p.setPackId(testId);
        p.setName("Test name.");
        Pack expResult = p;
        packService.addPack(p);
        Pack result = packService.getPackById(testId);
        assertEquals(expResult, result);
        packService.delPack(testId);
        result = packService.getPackById(testId);
        expResult = null;
        assertEquals(expResult, result);
    }
    
}

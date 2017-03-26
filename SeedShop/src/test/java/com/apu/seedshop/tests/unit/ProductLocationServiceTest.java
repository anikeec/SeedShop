/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.tests.unit;

import com.apu.seedshop.jpa.ProductLocation;
import com.apu.seedshop.services.ProductLocationMapper;
import com.apu.seedshop.services.ProductLocationService;
import com.apu.seedshop.tests.TestId;
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
public class ProductLocationServiceTest {
    private static final Logger logger =  LoggerFactory.getLogger(ProductLocationServiceTest.class);
    
    @Autowired
    ProductLocationMapper plMapper;
    @Autowired
    ProductLocationService plService;
    
    public ProductLocationServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ProductLocation pl = plMapper.newProductLocation();
        pl.setLocationId(TestId.TestIdProductLocationServPL);
        pl.setName("test");
        plService.addProductLocation(pl);
    }
    
    @After
    public void tearDown() {
        plService.delProductLocationFull(TestId.TestIdProductLocationServPL);
    }

    /**
     * Test of getAllProductLocations method, of class ProductLocationService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllProductLocations() throws Exception {
        logger.debug("Test - getAllProductLocations");
        int expResult = 1;
        int result = plService.getAllProductLocations().size();
        assert(expResult <= result);
    }

    /**
     * Test of getProductLocationById method, of class ProductLocationService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetProductLocationById() throws Exception {
        logger.debug("Test - getProductLocationById");
        ProductLocation expResult = null;
        ProductLocation result = plService.getProductLocationById(
                                            TestId.TestIdProductLocationServPL);
        assert(expResult != result);
    }

    /**
     * Test of addProductLocation method, of class ProductLocationService.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddProductLocation() throws Exception {
        logger.debug("Test - addProductLocation");
        
        ProductLocation pl = plMapper.newProductLocation();
        pl.setLocationId(TestId.TestIdProductLocationServPLNew);
        pl.setName("test");
        plService.addProductLocation(pl);

        ProductLocation expResult = pl;
        ProductLocation result = 
            plService.getProductLocationById(TestId.TestIdProductLocationServPLNew);
        assertEquals(expResult, result);
        plService.delProductLocation(TestId.TestIdProductLocationServPLNew);
        result = plService.getProductLocationById(TestId.TestIdProductLocationServPLNew);
        if(result.getUsed() == false) {
            result = null;
        }
        expResult = null;
        assertEquals(expResult, result);
        
        plService.delProductLocationFull(TestId.TestIdProductLocationServPLNew);
        result = plService.getProductLocationById(TestId.TestIdProductLocationServPLNew);
        expResult = null;
        assertEquals(expResult, result);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.tests.unit;

import com.apu.seedshop.jpa.DeliveryService;
import com.apu.seedshop.services.DeliveryServiceMapper;
import com.apu.seedshop.services.DeliveryServiceService;
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
public class DeliveryServiceServiceTest {
    private static final Logger logger =  LoggerFactory.getLogger(DeliveryServiceServiceTest.class);
    
    @Autowired
    DeliveryServiceMapper delivStatusMapper;
    @Autowired
    DeliveryServiceService delivStatusService;
    
    public DeliveryServiceServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        DeliveryService ds = delivStatusMapper.newDeliveryService();
        ds.setDeliveryId(TestId.TestIdDeliveryServiceServDS);
        ds.setName("test");
        ds.setCollectAvail(false);
        delivStatusService.addDeliveryService(ds);
    }
    
    @After
    public void tearDown() {
        delivStatusService.delTestDeliveryService(TestId.TestIdDeliveryServiceServDS);
    }

    /**
     * Test of getAllDeliveryServices method, of class DeliveryServiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllDeliveryServices() throws Exception {
        logger.debug("Test - getAllDeliveryServices");
        int expResult = 1;
        int result = delivStatusService.getAllDeliveryServices().size();
        assert(expResult <= result);
    }

    /**
     * Test of getDeliveryServiceById method, of class DeliveryServiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetDeliveryServiceById() throws Exception {
        logger.debug("Test - getDeliveryServiceById");
        DeliveryService expResult = null;
        DeliveryService result = delivStatusService.getDeliveryServiceById(
                                            TestId.TestIdDeliveryServiceServDS);
        assert(expResult != result);
    }

    /**
     * Test of addDeliveryService method, of class DeliveryServiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddDeliveryService() throws Exception {
        logger.debug("Test - addDeliveryService");
        
        DeliveryService ds = delivStatusMapper.newDeliveryService();
        ds.setDeliveryId(TestId.TestIdDeliveryServiceServDSNew);
        ds.setName("test");
        ds.setCollectAvail(false);
        delivStatusService.addDeliveryService(ds);

        DeliveryService expResult = ds;
        DeliveryService result = 
                delivStatusService.getDeliveryServiceById(TestId.TestIdDeliveryServiceServDSNew);
        assertEquals(expResult, result);
        delivStatusService.delDeliveryService(TestId.TestIdDeliveryServiceServDSNew);
        result = delivStatusService.getDeliveryServiceById(TestId.TestIdDeliveryServiceServDSNew);
        if(result.getUsed() == false) {
            result = null;
        }
        expResult = null;
        assertEquals(expResult, result);
        
        delivStatusService.delTestDeliveryService(TestId.TestIdDeliveryServiceServDSNew);
        result = delivStatusService.getDeliveryServiceById(TestId.TestIdDeliveryServiceServDSNew);
        expResult = null;
        assertEquals(expResult, result);
    }
    
}

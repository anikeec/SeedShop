/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.tests.unit;

import com.apu.seedshop.jpa.DeliveryStatus;
import com.apu.seedshop.services.DeliveryStatusMapper;
import com.apu.seedshop.services.DeliveryStatusService;
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
public class DeliveryStatusServiceTest {
    private static final Logger logger =  LoggerFactory.getLogger(DeliveryStatusServiceTest.class);
    
    @Autowired
    DeliveryStatusMapper delivStatusMapper;
    @Autowired
    DeliveryStatusService delivStatusService;
    
    public DeliveryStatusServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        DeliveryStatus ds = delivStatusMapper.newDeliveryStatus();
        ds.setStatusId(TestId.TestIdDeliveryStatusServDS);
        ds.setStatus("test");
        delivStatusService.addDeliveryStatus(ds);
    }
    
    @After
    public void tearDown() {
        delivStatusService.delTestDeliveryStatus(TestId.TestIdDeliveryStatusServDS);
    }

    /**
     * Test of getAllDeliveryStatuss method, of class DeliveryStatusService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllDeliveryStatuss() throws Exception {
        logger.debug("Test - getAllDeliveryStatuss");
        int expResult = 1;
        int result = delivStatusService.getAllDeliveryStatuses().size();
        assert(expResult <= result);
    }

    /**
     * Test of getDeliveryStatusById method, of class DeliveryStatusService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetDeliveryStatusById() throws Exception {
        logger.debug("Test - getDeliveryStatusById");
        DeliveryStatus expResult = null;
        DeliveryStatus result = delivStatusService.getDeliveryStatusById(
                                            TestId.TestIdDeliveryStatusServDS);
        assert(expResult != result);
    }

    /**
     * Test of addDeliveryStatus method, of class DeliveryStatusService.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddDeliveryStatus() throws Exception {
        logger.debug("Test - addDeliveryStatus");
        
        DeliveryStatus ds = delivStatusMapper.newDeliveryStatus();
        ds.setStatusId(TestId.TestIdDeliveryStatusServDSNew);
        ds.setStatus("test");
        delivStatusService.addDeliveryStatus(ds);

        DeliveryStatus expResult = ds;
        DeliveryStatus result = 
                delivStatusService.getDeliveryStatusById(TestId.TestIdDeliveryStatusServDSNew);
        assertEquals(expResult, result);
        delivStatusService.delDeliveryStatus(TestId.TestIdDeliveryStatusServDSNew);
        result = delivStatusService.getDeliveryStatusById(TestId.TestIdDeliveryStatusServDSNew);
        if(result.getUsed() == false) {
            result = null;
        }
        expResult = null;
        assertEquals(expResult, result);
        
        delivStatusService.delTestDeliveryStatus(TestId.TestIdDeliveryStatusServDSNew);
        result = delivStatusService.getDeliveryStatusById(TestId.TestIdDeliveryStatusServDSNew);
        expResult = null;
        assertEquals(expResult, result);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.tests.unit;

import com.apu.seedshop.jpa.Pack;
import com.apu.seedshop.jpa.Packing;
import com.apu.seedshop.services.PackMapper;
import com.apu.seedshop.services.PackService;
import com.apu.seedshop.services.PackingMapper;
import com.apu.seedshop.services.PackingService;
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
public class PackingServiceTest {
    private static final Logger logger =  LoggerFactory.getLogger(PackingServiceTest.class);
    
    @Autowired
    PackMapper packMapper;    
    @Autowired
    PackService packService;
    @Autowired
    PackingMapper packingMapper;    
    @Autowired
    PackingService packingService;
    
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
        Pack pack = packMapper.newPack();
        pack.setPackId(TestId.TestIdPackingServPack);
        pack.setName("test");
        packService.addPack(pack);
        
        Packing packing = packingMapper.newPacking();
        packing.setPackingId(TestId.TestIdPackingServPacking);
        packing.setPackId(pack);
        packingService.addPacking(packing);
    }
    
    @After
    public void tearDown() {
        packingService.delPackingFull(TestId.TestIdPackingServPacking);
        packService.delPackFull(TestId.TestIdPackingServPack);        
    }

    /**
     * Test of getAllPackings method, of class PackingService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllPackings() throws Exception {
        logger.debug("Test - getAllPackings");
        int expResult = 1;
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
        Packing result = 
                packingService.getPackingById(TestId.TestIdPackingServPacking);
        assert(expResult != result);
    }

    /**
     * Test of addPacking method, of class PackingService.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddPacking() throws Exception {
        logger.debug("Test - addPacking");
        
        Pack pack = packService.getPackById(TestId.TestIdPackingServPack);
        
        Packing packing = packingMapper.newPacking();
        packing.setPackingId(TestId.TestIdPackingServPackingNew);
        packing.setPackId(pack);
        packingService.addPacking(packing);    

        Packing expResult = packing;
        Packing result = 
                packingService.getPackingById(TestId.TestIdPackingServPackingNew);
        assertEquals(expResult, result);
        packingService.delPacking(TestId.TestIdPackingServPackingNew);
        result = packingService.getPackingById(TestId.TestIdPackingServPackingNew);
        if(result.getUsed() == false) {
            result = null;
        }
        expResult = null;
        assertEquals(expResult, result);
        
        packingService.delPackingFull(TestId.TestIdPackingServPackingNew);
        result = packingService.getPackingById(TestId.TestIdPackingServPackingNew);
        expResult = null;
        assertEquals(expResult, result);
    }
    
}

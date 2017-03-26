/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.tests.unit;

import com.apu.seedshop.jpa.Pack;
import com.apu.seedshop.services.PackMapper;
import com.apu.seedshop.services.PackService;
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
public class PackServiceTest {
    private static final Logger logger =  LoggerFactory.getLogger(PackServiceTest.class);
    
    @Autowired
    PackMapper packMapper;
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
        createTestPack(TestId.TestIdPackServPack);
    }
    
    @After
    public void tearDown() {
        removeTestPack(TestId.TestIdPackServPack);
    }
    
    public Pack createTestPack(Integer id) {
        Pack pack = packMapper.newPack();
        pack.setPackId(id);
        pack.setName("test");
        return packService.addPack(pack);
    }
    
    public void removeTestPack(Integer id) {
        packService.delPackFull(id);
    }

    /**
     * Test of getAllPacks method, of class PackService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllPacks() throws Exception {
        logger.debug("Test - getAllPacks");
        int expResult = 1;
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
        Pack expResult = null;
        Pack result = packService.getPackById(TestId.TestIdPackServPack);
        assert(expResult != result);
    }

    /**
     * Test of addPack method, of class PackService.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddPack() throws Exception {
        logger.debug("Test - addPack");
        
        Pack pack = createTestPack(TestId.TestIdPackServPackNew);
        Pack expResult = pack;
        Pack result = packService.getPackById(TestId.TestIdPackServPackNew);
        assertEquals(expResult, result);
        
        packService.delPack(TestId.TestIdPackServPackNew);
        result = packService.getPackById(TestId.TestIdPackServPackNew);
        if(result.getUsed() == false) {
            result = null;
        }
        expResult = null;
        assertEquals(expResult, result);
        
        removeTestPack(TestId.TestIdPackServPackNew);
        result = packService.getPackById(TestId.TestIdPackServPackNew);
        expResult = null;
        assertEquals(expResult, result);
    }
    
}

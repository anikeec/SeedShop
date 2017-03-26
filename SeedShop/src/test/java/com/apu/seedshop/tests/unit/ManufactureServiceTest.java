/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.tests.unit;

import com.apu.seedshop.jpa.Manufacture;
import com.apu.seedshop.services.ManufactureMapper;
import com.apu.seedshop.services.ManufactureService;
import com.apu.seedshop.tests.TestId;
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
    //Integer testId = 100;
    
    @Autowired
    ManufactureService manufactureService;
    @Autowired
    ManufactureMapper manufactureMapper;
    
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
        createTestManufacture(TestId.TestIdManufactServManufact);
    }
    
    @After
    public void tearDown() {
        removeTestManufacture(TestId.TestIdManufactServManufact);
    }
    
    public Manufacture createTestManufacture(Integer id) {
        Manufacture m = manufactureMapper.newManufacture();
        m.setManufactId(id);
        m.setName("test");
        m.setAddress("test");
        m.setUsed(true);
        return manufactureService.addManufacture(m);
    }
    
    public void removeTestManufacture(Integer id) {
        manufactureService.delManufactureFull(id);
    }

    /**
     * Test of getAllManufactures method, of class ManufactureService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllManufactures() throws Exception {
        logger.debug("Test - getAllManufactures");
        int expResult = 1;
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
        Manufacture expResult = null;
        Manufacture result = 
            manufactureService.getManufactureById(TestId.TestIdManufactServManufact);
        assert(expResult != result);
    }

    /**
     * Test of addManufacture method, of class ManufactureService.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddManufacture() throws Exception {
        logger.debug("Test - addManufacture");
        
        Manufacture m = 
            createTestManufacture(TestId.TestIdManufactServManufactNew);
        Manufacture expResult = m;
        Manufacture result = 
            manufactureService.getManufactureById(TestId.TestIdManufactServManufactNew);        
        assertEquals(expResult, result);
        
        manufactureService.delManufacture(TestId.TestIdManufactServManufactNew);
        result = 
            manufactureService.getManufactureById(TestId.TestIdManufactServManufactNew);
        if(result.getUsed() == false) {
            result = null;
        }
        expResult = null;
        assertEquals(expResult, result);
        
        //real delete test manufacture
        removeTestManufacture(TestId.TestIdManufactServManufactNew);
        result = 
            manufactureService.getManufactureById(TestId.TestIdManufactServManufactNew);
        expResult = null;
        assertEquals(expResult, result);
    }
    
}

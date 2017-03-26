/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.tests.unit;

import com.apu.seedshop.jpa.AProduct;
import com.apu.seedshop.services.AProductMapper;
import com.apu.seedshop.services.AProductService;
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
public class AProductServiceTest {
    private static final Logger logger =  LoggerFactory.getLogger(AProductServiceTest.class);
    
    @Autowired
    AProductMapper aProductMapper;
    @Autowired
    AProductService aProductService;
    
    public AProductServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            AProduct app = aProductMapper.newAProduct();
            app.setParentId(null);
            app.setProductId(TestId.TestIdAProductServAPparent);
            app.setName("test");
            aProductService.addAProduct(app);
            AProduct ap = aProductMapper.newAProduct();
            ap.setParentId(app);
            ap.setProductId(TestId.TestIdAProductServAP);
            ap.setName("test");
            aProductService.addAProduct(ap);
        } catch(Exception e) {
            logger.error("Exception:" + e.getCause() + e.getMessage());
        } 
    }
    
    @After
    public void tearDown() {
        aProductService.delAProductFull(TestId.TestIdAProductServAP);
        aProductService.delAProductFull(TestId.TestIdAProductServAPparent);
    }

    /**
     * Test of getAllAProducts method, of class AProductService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllAProducts() throws Exception {
        logger.debug("Test - getAllAProducts");
        int expResult = 1;
        int result = aProductService.getAllAProducts().size();
        assert(expResult <= result);
    }

    /**
     * Test of getAProductById method, of class AProductService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAProductById() throws Exception {
        logger.debug("Test - getAProductById");
        AProduct expResult = 
            aProductService.getAProductById(TestId.TestIdAProductServAPparent);
        AProduct result = 
            aProductService.getAProductById(TestId.TestIdAProductServAP).getParentId();
        assertEquals(expResult,result);
    }

    /**
     * Test of deleteAProduct method, of class AProductService.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteAProduct() throws Exception {
        logger.debug("Test - addAProduct");

        AProduct expResult = 
            aProductService.getAProductById(TestId.TestIdAProductServAPparent);
        AProduct result = 
            aProductService.getAProductById(TestId.TestIdAProductServAP).getParentId();
        assertEquals(expResult, result);
        
        aProductService.delAProduct(TestId.TestIdAProductServAP);
        result = aProductService.getAProductById(TestId.TestIdAProductServAP);
        if(result.getUsed() == false) {
            result = null;
        }
        expResult = null;
        assertEquals(expResult, result);
        aProductService.delAProductFull(TestId.TestIdAProductServAPparent);
        result = aProductService.getAProductById(TestId.TestIdAProductServAP);
        expResult = null;
        assertEquals(expResult, result);
    }
    
}

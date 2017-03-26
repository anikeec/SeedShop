/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.tests.unit;

import com.apu.seedshop.jpa.AProduct;
import com.apu.seedshop.jpa.Availability;
import com.apu.seedshop.jpa.Manufacture;
import com.apu.seedshop.jpa.Pack;
import com.apu.seedshop.jpa.Packing;
import com.apu.seedshop.jpa.Product;
import com.apu.seedshop.jpa.ProductLocation;
import com.apu.seedshop.services.AProductMapper;
import com.apu.seedshop.services.AProductService;
import com.apu.seedshop.services.AvailabilityMapper;
import com.apu.seedshop.services.AvailabilityService;
import com.apu.seedshop.services.ManufactureMapper;
import com.apu.seedshop.services.ManufactureService;
import com.apu.seedshop.services.PackMapper;
import com.apu.seedshop.services.PackService;
import com.apu.seedshop.services.PackingMapper;
import com.apu.seedshop.services.PackingService;
import com.apu.seedshop.services.ProductLocationMapper;
import com.apu.seedshop.services.ProductLocationService;
import com.apu.seedshop.services.ProductMapper;
import com.apu.seedshop.services.ProductService;
import com.apu.seedshop.tests.TestId;
import java.math.BigDecimal;
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
public class AvailabilityServiceTest {
    private static final Logger logger =  LoggerFactory.getLogger(AvailabilityServiceTest.class);
    
    @Autowired
    AvailabilityService availService;    
    @Autowired
    AvailabilityMapper availMapper;
    @Autowired
    ProductMapper productMapper; 
    @Autowired
    ProductService productService;    
    @Autowired
    AProductMapper aProductMapper;    
    @Autowired
    AProductService aProductService;    
    @Autowired
    ManufactureMapper manufactureMapper;    
    @Autowired
    ManufactureService manufactureService;
    @Autowired
    PackMapper packMapper;    
    @Autowired
    PackService packService;
    @Autowired
    PackingMapper packingMapper;    
    @Autowired
    PackingService packingService;
    @Autowired
    ProductLocationMapper plMapper;
    @Autowired
    ProductLocationService plService;
    
    public AvailabilityServiceTest() {
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
            AProduct ap = aProductMapper.newAProduct();
            ap.setProductId(TestId.TestIdAvailServAProd);
            ap.setName("test");
            aProductService.addAProduct(ap);

            Manufacture m = manufactureMapper.newManufacture();
            m.setManufactId(TestId.TestIdAvailServManuf);
            m.setName("test");
            m.setAddress("test");
            manufactureService.addManufacture(m);

            Pack pack = packMapper.newPack();
            pack.setPackId(TestId.TestIdAvailServPack);
            pack.setName("test");
            packService.addPack(pack);

            Packing packing = packingMapper.newPacking();
            packing.setPackingId(TestId.TestIdAvailServPacking);
            packing.setPackId(pack);
            packingService.addPacking(packing);

            Product p = productMapper.newProduct();
            p.setBarcode(TestId.TestIdAvailServProd);
            p.setProductId(ap);
            p.setManufactId(m);
            p.setPackingId(packing);
            p.setPrice(BigDecimal.ONE);
            productService.addProduct(p);

            ProductLocation pl = plMapper.newProductLocation();
            pl.setLocationId(TestId.TestIdAvailServLocation);
            pl.setName("test");
            plService.addProductLocation(pl);

            Availability a = availMapper.newAvailability();
            a.setId(TestId.TestIdAvailServAvail);
            a.setBarcode(p);
            a.setLocationId(pl);
            availService.addAvailability(a);
        } catch (Exception e) {
            logger.debug("Exception:" + e.getCause() + e.getMessage());
        }
    }
    
    @After
    public void tearDown() {
        availService.delAvailability(TestId.TestIdAvailServAvail);
        productService.delProductFull(TestId.TestIdAvailServProd);
        packingService.delPackingFull(TestId.TestIdAvailServPacking);
        packService.delPackFull(TestId.TestIdAvailServPack);
        manufactureService.delManufactureFull(TestId.TestIdAvailServManuf);
        aProductService.delAProductFull(TestId.TestIdAvailServAProd); 
        plService.delProductLocationFull(TestId.TestIdAvailServLocation);
    }

    /**
     * Test of testGetAllAvailabilities method, of class AvailabilityService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllAvailabilities() throws Exception {
        logger.debug("Test - getAllAvailabilities");
        int expResult = 1;
        int result = availService.getAllAvailabilities().size();
        assert(expResult <= result);
    }

    /**
     * Test of getAvailabilityById method, of class AvailabilityService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAvailabilityById() throws Exception {
        logger.debug("Test - GetAvailabilityById");
        Availability expResult = null;
        Availability result = 
                availService.getAvailabilityById(TestId.TestIdAvailServAvail);
        assert(expResult != result);
    }
    
    /**
     * Test of deleteAvailability method, of class AvailabilityService.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteAvailability() throws Exception {
        logger.debug("Test - DeleteAvailability(");

        availService.delAvailability(TestId.TestIdAvailServAvail);
        Availability result = 
                availService.getAvailabilityById(TestId.TestIdAvailServAvail);
        Availability expResult = null;
        assertEquals(expResult, result);
    }
    
}

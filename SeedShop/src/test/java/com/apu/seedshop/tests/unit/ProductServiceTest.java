/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.tests.unit;

import com.apu.seedshop.jpa.AProduct;
import com.apu.seedshop.jpa.Manufacture;
import com.apu.seedshop.jpa.Pack;
import com.apu.seedshop.jpa.Packing;
import com.apu.seedshop.jpa.Product;
import com.apu.seedshop.services.AProductMapper;
import com.apu.seedshop.services.AProductService;
import com.apu.seedshop.services.ManufactureMapper;
import com.apu.seedshop.services.ManufactureService;
import com.apu.seedshop.services.PackMapper;
import com.apu.seedshop.services.PackService;
import com.apu.seedshop.services.PackingMapper;
import com.apu.seedshop.services.PackingService;
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
public class ProductServiceTest {
    private static final Logger logger =  LoggerFactory.getLogger(ProductServiceTest.class);
    
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
    
    public ProductServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        AProduct ap = aProductMapper.newAProduct();
        ap.setProductId(TestId.TestIdProdServAProd);
        ap.setName("test");
        aProductService.addAProduct(ap);
        
        Manufacture m = manufactureMapper.newManufacture();
        m.setManufactId(TestId.TestIdProdServManuf);
        m.setName("test");
        m.setAddress("test");
        manufactureService.addManufacture(m);
        
        Pack pack = packMapper.newPack();
        pack.setPackId(TestId.TestIdProdServPack);
        pack.setName("test");
        packService.addPack(pack);
        
        Packing packing = packingMapper.newPacking();
        packing.setPackingId(TestId.TestIdProdServPacking);
        packing.setPackId(pack);
        packingService.addPacking(packing);
        
        Product p = new Product();
        p.setBarcode(TestId.TestIdProdServBarcode);
        p.setProductId(ap);
        p.setManufactId(m);
        p.setPackingId(packing);
        p.setPrice(BigDecimal.ONE);
        productService.addProduct(p);
    }
    
    @After
    public void tearDown() {
        productService.delTestProduct(TestId.TestIdProdServBarcode);
        packingService.delTestPacking(TestId.TestIdProdServPacking);
        packService.delPackFull(TestId.TestIdProdServPack);
        manufactureService.delTestManufacture(TestId.TestIdProdServManuf);
        aProductService.delTestAProduct(TestId.TestIdProdServAProd);      
    }

    /**
     * Test of getAllProducts method, of class ProductService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllProducts() throws Exception {
        logger.debug("Test - getAllProducts");
        int expResult = 2;
        int result = productService.getAllProducts().size();
        assert(expResult <= result);
    }

    /**
     * Test of getProductById method, of class ProductService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetProductByBarcode() throws Exception {
        logger.debug("Test - getProductByBarcode");
        Product expResult = null;
        Product result = 
                productService.getProductByBarcode(TestId.TestIdProdServBarcode);
        assert(expResult != result);
    }
    
}

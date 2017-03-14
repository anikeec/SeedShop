/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.tests.unit;

import com.apu.seedshop.jpa.AnOrder;
import com.apu.seedshop.jpa.Invoice;
import com.apu.seedshop.services.DeliveryStatusService;
import com.apu.seedshop.services.InvoiceService;
import com.apu.seedshop.services.UserService;
import java.util.ArrayList;
import java.util.Date;
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
public class InvoiceServiceTest {
    private static final Logger logger =  LoggerFactory.getLogger(InvoiceServiceTest.class);
    Long testId = 100l;
    Long testUserId = 1l;
    
    @Autowired
    InvoiceService invoiceService;
    
    @Autowired
    DeliveryStatusService dStatService;
    
    @Autowired
    UserService userService; 
    
    public InvoiceServiceTest() {
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
     * Test of getAllInvoices method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllInvoices() throws Exception {
        logger.debug("Test - getAllInvoices");
        int expResult = 2;
        int result = invoiceService.getAllInvoices().size();
        assert(expResult <= result);
    }

    /**
     * Test of getInvoiceByOrderId method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetInvoiceByOrderId() throws Exception {
        logger.debug("Test - getInvoiceByOrderId");
        Long orderId = 1l;
        Invoice expResult = null;
        Invoice result = invoiceService.getInvoiceByOrderId(orderId);
        assert(expResult != result);
    }

    /**
     * Test of addInvoice method, of class InvoiceService.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddInvoice() throws Exception {
        logger.debug("Test - addInvoice");
        Invoice inv = new Invoice();
        inv.setOrderId(testId);
        inv.setUserId(userService.getUserById(testUserId));
        inv.setStatusId(dStatService.getDeliveryStatusById(0));
        //inv.setAnOrderCollection(new ArrayList<AnOrder>());
        //inv.setInvoiceCollection(new ArrayList<Invoice>());
        inv.setOrderDate(new Date());
        Invoice expResult = inv;
        invoiceService.addInvoice(inv);
        Invoice result = invoiceService.getInvoiceByOrderId(testId);
        assertEquals(expResult, result);        
        invoiceService.delInvoice(testId);
        result = invoiceService.getInvoiceByOrderId(testId);
        expResult = null;
        assertEquals(expResult, result);
    }
    
}

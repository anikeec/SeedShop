/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.tests.unit;

import com.apu.seedshop.jpa.UserGender;
import com.apu.seedshop.services.UserGenderMapper;
import com.apu.seedshop.services.UserGenderService;
import com.apu.seedshop.tests.TestId;
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
public class UserGenderServiceTest {
    private static final Logger logger =  LoggerFactory.getLogger(UserGenderServiceTest.class);
    
    @Autowired
    UserGenderMapper ugMapper;
    @Autowired
    UserGenderService ugService;
    
    public UserGenderServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        createTestUserGender(TestId.TestIdUserGenderServUG);
    }
    
    @After
    public void tearDown() {
        removeTestUserGender(TestId.TestIdUserGenderServUG);
    }
    
    public UserGender createTestUserGender(Integer id) {
        UserGender ug = ugMapper.newUserGender();
        ug.setGenderId(id);
        ug.setName("Test");
        UserGender res = null;
        try {
            res = ugService.addUserGender(ug);
        } catch(Exception e) {
            logger.debug("Exception:" + e.getCause() + e.getMessage());
        }
        return res;
    }
    
    public void removeTestUserGender(Integer id) {
        ugService.delUserGenderFull(id);
    }

    /**
     * Test of getAll method, of class UserGenderService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAll() throws Exception {
        logger.debug("Test - getAll");
        int expResult = 1;
        int result = ugService.getAll().size();
        assert(expResult <= result);
    }

    /**
     * Test of getUserGenderById method, of class UserGenderService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetUserGenderById() throws Exception {
        logger.debug("Test - getUserGenderById");
        UserGender expResult = null;
        UserGender result = 
            ugService.getUserGenderById(TestId.TestIdUserGenderServUG);
        assert(expResult != result);
    }

    /**
     * Test of findUserGenderByName method, of class UserGenderService.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindUserGenderByName() throws Exception {
        logger.debug("Test - findUserGenderByName");
        String name = "T";
        int expResult = 1;
        int result = ugService.findUserGenderByName(name).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of addUserGender method, of class UserGenderService.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddUserGender() throws Exception {
        logger.debug("Test - addUserGender");
        
        UserGender ug = 
            createTestUserGender(TestId.TestIdUserGenderServUGNew);        
        UserGender expResult = ug;
        ugService.addUserGender(ug);
        UserGender result = 
            ugService.getUserGenderById(TestId.TestIdUserGenderServUGNew);        
        assertEquals(expResult, result); 
        
        ugService.delUserGender(TestId.TestIdUserGenderServUGNew);
        result = ugService.getUserGenderById(TestId.TestIdUserGenderServUGNew);
        if(result.getUsed() == false) {
            result = null;
        }
        expResult = null;
        assertEquals(expResult, result);
        
        ugService.delUserGenderFull(TestId.TestIdUserGenderServUGNew);
        result = ugService.getUserGenderById(TestId.TestIdUserGenderServUGNew);
        expResult = null;
        assertEquals(expResult, result);
    }
    
}

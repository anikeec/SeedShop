/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.tests.unit;


import com.apu.seedshop.jpa.Appuser;
import com.apu.seedshop.jpa.UserAuthorization;
import com.apu.seedshop.repository.AppuserRepository;
import com.apu.seedshop.services.UserAuthorizationService;
import com.apu.seedshop.services.UserService;
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
public class UserAuthorizationServiceTest {
    private static final Logger logger =  LoggerFactory.getLogger(UserAuthorizationServiceTest.class);
    Long testIdRead = 1l;
    Long testId = 100l;
    
    @Autowired
    UserAuthorizationService uaService;
    
    @Autowired
    UserService userService;
    
    public UserAuthorizationServiceTest() {
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
     * Test of getAllUserAuthorizations method, of class UserAuthorizationService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllUserAuthorizations() throws Exception {
        logger.debug("Test - getAllUserAuthorizations");
        int expResult = 2;
        int result = uaService.getAllUserAuthorization().size();
        assert(expResult <= result);
    }

    /**
     * Test of getUserAuthorizationById method, of class UserAuthorizationService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetUserAuthorizationById() throws Exception {
        logger.debug("Test - getUserAuthorizationById");
        UserAuthorization expResult = null;
        UserAuthorization result = uaService.getUserAuthorizationById(testIdRead);
        assert(expResult != result);
    }

    /**
     * Test of addUserAuthorization method, of class UserAuthorizationService.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddUserAuthorization() throws Exception {
        UserAuthorization result;
        logger.debug("Test - addUserAuthorization");
        UserAuthorization ua = new UserAuthorization();
        ua.setAuthId(testId);
        ua.setLogin("Test name");
        ua.setPasswdHash("Test address.");
        ua.setUsed(true);
        ua.setUserId(userService.getUserById(testIdRead));
        UserAuthorization expResult = ua;
        uaService.addUserAuthorization(ua);
        result = uaService.getUserAuthorizationById(testId);        
        assertEquals(expResult, result);
        uaService.delUserAuthorization(testId);
        result = uaService.getUserAuthorizationById(testId);
        if(result.getUsed() == false) {
            result = null;
        }
        expResult = null;
        assertEquals(expResult, result); 
        uaService.delTestUserAuthorization(testId);
        result = uaService.getUserAuthorizationById(testId);
        expResult = null;
        assertEquals(expResult, result);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.tests.unit;

import com.apu.seedshop.jpa.Appuser;
import com.apu.seedshop.jpa.UserGender;
import com.apu.seedshop.repository.InvoiceRepository;
import com.apu.seedshop.services.UserGenderService;
import com.apu.seedshop.services.UserService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {
    private static final Logger logger =  LoggerFactory.getLogger(UserServiceTest.class);
    Integer genderId = 1;
    Long userId = 100l;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserGenderService ugService;
    
    public UserServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //UserGender ug = new UserGender(genderId,"M");
        //ugService.addUserGender(ug);
    }
    
    @After
    public void tearDown() {
        //ugService.delUserGender(genderId);
    }

    /**
     * Test of getAllUsers method, of class UsersService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllUsers() throws Exception {
        logger.debug("Test - getAllUsers");
        List<Appuser> result = userService.getAllUsers();
        int count = result.size();
        int expCount = 2;
        assert(expCount <= count);
    }

    /**
     * Test of getUserById method, of class UsersService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetUserById() throws Exception {
        logger.debug("Test - getUserById");
        Long id = 2l;
        Appuser notExpResult = null;
        Appuser result = userService.getUserById(id);
        assert(result != notExpResult);
    }

    /**
     * Test of findUserByName method, of class UsersService.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindUserByName() throws Exception {
        logger.debug("Test - findUserByName");
        String name = "пет";
        Long expResult = 1l;
        List<Appuser> result = userService.findUserByName(name);
        assertEquals(result.get(0).getUserId(), expResult);
    }

    /**
     * Test of addUser method, of class UsersService.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddUser() throws Exception {
        logger.debug("Test - addUser");       
        Appuser u = new Appuser();
        u.setUserId(userId);
        u.setSecName("A");
        u.setFirstName("P");
        u.setThirdName("Y");
        u.setEmail("apu");
        u.setPhones("3");
        u.setDiscount(new BigDecimal("0.5"));         
        u.setBirthday(null);
        u.setCountry("");
        u.setRegion("");
        u.setArea("");
        u.setCity("");
        u.setGenderId(ugService.getUserGenderById(genderId));        
        Appuser expResult = u;
        userService.addUser(u);
        Appuser result = userService.getUserById(userId);
        assertEquals(expResult, result);
        userService.delUser(userId);
        result = userService.getUserById(userId);
        expResult = null;
        assertEquals(expResult, result);
    }
    
}

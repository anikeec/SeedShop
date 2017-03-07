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
    Integer userId = 100;
    
    @Autowired
    private UserService usersService;
    
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
        List<Appuser> result = usersService.getAllUsers();
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
        Integer id = 2;
        Appuser notExpResult = null;
        Appuser result = usersService.getUserById(id);
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
        int expResult = 1;
        List<Appuser> result = usersService.findUserByName(name);
        assert(result.get(0).getUserId() == expResult);
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
        usersService.addUser(u);
        Appuser result = usersService.getUserById(userId);
        assertEquals(expResult, result);
        usersService.delUser(userId);
        result = usersService.getUserById(userId);
        expResult = null;
        assertEquals(expResult, result);
    }
    
}

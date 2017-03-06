/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.tests.unit;

import com.apu.seedshop.jpa.User;
import com.apu.seedshop.services.UserService;
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

public class UsersServiceTest {
    private static final Logger logger =  LoggerFactory.getLogger(UsersServiceTest.class);
    
    @Autowired
    private UserService usersService;
    
    public UsersServiceTest() {
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
     * Test of getAllUsers method, of class UsersService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllUsers() throws Exception {
        logger.debug("Test - getAllUsers");
        List<User> result = usersService.getAllUsers();
        int count = result.size();
        int expCount = 2;
        assertEquals(expCount, count);
        //fail("The test case is a prototype.");
        logger.debug("OK");
    }

    /**
     * Test of getUserById method, of class UsersService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetUserById() throws Exception {
        logger.debug("Test - getUserById");
        Integer id = 2;
        User notExpResult = null;
        User result = usersService.getUserById(id);
        assert(result != notExpResult);
        //fail("The test case is a prototype.");
        logger.debug("OK");
    }

    /**
     * Test of findUserByName method, of class UsersService.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindUserByName()  throws Exception {
        System.out.println("findUserByName");
        String name = "пет";
        int expResult = 1;
        List<User> result = usersService.findUserByName(name);
        assert(result.get(0).getUserId() == expResult);
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addUser method, of class UsersService.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        User u = null;
        UserService instance = new UserService();
        User expResult = null;
        User result = instance.addUser(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delUser method, of class UsersService.
     */
    @Test
    public void testDelUser() {
        System.out.println("delUser");
        Integer id = null;
        UserService instance = new UserService();
        instance.delUser(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

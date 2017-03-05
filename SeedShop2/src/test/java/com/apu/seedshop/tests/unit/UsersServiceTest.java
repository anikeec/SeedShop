/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.tests.unit;

import com.apu.seedshop.jpa.Users;
import com.apu.seedshop.services.UsersService;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UsersServiceTest {
    
    @Autowired
    private UsersService usersService;
    
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
        System.out.println("getAllUsers");
        List<Users> result = usersService.getAllUsers();
        int count = result.size();
        int expCount = 2;
        assertEquals(expCount, count);
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getUserById method, of class UsersService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetUserById() throws Exception {
        System.out.println("getUserById");
        Integer id = 2;
        Users notExpResult = null;
        Users result = usersService.getUserById(id);
        assert(result != notExpResult);
        //fail("The test case is a prototype.");
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
        List<Users> result = usersService.findUserByName(name);
        assert(result.get(0).getUserId() == expResult);
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addUser method, of class UsersService.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        Users u = null;
        UsersService instance = new UsersService();
        Users expResult = null;
        Users result = instance.addUser(u);
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
        UsersService instance = new UsersService();
        instance.delUser(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

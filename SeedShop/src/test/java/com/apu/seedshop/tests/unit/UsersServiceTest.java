/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.seedshop.tests.unit;

import com.apu.seedshop.jpa.Appuser;
import com.apu.seedshop.jpa.Invoice;
import com.apu.seedshop.jpa.UserAuthorization;
import com.apu.seedshop.jpa.UserGender;
import com.apu.seedshop.repository.InvoiceRepository;
import com.apu.seedshop.repository.UserGenderRepository;
import com.apu.seedshop.services.UserService;
import java.util.ArrayList;
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
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersServiceTest {
    private static final Logger logger =  LoggerFactory.getLogger(UsersServiceTest.class);
    
    @Autowired
    private UserService usersService;
    
    @Autowired
    private UserGenderRepository ugRepository;
    
    @Autowired
    private InvoiceRepository iRepository;
    
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
        List<Appuser> result = usersService.getAllUsers();
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
        logger.debug("Test - getUserById");
        Integer id = 2;
        Appuser notExpResult = null;
        Appuser result = usersService.getUserById(id);
        assert(result != notExpResult);
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findUserByName method, of class UsersService.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindUserByName()  throws Exception {
        logger.debug("Test - findUserByName");
        String name = "пет";
        int expResult = 1;
        List<Appuser> result = usersService.findUserByName(name);
        assert(result.get(0).getUserId() == expResult);
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addUser method, of class UsersService.
     */
    @Test
    @Transactional 
    public void testAddUser() {
        logger.debug("Test - addUser");
        
        UserGender ug = ugRepository.findByGenderId(0).get(0);//new UserGender();
        //ug.setGenderId(3);
        //ug.setName("F");
        //Appuser user = usersService.getUserById(0);
        
        Appuser u = new Appuser();
        u.setUserId(3);
        u.setSecName("Аникейчик");
        u.setFirstName("Павел");
        u.setThirdName("Юрьевич");
        u.setEmail("pasha_anik@ukr.net");
        u.setPhones("");
        u.setDiscount(0);
        
        UserAuthorization ua = new UserAuthorization();
        ua.setUserId(1);
        ua.setLogin("");
        ua.setPasswdHash("");
        ua.setAppuser(u);
        
        u.setUserAuthorization(ua);
        
        Invoice inv = iRepository.findByOrderId(1).get(0);
        
        List<Appuser> list = new ArrayList<Appuser>();
        list.add(u);
        ug.setAppuserCollection(list);      
        //ugRepository.save(ug);
        u.setGenderId(ug);
        Appuser expResult = u;
        Appuser result = usersService.addUser(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of delUser method, of class UsersService.
     */
/*    @Test
    public void testDelUser() {
        logger.debug("Test - delUser");
        Integer id = null;
        UserService instance = new UserService();
        instance.delUser(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
  */  
}

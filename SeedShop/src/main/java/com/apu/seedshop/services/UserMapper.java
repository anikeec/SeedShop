package com.apu.seedshop.services;

import com.apu.seedshop.jpa.Appuser;
import com.apu.seedshopapi.SeedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.apu.seedshop.repository.UserRepository;

@Component

public class UserMapper {
    private static final Logger logger =  LoggerFactory.getLogger(UserMapper.class);

    public static final Long LIBRARIANS_GROUP_ID = 1L;
    @Autowired
    UserRepository userRepository;
    
/**
 * Maps internal JPA model to external REST model
 * @param u internal user model
 * @return external REST user model
 */
    public SeedUser fromInternal(Appuser u) {
        SeedUser su = null;
        if (u != null) {
            su = new SeedUser();
            //Userdetails ud = u.getUserdetails();
            //lu.isLibrarian = u.getUserId() < 100;
            su.firstName = u.getFirstName();
            su.userId = u.getUserId();
            /*
            if (ud != null) {
                lu.firstName = u.getUserdetails().getFirstName();
                lu.lastName = u.getUserdetails().getLastName();
            }*/
        }
        return su;
    }

    
/**
 * Maps external REST model to internal Users;
 * If user does not exists in DB then creates new. If user already exists
 * then fetches user from DB and sets all fields from external REST model
 * @param su REST model
 * @return internal Users with all required fields set
 */
    public Appuser toInternal(SeedUser su) {
        Appuser u = null;
        //first, check if it exists
        if (su.userId != null) {
            u = userRepository.findOne(su.userId);
        }
        if (u == null) { //not found, create new
            logger.debug("Creating new user");
            //au = newUser();
        }
        logger.debug("Updating existing user");
        //au.setUsername(lu.login);
        //au.getUserdetails().setFirstName(lu.firstName);
        //au.getUserdetails().setLastName(lu.lastName);
        u.setEmail(su.email);
        return u;
    }
}

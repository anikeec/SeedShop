package com.apu.seedshop.services;

import com.apu.seedshop.jpa.Appuser;
import com.apu.seedshop.repository.UserAuthorizRepository;
import com.apu.seedshop.repository.UserGenderRepository;
import com.apu.seedshopapi.SeedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.apu.seedshop.repository.UserRepository;
import com.apu.seedshop.utils.EntityIdGenerator;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;

@Component

public class UserMapper {
    private static final Logger logger =  LoggerFactory.getLogger(UserMapper.class);

    public static final Long LIBRARIANS_GROUP_ID = 1L;
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserGenderRepository ugRepository;
    
    @Autowired
    UserAuthorizRepository uaRepository;
    
/**
 * Maps internal JPA model to external REST model
 * @param u internal user model
 * @return external REST user model
 */
    public SeedUser fromInternal(Appuser u) {
        SeedUser su = null;
        if (u != null) {
            su = new SeedUser();          
            su.userId = u.getUserId();            
            if(uaRepository.findByUserId(u.getUserId()).size() > 0) {
                su.login = uaRepository.findByUserId(u.getUserId()).get(0).getLogin();
            }
            su.firstName = u.getFirstName();
            su.secName = u.getSecName();
            su.thirdName = u.getThirdName();
            su.email = u.getEmail();
            su.phones = u.getPhones();
            su.discount = "" + u.getDiscount();
            su.birthday = "" + u.getBirthday();            
            su.gender = u.getGenderId().getName();
            su.country = u.getCountry();
            su.region = u.getRegion();
            su.area = u.getArea();
            su.city = u.getCity();         
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
            u = new Appuser();
        }
        logger.debug("Updating existing user");
        u.setUserId(su.userId);
        u.setFirstName(su.firstName);
        u.setSecName(su.secName);
        u.setThirdName(su.thirdName);
        DateFormat format = new SimpleDateFormat("d.MM.yyyy", Locale.ENGLISH);
        Date date = null;
        try { 
            date = format.parse(su.birthday);//"15.03.1980"
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        u.setBirthday(date);
        u.setDiscount(new BigDecimal(su.discount));
        u.setEmail(su.email);
        u.setPhones(su.phones);
        u.setGenderId(ugRepository.findByName(su.gender).get(0));
        u.setCountry(su.country);
        u.setRegion(su.region);
        u.setArea(su.area);
        u.setCity(su.city);
        return u;
    }
}

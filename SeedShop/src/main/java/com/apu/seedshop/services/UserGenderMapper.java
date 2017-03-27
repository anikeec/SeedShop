package com.apu.seedshop.services;

import com.apu.seedshop.jpa.UserGender;
import com.apu.seedshop.repository.UserGenderRepository;
import com.apu.seedshopapi.SeedUserGender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserGenderMapper {
    private static final Logger logger =  LoggerFactory.getLogger(UserGenderMapper.class);

    public static final Long LIBRARIANS_GROUP_ID = 1L;
    
    @Autowired
    UserGenderRepository ugRepository;
    
/**
 * Maps internal JPA model to external REST model
 * @param inp internal UserGender model
 * @return external REST SeedUserGender model
 */
    public SeedUserGender fromInternal(UserGender inp) throws IllegalArgumentException {
        if(inp == null) throw new IllegalArgumentException("fromInternal. inp = null");
        SeedUserGender result = new SeedUserGender();          
        result.genderId = inp.getGenderId();
        result.name = inp.getName();
        result.used = inp.getUsed().toString();
        return result;
    }
    
    /**
 * Creates new UserGender with good Id
 * @return newly created UserGender with required fields set
 */
    public UserGender newUserGender() {
        UserGender item = new UserGender();
        boolean idOK = false;
        int id = 0;
        while (!idOK) {
            id++;
            idOK = !ugRepository.exists(id);
        }
        item.setGenderId(id);
        item.setUsed(true);  
        return item;
    }
    
/**
 * Maps external REST model to internal UserGender;
 * If user does not exists in DB then creates new. If user already exists
 * then fetches user from DB and sets all fields from external REST model
 * @param sug REST model
 * @return internal UserGender with all required fields set
 */
    public UserGender toInternal(SeedUserGender sug) throws IllegalArgumentException {
        UserGender ug = null;
        if(sug == null) 
            throw new IllegalArgumentException("UserGenderMapper. toInternal. input = null");
        if(sug.name == null) 
            throw new IllegalArgumentException("UserGenderName = null");
        
        if (sug.genderId != null) {    //first, check if it exists
            ug = ugRepository.findOne(sug.genderId);            
        }
        if(ug == null){                  //not found, create new
            logger.debug("Creating new UserGender");
            ug = newUserGender();
            if(sug.genderId != null)    ug.setGenderId(sug.genderId);
        } else {
            logger.debug("Updating existing UserGender");
        }        
        ug.setName(sug.name);
        if(sug.used != null)    ug.setUsed(sug.used.equals("true"));
        
        return ug;
    }

}

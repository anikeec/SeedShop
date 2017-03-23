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

}

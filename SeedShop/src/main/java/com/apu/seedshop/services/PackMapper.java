package com.apu.seedshop.services;

import com.apu.seedshop.jpa.Pack;
import com.apu.seedshop.repository.PackRepository;
import com.apu.seedshopapi.SeedPack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PackMapper {
    private static final Logger logger =  LoggerFactory.getLogger(PackMapper.class);

    public static final Long LIBRARIANS_GROUP_ID = 1L;
    
    @Autowired
    PackRepository pRepository;
    
/**
 * Maps internal JPA model to external REST model
 * @param inp internal Pack model
 * @return external REST SeedPack model
 */
    public SeedPack fromInternal(Pack inp) throws IllegalArgumentException {
        if(inp == null) 
            throw new IllegalArgumentException("PackMapper. fromInternal. input = null");
        SeedPack result = new SeedPack();          
        result.packId = inp.getPackId();
        result.name = inp.getName();
        result.used = inp.getUsed().toString();
        return result;
    }
    
    /**
 * Creates new Pack with good Id
 * @return newly created Pack with required fields set
 */
    public Pack newPack() {
        Pack item = new Pack();
        boolean idOK = false;
        int id = 0;
        while (!idOK) {
            id++;
            idOK = !pRepository.exists(id);
        }
        item.setPackId(id);
        item.setUsed(true);  
        return item;
    }
    
/**
 * Maps external REST model to internal Pack;
 * If user does not exists in DB then creates new. If user already exists
 * then fetches user from DB and sets all fields from external REST model
 * @param sp REST model
 * @return internal Pack with all required fields set
 */
    public Pack toInternal(SeedPack sp) throws IllegalArgumentException {
        Pack p = null;
        if(sp == null) 
            throw new IllegalArgumentException("PackMapper. toInternal. input = null");
        
        if (sp.packId != null) {    //first, check if it exists
            p = pRepository.findOne(sp.packId);            
        }
        if(p == null){              //not found, create new
            logger.debug("Creating new pack");
            p = newPack();
            if(sp.packId != null)
                p.setPackId(sp.packId);
        } else {
            logger.debug("Updating existing pack");
        }        

        p.setName(sp.name);
        if(sp.used != null)
            p.setUsed(sp.used.equals("true"));
        
        return p;
    }

}

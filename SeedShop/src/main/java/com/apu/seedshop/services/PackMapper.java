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
    PackRepository mRepository;
    
/**
 * Maps internal JPA model to external REST model
 * @param inp internal Pack model
 * @return external REST SeedPack model
 */
    public SeedPack fromInternal(Pack inp) throws IllegalArgumentException {
        if(inp == null) throw new IllegalArgumentException("fromInternal. inp = null");
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
            idOK = !mRepository.exists(id);
        }
        item.setPackId(id);
        item.setUsed(true);  
        return item;
    }

}

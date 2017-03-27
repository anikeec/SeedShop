package com.apu.seedshop.services;

import com.apu.seedshop.jpa.Packing;
import com.apu.seedshop.repository.PackingRepository;
import com.apu.seedshopapi.SeedPacking;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PackingMapper {
    private static final Logger logger =  LoggerFactory.getLogger(PackingMapper.class);

    public static final Long LIBRARIANS_GROUP_ID = 1L;
    
    @Autowired
    PackingRepository pRepository;
    
    @Autowired
    PackService packService;
    
/**
 * Maps internal JPA model to external REST model
 * @param inp internal Packing model
 * @return external REST SeedPacking model
 */
    public SeedPacking fromInternal(Packing inp) throws IllegalArgumentException {
        if(inp == null) 
            throw new IllegalArgumentException("PackingMapper. fromInternal. input = null");
        SeedPacking result = new SeedPacking();          
        result.packingId = inp.getPackingId();
        result.packId = inp.getPackId().getPackId();
        result.amount = inp.getAmount();
        result.weight = "" + inp.getWeight();        
        result.used = inp.getUsed().toString();
        return result;
    }
    
    /**
 * Creates new Packing with good Id
 * @return newly created Packing with required fields set
 */
    public Packing newPacking() {
        Packing item = new Packing();
        boolean idOK = false;
        int id = 0;
        while (!idOK) {
            id++;
            idOK = !pRepository.exists(id);
        }
        item.setPackingId(id);
        item.setAmount(0);
        item.setWeight(new BigDecimal(0));
        item.setUsed(true); 
        item.setPackId(null);
        return item;
    }
    
/**
 * Maps external REST model to internal Packing;
 * If user does not exists in DB then creates new. If user already exists
 * then fetches user from DB and sets all fields from external REST model
 * @param sp REST model
 * @return internal Packing with all required fields set
 */
    public Packing toInternal(SeedPacking sp) throws IllegalArgumentException {
        Packing packing = null;
        if(sp == null) 
            throw new IllegalArgumentException("PackingMapper. toInternal. input = null");
        if(sp.packId == null) 
            throw new IllegalArgumentException("packId = null");
        
        if (sp.packingId != null) {     //first, check if it exists
            packing = pRepository.findOne(sp.packingId);            
        }
        if(packing == null){            //not found, create new
            logger.debug("Creating new packing");
            packing = newPacking();
            if(sp.packingId != null)    packing.setPackingId(sp.packingId);
        } else {
            logger.debug("Updating existing packing");
        }      
        
        packing.setAmount(sp.amount);
        
        if(sp.weight != null) {
            packing.setWeight(new BigDecimal(sp.weight));
        } else {
            packing.setWeight(null);
        }
        
        packing.setPackId(packService.getPackById(sp.packId));

        if(sp.used != null)
            packing.setUsed(sp.used.equals("true"));
        
        return packing;
    }

}

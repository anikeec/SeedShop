package com.apu.seedshop.services;

import com.apu.seedshop.jpa.ProductLocation;
import com.apu.seedshop.repository.ProductLocationRepository;
import com.apu.seedshopapi.SeedProductLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductLocationMapper {
    private static final Logger logger =  LoggerFactory.getLogger(ProductLocationMapper.class);

    public static final Long LIBRARIANS_GROUP_ID = 1L;
    
    @Autowired
    ProductLocationRepository plRepository;
    
/**
 * Maps internal JPA model to external REST model
 * @param inp internal ProductLocation model
 * @return external REST SeedProductLocation model
 */
    public SeedProductLocation fromInternal(ProductLocation inp) throws IllegalArgumentException {
        if(inp == null) throw new IllegalArgumentException("fromInternal. inp = null");
        SeedProductLocation result = new SeedProductLocation();          
        result.locationId = inp.getLocationId();
        result.name = inp.getName();
        result.used = inp.getUsed().toString();
        return result;
    }
    
    /**
 * Creates new ProductLocation with good Id
 * @return newly created ProductLocation with required fields set
 */
    public ProductLocation newProductLocation() {
        ProductLocation item = new ProductLocation();
        boolean idOK = false;
        int id = 0;
        while (!idOK) {
            id++;
            idOK = !plRepository.exists(id);
        }
        item.setLocationId(id);
        item.setUsed(true);  
        return item;
    }
    
/**
 * Maps external REST model to internal ProductLocation;
 * If user does not exists in DB then creates new. If user already exists
 * then fetches user from DB and sets all fields from external REST model
 * @param spl REST model
 * @return internal ProductLocation with all required fields set
 */
    public ProductLocation toInternal(SeedProductLocation spl) throws IllegalArgumentException {
        ProductLocation p = null;
        if(spl == null) 
            throw new IllegalArgumentException("ProductLocationMapper. toInternal. input = null");
        if(spl.name == null) 
            throw new IllegalArgumentException("ProductLocationName = null");
        
        if (spl.locationId != null) {    //first, check if it exists
            p = plRepository.findOne(spl.locationId);            
        }
        if(p == null){                  //not found, create new
            logger.debug("Creating new ProductLocation");
            p = newProductLocation();
            if(spl.locationId != null)
                p.setLocationId(spl.locationId);
        } else {
            logger.debug("Updating existing ProductLocation");
        }        
        p.setName(spl.name);
        if(spl.used != null)
            p.setUsed(spl.used.equals("true"));
        
        return p;
    }

}

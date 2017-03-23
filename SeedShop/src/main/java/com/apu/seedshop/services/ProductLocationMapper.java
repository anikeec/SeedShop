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

}

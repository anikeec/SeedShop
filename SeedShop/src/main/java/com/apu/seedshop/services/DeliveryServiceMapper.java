package com.apu.seedshop.services;

import com.apu.seedshopapi.SeedDeliveryService;
import com.apu.seedshop.jpa.DeliveryService;
import com.apu.seedshop.repository.DeliveryServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryServiceMapper {
    private static final Logger logger =  LoggerFactory.getLogger(DeliveryServiceMapper.class);

    public static final Long LIBRARIANS_GROUP_ID = 1L;
    
    @Autowired
    DeliveryServiceRepository dsRepository;
    
/**
 * Maps internal JPA model to external REST model
 * @param inp internal DeliveryService model
 * @return external REST SeedDeliveryService model
 */
    public SeedDeliveryService fromInternal(DeliveryService inp) throws IllegalArgumentException {
        if(inp == null) throw new IllegalArgumentException("fromInternal. inp = null");
        SeedDeliveryService result = new SeedDeliveryService();          
        result.deliveryId = inp.getDeliveryId();
        result.name = inp.getName();
        result.used = inp.getUsed().toString();
        return result;
    }
    
    /**
 * Creates new DeliveryService with good Id
 * @return newly created DeliveryService with required fields set
 */
    public DeliveryService newDeliveryService() {
        DeliveryService ds = new DeliveryService();
        boolean idOK = false;
        int id = 0;
        while (!idOK) {
            id++;
            idOK = !dsRepository.exists(id);
        }
        ds.setDeliveryId(id);
        ds.setUsed(true);  
        return ds;
    }

}

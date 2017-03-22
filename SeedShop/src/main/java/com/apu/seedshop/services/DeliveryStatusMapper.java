package com.apu.seedshop.services;

import com.apu.seedshopapi.SeedDeliveryStatus;
import com.apu.seedshop.jpa.DeliveryStatus;
import com.apu.seedshop.repository.DeliveryStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class DeliveryStatusMapper {
    private static final Logger logger =  LoggerFactory.getLogger(DeliveryStatusMapper.class);

    public static final Long LIBRARIANS_GROUP_ID = 1L;
    
    @Autowired
    DeliveryStatusRepository dsRepository;
    
/**
 * Maps internal JPA model to external REST model
 * @param inp internal DeliveryStatus model
 * @return external REST SeedDeliveryStatus model
 */
    public SeedDeliveryStatus fromInternal(DeliveryStatus inp) throws IllegalArgumentException {
        if(inp == null) throw new IllegalArgumentException("fromInternal. inp = null");
        SeedDeliveryStatus result = new SeedDeliveryStatus();          
        result.statusId = inp.getStatusId();
        result.status = inp.getStatus();
        result.used = inp.getUsed().toString();
        return result;
    }
    
    /**
 * Creates new DeliveryStatus with good Id
 * @return newly created DeliveryStatus with required fields set
 */
    public DeliveryStatus newDeliveryStatus() {
        DeliveryStatus ds = new DeliveryStatus();
        boolean idOK = false;
        int id = 0;
        while (!idOK) {
            id++;
            idOK = !dsRepository.exists(id);
        }
        ds.setStatusId(id);
        ds.setUsed(true);  
        return ds;
    }

}

package com.apu.seedshop.services;

import com.apu.seedshop.jpa.Availability;
import com.apu.seedshop.repository.AvailabilityRepository;
import com.apu.seedshopapi.SeedAvailability;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityMapper {
    private static final Logger logger =  LoggerFactory.getLogger(AvailabilityMapper.class);

    public static final Long LIBRARIANS_GROUP_ID = 1L;
    
    @Autowired
    AvailabilityRepository avRepository;
    
/**
 * Maps internal JPA model to external REST model
 * @param inp internal Availability model
 * @return external REST SeedAvailability model
 */
    public SeedAvailability fromInternal(Availability inp) throws IllegalArgumentException {
        if(inp == null) throw new IllegalArgumentException("fromInternal. inp = null");
        SeedAvailability result = new SeedAvailability();          
        result.id = inp.getId();
        result.barcode = inp.getBarcode().getBarcode();
        result.locationId = inp.getLocationId().getLocationId();
        result.available= inp.getAvailable();
        result.reserv = inp.getReserv();
        return result;
    }
    
    /**
 * Creates new Availability with good Id
 * @return newly created Availability with required fields set
 */
    public Availability newAvailability() {
        Availability item = new Availability();
        boolean idOK = false;
        int id = 0;
        while (!idOK) {
            id++;
            idOK = !avRepository.exists(id);
        }
        item.setId(id);
        item.setAvailable(0);
        item.setReserv(0);
        return item;
    }

}

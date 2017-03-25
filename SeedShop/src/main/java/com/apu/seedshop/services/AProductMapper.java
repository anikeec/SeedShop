package com.apu.seedshop.services;

import com.apu.seedshop.jpa.Packing;
import com.apu.seedshop.repository.PackingRepository;
import com.apu.seedshopapi.SeedPacking;
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
    
/**
 * Maps internal JPA model to external REST model
 * @param inp internal Packing model
 * @return external REST SeedPacking model
 */
    public SeedPacking fromInternal(Packing inp) throws IllegalArgumentException {
        if(inp == null) throw new IllegalArgumentException("fromInternal. inp = null");
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
        item.setUsed(true);  
        return item;
    }

}

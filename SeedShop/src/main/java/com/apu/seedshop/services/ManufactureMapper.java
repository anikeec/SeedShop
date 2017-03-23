package com.apu.seedshop.services;

import com.apu.seedshop.jpa.Manufacture;
import com.apu.seedshop.repository.ManufactureRepository;
import com.apu.seedshopapi.SeedManufacture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManufactureMapper {
    private static final Logger logger =  LoggerFactory.getLogger(ManufactureMapper.class);

    public static final Long LIBRARIANS_GROUP_ID = 1L;
    
    @Autowired
    ManufactureRepository mRepository;
    
/**
 * Maps internal JPA model to external REST model
 * @param inp internal Manufacture model
 * @return external REST SeedManufacture model
 */
    public SeedManufacture fromInternal(Manufacture inp) throws IllegalArgumentException {
        if(inp == null) throw new IllegalArgumentException("fromInternal. inp = null");
        SeedManufacture result = new SeedManufacture();          
        result.manufactureId = inp.getManufactId();
        result.name = inp.getName();
        result.adress = inp.getAddress();
        result.used = inp.getUsed().toString();
        return result;
    }
    
    /**
 * Creates new Manufacture with good Id
 * @return newly created Manufacture with required fields set
 */
    public Manufacture newManufacture() {
        Manufacture item = new Manufacture();
        boolean idOK = false;
        int id = 0;
        while (!idOK) {
            id++;
            idOK = !mRepository.exists(id);
        }
        item.setManufactId(id);
        item.setUsed(true);  
        return item;
    }

}

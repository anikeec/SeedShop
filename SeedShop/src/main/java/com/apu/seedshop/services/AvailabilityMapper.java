package com.apu.seedshop.services;

import com.apu.seedshop.jpa.Availability;
import com.apu.seedshop.jpa.Product;
import com.apu.seedshop.jpa.ProductLocation;
import com.apu.seedshop.repository.AvailabilityRepository;
import com.apu.seedshopapi.SeedAvailability;
import java.util.List;
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
    @Autowired
    ProductService productService;
    @Autowired
    ProductLocationService productLocationService;
    
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
    
 /**
 * Maps external REST model to internal Availability;
 * If user does not exists in DB then creates new. If user already exists
 * then fetches user from DB and sets all fields from external REST model
 * @param sav REST model
 * @return internal Availability with all required fields set
 */
    public Availability toInternal(SeedAvailability sav) 
            throws IllegalArgumentException{
        Availability availability = null;
        Product product = null;
        ProductLocation productL = null;
        List<Availability> avList = null;
        if(sav == null) 
            throw new IllegalArgumentException("AvailabilityMapper. toInternal. input = null");
        if(sav.barcode == null) 
            throw new IllegalArgumentException("AvailabilityBarcode = null");
        if(sav.locationId == null) 
            throw new IllegalArgumentException("AvailabilityLocationId = null");
        
        try {
            product = productService.getProductByBarcode(sav.barcode);                 
        } catch (Exception e) {
            throw new IllegalArgumentException("Error: product with current Barcode " +
                    sav.barcode + " is absent!");
        } 
        
        try {
            productL = productLocationService.getProductLocationById(sav.locationId);                 
        } catch (Exception e) {
            throw new IllegalArgumentException("Error: product with current Location " +
                    sav.locationId + " is absent!");
        }
          //first, check if it exists
             
        avList = (List<Availability>)product.getAvailabilityCollection();
        
        if(avList != null) {        
            if(!avList.isEmpty()) {
                logger.debug("Updating existing Availability");            
                for(Availability avail:avList) {
                    if(avail.getLocationId().getLocationId() == sav.locationId) {                        
                        availability = avail;                        
                        break;
                    }
                } 
            }
        }
        
        if(availability == null){  //not found, create new
            logger.debug("Creating new Availability");
            availability = newAvailability();
            availability.setBarcode(product);
            if(sav.id != null)    availability.setId(sav.id);
            availability.setLocationId(productL);
        }
        
        setAvailability(availability, sav.available, sav.reserv);
        
        return availability;    
            
    } 
    
    private void setAvailability(Availability av, Integer avail, Integer reserv) {
        if((avail == null)||(reserv == null)) 
            throw new IllegalArgumentException("AvailabilityAvailable || "
                    + "AvailabilityReserve = null");
        av.setAvailable(avail);
        av.setReserv(reserv);        
    }

    private void updateAvailability(Availability av, Integer avail, Integer reserv) {
        if((avail == null)&&(reserv == null)) 
            throw new IllegalArgumentException("AvailabilityAvailable && "
                    + "AvailabilityReserve = null");
        if(avail != null)
            av.setAvailable(av.getAvailable() + avail);
        if(reserv != null)
            av.setReserv(av.getReserv() + reserv);        
    }

}

/*
 * 
 * 
 */
package com.apu.seedshop.services;
import com.apu.seedshop.jpa.ProductLocation;
import com.apu.seedshop.repository.ProductLocationRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductLocationService {
private static final Logger logger =  LoggerFactory.getLogger(ProductLocationService.class);   

@Autowired
ProductLocationRepository plRepository;

  
    public List<ProductLocation> getAllProductLocations(){
        return  plRepository.findAll();
    }

    public ProductLocation getProductLocationById(Integer plId) throws IllegalArgumentException {
        if(plId == null)    throw new IllegalArgumentException("plId = null");
        ProductLocation p = plRepository.findOne(plId);
        return p;
    }
    
    public ProductLocation addProductLocation(ProductLocation p) throws IllegalArgumentException { 
        if(p == null)    throw new IllegalArgumentException("p = null");
        logger.debug(String.format("Adding ProductLocation %s with id %s", 
                        p.getName(), p.getLocationId()));
        p = plRepository.save(p);
        return p;
    }
    
    public void delProductLocation(Integer packId) throws IllegalArgumentException {
        if(packId == null)    throw new IllegalArgumentException("packId = null");
        ProductLocation p = plRepository.findOne(packId);
        if(p!=null){
            logger.debug(String.format("Deleting ProductLocation with id %s", packId));
            p.setUsed(false);
            plRepository.save(p);
        } else {
            logger.error("Not found productLocation with id = " + packId);
            throw new IllegalArgumentException("Not found productLocation with id = " + packId);
        }
    }
    
    public void delProductLocationFull(Integer packId){
        ProductLocation p = plRepository.findOne(packId);
        if(p!=null){
            logger.debug(String.format("Deleting ProductLocation full with id %s", packId));
            plRepository.delete(packId);
        }
    }
    
}

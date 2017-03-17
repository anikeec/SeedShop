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

    public ProductLocation getProductLocationById(Integer plId) {
        ProductLocation p = plRepository.findOne(plId);
        return p;
    }
    
    public ProductLocation addProductLocation(ProductLocation p) {        
        logger.debug(String.format("Adding ProductLocation %s with id %s", 
                        p.getName(), p.getLocationId()));
        p = plRepository.save(p);
        return p;
    }
    
    public void delProductLocation(Integer packId){
        ProductLocation p = plRepository.findOne(packId);
        if(p!=null){
            logger.debug(String.format("Deleting ProductLocation with id %s", packId));
            p.setUsed(false);
            plRepository.save(p);
        }
    }
    
    public void delTestProductLocation(Integer packId){
        ProductLocation p = plRepository.findOne(packId);
        if(p!=null){
            logger.debug(String.format("Deleting test ProductLocation with id %s", packId));
            plRepository.delete(packId);
        }
    }
    
}

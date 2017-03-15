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
    
}

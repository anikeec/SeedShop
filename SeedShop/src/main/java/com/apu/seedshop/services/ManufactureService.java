/*
 * 
 * 
 */
package com.apu.seedshop.services;
import com.apu.seedshop.jpa.Manufacture;
import com.apu.seedshop.repository.ManufactureRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ManufactureService {
    private static final Logger logger =  LoggerFactory.getLogger(ManufactureService.class);   

    @Autowired
    ManufactureRepository manufactureRepository;
  
    public List<Manufacture> getAllManufactures(){
        return  manufactureRepository.findAll();
    }

    public Manufacture getManufactureById(Integer manufactId) throws IllegalArgumentException {
        if(manufactId == null)    throw new IllegalArgumentException("manufactId = null");
        Manufacture m = manufactureRepository.findOne(manufactId);
        return m;
    }
    
    public Manufacture addManufacture(Manufacture m) throws IllegalArgumentException {  
        if(m == null)    throw new IllegalArgumentException("m = null");
        logger.debug(String.format("Adding manufacture %s, %s with id %s", 
                        m.getName(), m.getAddress(), m.getManufactId()));
        m = manufactureRepository.save(m);
        return m;
    }
    
    public void delManufacture(Integer manufactId) throws IllegalArgumentException {
        if(manufactId == null)    throw new IllegalArgumentException("manufactId = null");
        Manufacture m = manufactureRepository.findOne(manufactId);
        if(m!=null){
            logger.debug(String.format("Deleting manufacture with id %s", manufactId));
            //manufactureRepository.delete(manufactId);
            m.setUsed(false);
            manufactureRepository.save(m);
        }
    }
    
    public void delManufactureFull(Integer manufactId){
        Manufacture m = manufactureRepository.findOne(manufactId);
        if(m!=null){
            logger.debug(String.format("Deleting manufacture full with id %s", manufactId));
            manufactureRepository.delete(manufactId);
        }
    }
    
}

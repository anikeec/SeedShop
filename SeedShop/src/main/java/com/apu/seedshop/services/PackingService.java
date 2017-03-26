/*
 * 
 * 
 */
package com.apu.seedshop.services;
import com.apu.seedshop.jpa.Packing;
import com.apu.seedshop.repository.PackingRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PackingService {
    private static final Logger logger =  LoggerFactory.getLogger(PackingService.class);   

    @Autowired
    PackingRepository packingRepository;
  
    public List<Packing> getAllPackings(){
        return  packingRepository.findAll();
    }

    public Packing getPackingById(Integer packingId) throws IllegalArgumentException {
        if(packingId == null)    throw new IllegalArgumentException("packingId = null");
        Packing p = packingRepository.findOne(packingId);
        return p;
    }
    
    public Packing addPacking(Packing p) throws IllegalArgumentException { 
        if(p == null)    throw new IllegalArgumentException("p = null");
        logger.debug(String.format("Adding packing w=%s, a=%s, p=%s with id %s", 
               p.getWeight(), p.getAmount(), p.getPackId().getPackId(), p.getPackId()));
        p = packingRepository.save(p);
        return p;
    }
    
    public void delPacking(Integer packingId) throws IllegalArgumentException {
        if(packingId == null)    throw new IllegalArgumentException("packingId = null");
        Packing p = packingRepository.findOne(packingId);
        if(p!=null){
            logger.debug(String.format("Deleting packing with id %s", packingId));
            p.setUsed(false);
            packingRepository.save(p);
        }
    }
    
    public void delPackingFull(Integer packingId){
        Packing p = packingRepository.findOne(packingId);
        if(p!=null){
            logger.debug(String.format("Deleting packing full with id %s", packingId));
            packingRepository.delete(packingId);
        }
    }
    
}

/*
 * 
 * 
 */
package com.apu.seedshop.services;
import com.apu.seedshop.jpa.Pack;
import com.apu.seedshop.repository.PackRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PackService {
    private static final Logger logger =  LoggerFactory.getLogger(PackService.class);   

    @Autowired
    PackRepository packRepository;
  
    public List<Pack> getAllPacks(){
        return  packRepository.findAll();
    }

    public Pack getPackById(Integer packId) {
        Pack p = packRepository.findOne(packId);
        return p;
    }
    
    public Pack addPack(Pack p) {        
        logger.debug(String.format("Adding pack %s with id %s", 
                        p.getName(), p.getPackId()));
        p = packRepository.save(p);
        return p;
    }
    
    public void delPack(Integer packId){
        Pack p = packRepository.findOne(packId);
        if(p!=null){
            logger.debug(String.format("Deleting pack with id %s", packId));
            //packRepository.delete(packId);
            p.setUsed(false);
        }
    }
    
}

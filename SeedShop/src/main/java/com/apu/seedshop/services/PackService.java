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
private static final Logger logger =  LoggerFactory.getLogger(PackingService.class);   

@Autowired
PackRepository packRepository;

  
    public List<Pack> getAllPacks(){
        return  packRepository.findAll();
    }

    public Pack getPackById(Integer packId) {
        Pack p = packRepository.findByPackId(packId).get(0);
        return p;
    }
    
}

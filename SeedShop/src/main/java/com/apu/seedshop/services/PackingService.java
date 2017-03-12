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

    public Packing getPackingById(Integer packingId) {
        Packing p = packingRepository.findByPackingId(packingId).get(0);
        return p;
    }
    
}

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

    public Manufacture getManufactureById(Integer manufactId) {
        Manufacture m = manufactureRepository.findByManufactId(manufactId).get(0);
        return m;
    }
    
}

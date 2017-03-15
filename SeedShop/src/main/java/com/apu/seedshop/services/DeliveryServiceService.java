/*
 * 
 * 
 */
package com.apu.seedshop.services;
import com.apu.seedshop.jpa.DeliveryService;
import com.apu.seedshop.repository.DeliveryServiceRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DeliveryServiceService {
private static final Logger logger =  LoggerFactory.getLogger(DeliveryServiceService.class);   

@Autowired
DeliveryServiceRepository dservRepository;

  
    public List<DeliveryService> getAllDeliveryServices(){
        return  dservRepository.findAll();
    }

    public DeliveryService getDeliveryServiceById(Integer dservId) {
        DeliveryService p = dservRepository.findOne(dservId);
        return p;
    }
    
}

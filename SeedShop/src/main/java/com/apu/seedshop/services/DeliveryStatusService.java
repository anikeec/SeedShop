/*
 * 
 * 
 */
package com.apu.seedshop.services;
import com.apu.seedshop.jpa.DeliveryStatus;
import com.apu.seedshop.repository.DeliveryStatusRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DeliveryStatusService {
private static final Logger logger =  LoggerFactory.getLogger(DeliveryStatusService.class);   

@Autowired
DeliveryStatusRepository dsRepository;

  
    public List<DeliveryStatus> getAllDeliveryStatuses(){
        return  dsRepository.findAll();
    }

    public DeliveryStatus getDeliveryStatusById(Integer dsId) {
        DeliveryStatus p = dsRepository.findByStatusId(dsId).get(0);
        return p;
    }
    
}

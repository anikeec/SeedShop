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

    public DeliveryStatus getDeliveryStatusById(Integer dsId) throws IllegalArgumentException {
        if(dsId == null) throw new IllegalArgumentException("getDeliveryStatusById. dsId = null");
        DeliveryStatus p = dsRepository.findOne(dsId);
        return p;
    }
    
    public DeliveryStatus addDeliveryStatus(DeliveryStatus ds) throws IllegalArgumentException {      
        if(ds == null)    throw new IllegalArgumentException("p = null");
        logger.debug(String.format("Adding DeliveryStatus %s with id %s", 
                        ds.getStatus(), ds.getStatusId()));
        ds = dsRepository.save(ds);
        return ds;
    }
    
    public void delDeliveryStatus(Integer id) throws IllegalArgumentException {
        if(id == null) throw new IllegalArgumentException("delDeliveryStatus. id = null");
        DeliveryStatus temp = dsRepository.findOne(id);
        if(temp!=null){
            logger.debug(String.format("Deleting deliveryStatus with id %s",                    
                    temp.getStatusId()));
            temp.setUsed(false);
            dsRepository.save(temp);
        }
    }
    
    public void delDeliveryStatusFull(Integer id) throws IllegalArgumentException {
        if(id == null) throw new IllegalArgumentException("delDeliveryStatus. id = null");
        DeliveryStatus temp = dsRepository.findOne(id);
        if(temp!=null){
            logger.debug(String.format("Deleting test deliveryStatus with id %s",                    
                    temp.getStatusId()));
            dsRepository.delete(id);
        }
    }
    
}

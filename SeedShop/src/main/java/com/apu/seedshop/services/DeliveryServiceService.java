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

    public DeliveryService getDeliveryServiceById(Integer dservId) throws IllegalArgumentException {
        if(dservId == null)    throw new IllegalArgumentException("getDeliveryServiceById. dservId = null");
        DeliveryService p = dservRepository.findOne(dservId);
        return p;
    }
    
    public DeliveryService addDeliveryService(DeliveryService ds) throws IllegalArgumentException {      
        if(ds == null)    throw new IllegalArgumentException("p = null");
        logger.debug(String.format("Adding DeliveryService %s with id %s", 
                        ds.getName(), ds.getDeliveryId()));
        ds = dservRepository.save(ds);
        return ds;
    }
    
    public void delDeliveryService(Integer id) throws IllegalArgumentException {
        if(id == null) throw new IllegalArgumentException("delDeliveryService. id = null");
        DeliveryService temp = dservRepository.findOne(id);
        if(temp!=null){
            logger.debug(String.format("Deleting deliveryService with id %s",                    
                    temp.getDeliveryId()));
            temp.setUsed(false);
            dservRepository.save(temp);
        }
    }
    
    public void delTestDeliveryService(Integer id) throws IllegalArgumentException {
        if(id == null) throw new IllegalArgumentException("delDeliveryService. id = null");
        DeliveryService temp = dservRepository.findOne(id);
        if(temp!=null){
            logger.debug(String.format("Deleting test deliveryService with id %s",                    
                    temp.getDeliveryId()));
            dservRepository.delete(id);
        }
    }
    
}

/*
 * 
 * 
 */
package com.apu.seedshop.services;
import com.apu.seedshop.jpa.Availability;
import com.apu.seedshop.repository.AvailabilityRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AvailabilityService {
    private static final Logger logger =  LoggerFactory.getLogger(AvailabilityService.class);   

    @Autowired
    AvailabilityRepository availabilityRepository;
  
    public List<Availability> getAllAvailabilitys(){
        return  availabilityRepository.findAll();
    }

    public Availability getAvailabilityById(Integer id) throws IllegalArgumentException {
        if(id == null)    throw new IllegalArgumentException("manufactId = null");
        Availability av = availabilityRepository.findOne(id);
        return av;
    }
    
    public Availability addAvailability(Availability av) throws IllegalArgumentException {  
        if(av == null)    throw new IllegalArgumentException("av = null");
        logger.debug(String.format("Adding Availability %s, %s with id %s", 
                        av.getBarcode().getBarcode(), 
                        av.getLocationId().getLocationId(), 
                        av.getId()));
        av = availabilityRepository.save(av);
        return av;
    }
    
    public void delAvailability(Integer id) throws IllegalArgumentException {
        if(id == null)    throw new IllegalArgumentException("manufactId = null");
        Availability m = availabilityRepository.findOne(id);
        if(m!=null){
            logger.debug(String.format("Deleting Availability with id %s", id));
            availabilityRepository.delete(id);
            availabilityRepository.save(m);
        }
    }
    
}

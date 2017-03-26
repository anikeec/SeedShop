/*
 * 
 * 
 */
package com.apu.seedshop.rest;

import com.apu.seedshop.jpa.Availability;
import com.apu.seedshop.services.AvailabilityMapper;
import com.apu.seedshop.services.AvailabilityService;
import com.apu.seedshopapi.SeedGenericReply;
import com.apu.seedshopapi.SeedAvailabilityReply;
import com.apu.seedshopapi.SeedAvailabilityListReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController; 


@RestController
public class AvailabilityController {
    private static final Logger logger =  LoggerFactory.getLogger(AvailabilityController.class);
    @Autowired         
    AvailabilityService   avService;
    @Autowired         
    AvailabilityMapper    avMapper;
   
    
    @RequestMapping(path="/avail/all",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedAvailabilityListReply getAllAvailabilitys(){
        SeedAvailabilityListReply reply = new SeedAvailabilityListReply();
        for(Availability temp: avService.getAllAvailabilities()){
            reply.availabilities.add(avMapper.fromInternal(temp));    
        }
        return reply;
    }
    
    @RequestMapping(path="/avail/byid/{id}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedAvailabilityReply getAvailabilityById(@PathVariable Integer id ){
        SeedAvailabilityReply rep = new SeedAvailabilityReply();
        try {            
            Availability temp = avService.getAvailabilityById(id);
            rep.availability = avMapper.fromInternal(temp);
        } catch(IllegalArgumentException e) {
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error find Availability. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
    @RequestMapping(path="/avail/del/{id}", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedGenericReply delAvailability(@PathVariable Integer id ) {
        SeedGenericReply rep = new SeedGenericReply();
        try{
           avService.delAvailability(id);
        }catch(IllegalArgumentException e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error delete Availability. Expetion: " + e.getMessage(),e);
        }
        return rep;       
    }
    
}

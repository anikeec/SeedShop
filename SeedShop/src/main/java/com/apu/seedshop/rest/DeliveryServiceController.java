/*
 * 
 * 
 */
package com.apu.seedshop.rest;

import com.apu.seedshop.jpa.DeliveryService;
import com.apu.seedshop.services.DeliveryServiceMapper;
import com.apu.seedshop.services.DeliveryServiceService;
import com.apu.seedshopapi.SeedGenericReply;
import com.apu.seedshopapi.SeedDeliveryServiceListReply;
import com.apu.seedshopapi.SeedDeliveryServiceReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController; 


@RestController
public class DeliveryServiceController {
    private static final Logger logger =  LoggerFactory.getLogger(DeliveryServiceController.class);
    @Autowired         
    DeliveryServiceService   dsService;
    @Autowired         
    DeliveryServiceMapper    dsMapper;
   
    
    @RequestMapping(path="/dservice/all",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedDeliveryServiceListReply getAllDeliveryServices(){
        SeedDeliveryServiceListReply reply = new SeedDeliveryServiceListReply();
        for(DeliveryService temp: dsService.getAllDeliveryServices()){
            reply.deliveryServices.add(dsMapper.fromInternal(temp));    
        }
        return reply;
    }
    
    @RequestMapping(path="/dservice/byid/{id}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedDeliveryServiceReply getDeliveryServiceById(@PathVariable Integer id ){
        SeedDeliveryServiceReply rep = new SeedDeliveryServiceReply();
        try {            
            DeliveryService temp = dsService.getDeliveryServiceById(id);
            rep.deliveryService = dsMapper.fromInternal(temp);
        } catch(IllegalArgumentException e) {
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error find DeliveryService. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
    @RequestMapping(path="/dservice/del/{id}", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedGenericReply delDeliveryService(@PathVariable Integer id ) {
        SeedGenericReply rep = new SeedGenericReply();
        try{
           dsService.delDeliveryService(id);
        }catch(IllegalArgumentException e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error delete deliveryService. Expetion: " + e.getMessage(),e);
        }
        return rep;       
    }
    
}

/*
 * 
 * 
 */
package com.apu.seedshop.rest;

import com.apu.seedshop.jpa.DeliveryStatus;
import com.apu.seedshop.services.DeliveryStatusMapper;
import com.apu.seedshop.services.DeliveryStatusService;
import com.apu.seedshopapi.SeedGenericReply;
import com.apu.seedshopapi.SeedDeliveryStatusListReply;
import com.apu.seedshopapi.SeedDeliveryStatusReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController; 


@RestController
public class DeliveryStatusController {
    private static final Logger logger =  LoggerFactory.getLogger(DeliveryStatusController.class);
    @Autowired         
    DeliveryStatusService   dsService;
    @Autowired         
    DeliveryStatusMapper    dsMapper;
   
    
    @RequestMapping(path="/dstatus/all",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedDeliveryStatusListReply getAllDeliveryStatuses(){
        SeedDeliveryStatusListReply reply = new SeedDeliveryStatusListReply();
        for(DeliveryStatus temp: dsService.getAllDeliveryStatuses()){
            reply.deliveryStatuses.add(dsMapper.fromInternal(temp));    
        }
        return reply;
    }
    
    @RequestMapping(path="/dstatus/byid/{id}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedDeliveryStatusReply getDeliveryStatusById(@PathVariable Integer id){
        SeedDeliveryStatusReply rep = new SeedDeliveryStatusReply();
        try {            
            DeliveryStatus temp = dsService.getDeliveryStatusById(id);
            rep.deliveryStatus = dsMapper.fromInternal(temp);
        } catch(IllegalArgumentException e) {
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error find DeliveryStatus. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
    @RequestMapping(path="/dstatus/del/{id}", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedGenericReply delDeliveryStatus(@PathVariable Integer id ) {
        SeedGenericReply rep = new SeedGenericReply();
        try{
           dsService.delDeliveryStatus(id);
        }catch(IllegalArgumentException e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error delete deliveryStatus. Expetion: " + e.getMessage(),e);
        }
        return rep;       
    }
    
}

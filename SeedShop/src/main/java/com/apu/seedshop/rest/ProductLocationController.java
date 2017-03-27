/*
 * 
 * 
 */
package com.apu.seedshop.rest;

import com.apu.seedshop.jpa.ProductLocation;
import com.apu.seedshop.services.ProductLocationMapper;
import com.apu.seedshop.services.ProductLocationService;
import com.apu.seedshopapi.SeedGenericReply;
import com.apu.seedshopapi.SeedProductLocation;
import com.apu.seedshopapi.SeedProductLocationReply;
import com.apu.seedshopapi.SeedProductLocationListReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController; 


@RestController
public class ProductLocationController {
    private static final Logger logger =  LoggerFactory.getLogger(ProductLocationController.class);
    @Autowired         
    ProductLocationService   plService;
    @Autowired         
    ProductLocationMapper    plMapper;
   
    
    @RequestMapping(path="/plocation/all",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedProductLocationListReply getAllProductLocations(){
        SeedProductLocationListReply reply = new SeedProductLocationListReply();
        for(ProductLocation temp: plService.getAllProductLocations()){
            reply.pLocations.add(plMapper.fromInternal(temp));    
        }
        return reply;
    }
    
    @RequestMapping(path="/plocation/byid/{id}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedProductLocationReply getProductLocationById(@PathVariable Integer id ){
        SeedProductLocationReply rep = new SeedProductLocationReply();
        try {            
            ProductLocation temp = plService.getProductLocationById(id);
            rep.pLocation = plMapper.fromInternal(temp);
        } catch(IllegalArgumentException e) {
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error find ProductLocation. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
    @RequestMapping(path="/plocation/del/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedGenericReply delProductLocation(@PathVariable("id") Integer id ) {
        SeedGenericReply rep = new SeedGenericReply();
        try{
           plService.delProductLocation(id);
        }catch(IllegalArgumentException e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error delete ProductLocation. Expetion: " + e.getMessage(),e);
        }
        return rep;       
    }
    
    @RequestMapping(path="/plocation/add",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedGenericReply addInvoice(@RequestBody SeedProductLocation req){
        SeedGenericReply rep = new SeedGenericReply();
        try{
           ProductLocation p;
           p = plService.addProductLocation(plMapper.toInternal(req));
           //rep = plMapper.fromInternal(p);
        }catch(IllegalArgumentException e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding ProductLocation. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
}

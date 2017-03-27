/*
 * 
 * 
 */
package com.apu.seedshop.rest;

import com.apu.seedshop.jpa.Packing;
import com.apu.seedshop.services.PackingMapper;
import com.apu.seedshop.services.PackingService;
import com.apu.seedshopapi.SeedGenericReply;
import com.apu.seedshopapi.SeedPacking;
import com.apu.seedshopapi.SeedPackingReply;
import com.apu.seedshopapi.SeedPackingListReply;
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
public class PackingController {
    private static final Logger logger =  LoggerFactory.getLogger(PackingController.class);
    @Autowired         
    PackingService   pService;
    @Autowired         
    PackingMapper    pMapper;
   
    
    @RequestMapping(path="/packing/all",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedPackingListReply getAllPackings(){
        SeedPackingListReply reply = new SeedPackingListReply();
        for(Packing temp: pService.getAllPackings()){
            reply.packings.add(pMapper.fromInternal(temp));    
        }
        return reply;
    }
    
    @RequestMapping(path="/packing/byid/{id}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedPackingReply getPackingById(@PathVariable Integer id ){
        SeedPackingReply rep = new SeedPackingReply();
        try {            
            Packing temp = pService.getPackingById(id);
            rep.packing = pMapper.fromInternal(temp);
        } catch(IllegalArgumentException e) {
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error find Packing. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
    @RequestMapping(path="/packing/del/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedGenericReply delPacking(@PathVariable Integer id ) {
        SeedGenericReply rep = new SeedGenericReply();
        try{
           pService.delPacking(id);
        }catch(IllegalArgumentException e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error delete Packing. Expetion: " + e.getMessage(),e);
        }
        return rep;       
    }
    
    @RequestMapping(path="/packing/add", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedGenericReply addInvoice( @RequestBody SeedPacking req){
        SeedGenericReply rep = new SeedPackingReply();
        try{
           Packing p;
           p = pService.addPacking(pMapper.toInternal(req));           
        }catch(IllegalArgumentException e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding packing. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
}

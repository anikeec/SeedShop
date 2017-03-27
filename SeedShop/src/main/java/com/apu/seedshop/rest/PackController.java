/*
 * 
 * 
 */
package com.apu.seedshop.rest;

import com.apu.seedshop.jpa.Pack;
import com.apu.seedshop.services.PackMapper;
import com.apu.seedshop.services.PackService;
import com.apu.seedshopapi.SeedGenericReply;
import com.apu.seedshopapi.SeedPack;
import com.apu.seedshopapi.SeedPackReply;
import com.apu.seedshopapi.SeedPackListReply;
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
public class PackController {
    private static final Logger logger =  LoggerFactory.getLogger(PackController.class);
    @Autowired         
    PackService   pService;
    @Autowired         
    PackMapper    pMapper;
   
    
    @RequestMapping(path="/pack/all",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedPackListReply getAllPacks(){
        SeedPackListReply reply = new SeedPackListReply();
        for(Pack temp: pService.getAllPacks()){
            reply.packs.add(pMapper.fromInternal(temp));    
        }
        return reply;
    }
    
    @RequestMapping(path="/pack/byid/{id}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedPackReply getPackById(@PathVariable Integer id ){
        SeedPackReply rep = new SeedPackReply();
        try {            
            Pack temp = pService.getPackById(id);
            rep.pack = pMapper.fromInternal(temp);
        } catch(IllegalArgumentException e) {
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error find Pack. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
    @RequestMapping(path="/pack/del/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedGenericReply delPack(@PathVariable Integer id ) {
        SeedGenericReply rep = new SeedGenericReply();
        try{
           pService.delPack(id);
        }catch(IllegalArgumentException e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error delete Pack. Expetion: " + e.getMessage(),e);
        }
        return rep;       
    }
    
    @RequestMapping(path="/pack/add",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedGenericReply addInvoice( @RequestBody SeedPack req){
        SeedGenericReply rep = new SeedGenericReply();
        try{
           Pack p;
           p = pService.addPack(pMapper.toInternal(req));
        }catch(IllegalArgumentException e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding pack. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
}

/*
 * 
 * 
 */
package com.apu.seedshop.rest;

import com.apu.seedshop.jpa.Manufacture;
import com.apu.seedshop.services.ManufactureMapper;
import com.apu.seedshop.services.ManufactureService;
import com.apu.seedshopapi.SeedGenericReply;
import com.apu.seedshopapi.SeedManufactureReply;
import com.apu.seedshopapi.SeedManufactureListReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController; 


@RestController
public class ManufactureController {
    private static final Logger logger =  LoggerFactory.getLogger(ManufactureController.class);
    @Autowired         
    ManufactureService   mService;
    @Autowired         
    ManufactureMapper    mMapper;
   
    
    @RequestMapping(path="/manufacture/all",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedManufactureListReply getAllManufactures(){
        SeedManufactureListReply reply = new SeedManufactureListReply();
        for(Manufacture temp: mService.getAllManufactures()){
            reply.manufactures.add(mMapper.fromInternal(temp));    
        }
        return reply;
    }
    
    @RequestMapping(path="/manufacture/byid/{id}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedManufactureReply getManufactureById(@PathVariable Integer id ){
        SeedManufactureReply rep = new SeedManufactureReply();
        try {            
            Manufacture temp = mService.getManufactureById(id);
            rep.manufacture = mMapper.fromInternal(temp);
        } catch(IllegalArgumentException e) {
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error find Manufacture. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
    @RequestMapping(path="/manufacture/del/{id}", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedGenericReply delManufacture(@PathVariable Integer id ) {
        SeedGenericReply rep = new SeedGenericReply();
        try{
           mService.delManufacture(id);
        }catch(IllegalArgumentException e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error delete Manufacture. Expetion: " + e.getMessage(),e);
        }
        return rep;       
    }
    
}

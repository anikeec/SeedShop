/*
 * 
 * 
 */
package com.apu.seedshop.rest;

import com.apu.seedshop.jpa.UserGender;
import com.apu.seedshop.services.UserGenderMapper;
import com.apu.seedshop.services.UserGenderService;
import com.apu.seedshopapi.SeedGenericReply;
import com.apu.seedshopapi.SeedUserGender;
import com.apu.seedshopapi.SeedUserGenderReply;
import com.apu.seedshopapi.SeedUserGenderListReply;
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
public class UserGenderController {
    private static final Logger logger =  LoggerFactory.getLogger(UserGenderController.class);
    @Autowired         
    UserGenderService   ugService;
    @Autowired         
    UserGenderMapper    ugMapper;
   
    
    @RequestMapping(path="/ugender/all",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedUserGenderListReply getAllUserGenders(){
        SeedUserGenderListReply reply = new SeedUserGenderListReply();
        for(UserGender temp: ugService.getAll()){
            reply.userGenders.add(ugMapper.fromInternal(temp));    
        }
        return reply;
    }
    
    @RequestMapping(path="/ugender/byid/{id}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedUserGenderReply getUserGenderById(@PathVariable Integer id ){
        SeedUserGenderReply rep = new SeedUserGenderReply();
        try {            
            UserGender temp = ugService.getUserGenderById(id);
            rep.userGender = ugMapper.fromInternal(temp);
        } catch(IllegalArgumentException e) {
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error find UserGender. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
    @RequestMapping(path="/ugender/del/{id}", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedGenericReply delUserGender(@PathVariable Integer id ) {
        SeedGenericReply rep = new SeedGenericReply();
        try{
           ugService.delUserGender(id);
        }catch(IllegalArgumentException e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error delete UserGender. Expetion: " + e.getMessage(),e);
        }
        return rep;       
    }
    
    @RequestMapping(path="/ugender/add",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SeedGenericReply addUserGender(@RequestBody SeedUserGender req){
        SeedGenericReply rep = new SeedGenericReply();
        try{
           UserGender p;
           p = ugService.addUserGender(ugMapper.toInternal(req));
        }catch(IllegalArgumentException e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding UserGender. Expetion: " + e.getMessage(),e);
        }
        return rep;
    }
    
}

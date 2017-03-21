/*
 * 
 * 
 */
package com.apu.seedshop.services;

import com.apu.seedshop.jpa.UserGender;
import com.apu.seedshop.repository.UserGenderRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserGenderService {
    private static final Logger logger =  LoggerFactory.getLogger(UserGenderService.class);   

    @Autowired
    UserGenderRepository ugRepository;
  
    public List<UserGender> getAll(){
        return  ugRepository.findAll();
    }

    public UserGender getUserGenderById(Integer id) throws IllegalArgumentException {
        if(id == null)    throw new IllegalArgumentException("id = null");
        UserGender ug = ugRepository.findOne(id);
        return ug;
    }
    
    public List<UserGender> findUserGenderByName(String name) throws IllegalArgumentException {
        if(name == null)    throw new IllegalArgumentException("name = null");
        List<UserGender> udl = ugRepository.findByName(name);
        List<UserGender> res = new ArrayList<>();
        for(UserGender ug:udl){
            logger.debug(ug.getGenderId() + ", " + ug.getName());
            res.add(ug);
        }        
        return res;
    }

    public UserGender addUserGender(UserGender ug) throws IllegalArgumentException {
        if(ug == null)    throw new IllegalArgumentException("ug = null");
        logger.debug(String.format("Adding user gender %s with id %s", ug.getName(), ug.getGenderId()));
        ug = ugRepository.save(ug);
        return ug;
    }

    public void delUserGender(Integer id) throws IllegalArgumentException {
        if(id == null)    throw new IllegalArgumentException("id = null");
        UserGender ug = ugRepository.findOne(id);
        if(ug!=null){
            logger.debug(String.format("Deleting user gender %s with id %s", ug.getName(), ug.getGenderId()));
            //ugRepository.delete(id);
            ug.setUsed(false);
            ugRepository.save(ug);
        }
    }
    
    public void delTestUserGender(Integer id){
        if(id == null)    throw new IllegalArgumentException("id = null");
        UserGender ug = ugRepository.findOne(id);
        if(ug!=null){
            logger.debug(String.format("Deleting test user gender %s with id %s", ug.getName(), ug.getGenderId()));
            ugRepository.delete(id);
        }
    }
}

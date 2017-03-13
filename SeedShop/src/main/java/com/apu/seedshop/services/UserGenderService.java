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
    private static final Logger logger =  LoggerFactory.getLogger(UserService.class);   

    @Autowired
    UserGenderRepository ugRepository;
  
    public List<UserGender> getAll(){
        return  ugRepository.findAll();
    }

    public UserGender getUserGenderById(Integer id) {
        UserGender ug = ugRepository.findOne(id);
        return ug;
    }
    
    public List<UserGender> findUserGenderByName(String name){
        List<UserGender> udl = ugRepository.findByName(name);
        List<UserGender> res = new ArrayList<>();
        for(UserGender ug:udl){
            logger.debug(ug.getGenderId() + ", " + ug.getName());
            res.add(ug);
        }        
        return res;
    }

    public UserGender addUserGender(UserGender ug) {
        logger.debug(String.format("Adding user gender %s with id %s", ug.getName(), ug.getGenderId()));
        ug = ugRepository.save(ug);
        return ug;
    }

    public void delUserGender(Integer id){
        UserGender ug = ugRepository.findOne(id);
        if(ug!=null){
            logger.debug(String.format("Deleting user gender %s with id %s", ug.getName(), ug.getGenderId()));
            ugRepository.delete(id);
        }
    }
}

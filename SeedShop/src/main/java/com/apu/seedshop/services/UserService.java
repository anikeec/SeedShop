/*
 * 
 * 
 */
package com.apu.seedshop.services;

import com.apu.seedshop.jpa.Appuser;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apu.seedshop.repository.UserRepository;


@Service
public class UserService {
private static final Logger logger =  LoggerFactory.getLogger(UserService.class);   

@Autowired
UserRepository userRepository;

  
    public List<Appuser> getAllUsers(){
        return  userRepository.findAll();
    }

    public Appuser getUserById(Long id) {
        Appuser u = userRepository.findOne(id);
        return u;
    }
    
    public List<Appuser> findUserByName(String name){
        List<Appuser> udl = userRepository.findByFirstName(name);
        List<Appuser> res = new ArrayList<>();
        for(Appuser u:udl){
            System.out.println(u.getUserId() + ", " +
                                u.getSecName()+ ", " + 
                                u.getFirstName());
            res.add(u);
        }        
        return res;
    }
    
    public List<Appuser> findBySecNameAndFirstName(String secName, String firstName){
        List<Appuser> udl = userRepository.findBySecNameAndFirstName(secName, firstName);
        List<Appuser> res = new ArrayList<>();
        for(Appuser u:udl){
            System.out.println(u.getUserId() + ", " +
                                u.getSecName()+ ", " + 
                                u.getFirstName());
            res.add(u);
        }        
        return res;
    }

    public Appuser addUser(Appuser u) {
        logger.debug(String.format("Adding users %s %s %s with id %s", u.getFirstName(), u.getSecName(), u.getThirdName(), u.getUserId()));
        u = userRepository.save(u);
        return u;
    }

    public void delUser(Long id){
        Appuser u = userRepository.findOne(id);
        if(u!=null){
            logger.debug(String.format("Deleting users %s %s %s with id %s", u.getFirstName(), u.getSecName(), u.getThirdName(), u.getUserId()));
            //List<Ugroup> gl = u.getUgroupList();
            //detailsRepository.delete(id);
            userRepository.delete(id);
        }
    }
}

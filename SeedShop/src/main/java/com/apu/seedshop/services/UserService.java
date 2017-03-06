/*
 * 
 * 
 */
package com.apu.seedshop.services;

import com.apu.seedshop.jpa.User;
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

  
    public List<User> getAllUsers(){
        return  userRepository.findAll();
    }

    public User getUserById(Integer id) {
        User u = userRepository.findOne(id);
        return u;
    }
    
    public List<User> findUserByName(String name){
        List<User> udl = userRepository.findByFirstName(name);
        List<User> res = new ArrayList<>();
        for(User u:udl){
            System.out.println(u.getUserId() + ", " +
                                u.getSecName()+ ", " + 
                                u.getFirstName());
            res.add(u);
        }        
        return res;
    }
    
    public List<User> findBySecNameAndFirstName(String secName, String firstName){
        List<User> udl = userRepository.findBySecNameAndFirstName(secName, firstName);
        List<User> res = new ArrayList<>();
        for(User u:udl){
            System.out.println(u.getUserId() + ", " +
                                u.getSecName()+ ", " + 
                                u.getFirstName());
            res.add(u);
        }        
        return res;
    }

    public User addUser(User u) {
        logger.debug("Adding users %s with id %s", u.getFirstName(), u.getSecName(), u.getThirdName(), u.getUserId());
        u = userRepository.save(u);
        return u;
    }

    public void delUser(Integer id){
        User u = userRepository.findOne(id);
        if(u!=null){
            logger.debug("Deleting users %s with id %s", u.getFirstName(), u.getSecName(), u.getThirdName(), u.getUserId());
            //List<Ugroup> gl = u.getUgroupList();
            //detailsRepository.delete(id);
            userRepository.delete(id);
        }
    }
}

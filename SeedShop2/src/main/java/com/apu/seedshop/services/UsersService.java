/*
 * 
 * 
 */
package com.apu.seedshop.services;

import com.apu.seedshop.jpa.Users;
import com.apu.seedshop.repository.UsersRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsersService {
private static final Logger logger =  LoggerFactory.getLogger(UsersService.class);   

@Autowired
UsersRepository usersRepository;

  
    public List<Users> getAllUsers(){
        return  usersRepository.findAll();
    }

    public Users getUserById(Integer id) {
        Users u = usersRepository.findOne(id);
        return u;
    }
    
    public List<Users> findUserByName(String name){
        List<Users> udl = usersRepository.findByFirstName(name);
        List<Users> res = new ArrayList<>();
        for(Users u:udl){
            System.out.println(u.getUserId() + ", " +
                                u.getSecName()+ ", " + 
                                u.getFirstName());
            res.add(u);
        }        
        return res;
    }
    
    public List<Users> findBySecNameAndFirstName(String secName, String firstName){
        List<Users> udl = usersRepository.findBySecNameAndFirstName(secName, firstName);
        List<Users> res = new ArrayList<>();
        for(Users u:udl){
            System.out.println(u.getUserId() + ", " +
                                u.getSecName()+ ", " + 
                                u.getFirstName());
            res.add(u);
        }        
        return res;
    }

    public Users addUser(Users u) {
        logger.debug("Adding users %s with id %s", u.getFirstName(), u.getSecName(), u.getThirdName(), u.getUserId());
        u = usersRepository.save(u);
        return u;
    }

    public void delUser(Integer id){
        Users u = usersRepository.findOne(id);
        if(u!=null){
            logger.debug("Deleting users %s with id %s", u.getFirstName(), u.getSecName(), u.getThirdName(), u.getUserId());
            //List<Ugroup> gl = u.getUgroupList();
            //detailsRepository.delete(id);
            usersRepository.delete(id);
        }
    }
}

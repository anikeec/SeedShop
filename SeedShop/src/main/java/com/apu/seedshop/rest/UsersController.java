/*
 * 
 * 
 */
package com.apu.seedshop.rest;

import com.apu.seedshop.jpa.Users;
import com.apu.seedshop.services.UsersMapper;
import com.apu.seedshop.services.UsersService;
import com.apu.seedshopapi.UserListReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UsersController {
    private static final Logger logger =  LoggerFactory.getLogger(UsersController.class);
    @Autowired         
    UsersService usersService;
    @Autowired
    UsersMapper usersMapper;    
    
    @RequestMapping(path="/users/all",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply getAllUsers(){
        UserListReply reply = new UserListReply();
        for(Users u: usersService.getAllUsers()){
           reply.users.add(usersMapper.fromInternal(u));    
        }
        return reply;
    }
    
    @RequestMapping(path="/users/byid/{userid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply getUserById(@PathVariable Integer userid ){
        UserListReply reply = new UserListReply();
        reply.users.add(usersMapper.fromInternal(usersService.getUserById(userid)));
        return reply;
    }
    
}

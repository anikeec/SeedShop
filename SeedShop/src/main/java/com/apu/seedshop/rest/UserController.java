/*
 * 
 * 
 */
package com.apu.seedshop.rest;

import com.apu.seedshop.jpa.Appuser;
import com.apu.seedshop.services.UserMapper;
import com.apu.seedshop.services.UserService;
import com.apu.seedshopapi.AddUserRequest;
import com.apu.seedshopapi.GenericReply;
import com.apu.seedshopapi.UserListReply;
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
public class UserController {
    private static final Logger logger =  LoggerFactory.getLogger(UserController.class);
    @Autowired         
    UserService userService;
    @Autowired
    UserMapper userMapper;    
    
    @RequestMapping(path="/users/all",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply getAllUsers(){
        UserListReply reply = new UserListReply();
        for(Appuser u: userService.getAllUsers()){
           reply.users.add(userMapper.fromInternal(u));    
        }
        return reply;
    }
    
    @RequestMapping(path="/users/byid/{userid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply getUserById(@PathVariable Integer userid ){
        UserListReply reply = new UserListReply();
        reply.users.add(userMapper.fromInternal(userService.getUserById(userid)));        
        return reply;
    }
    
    @RequestMapping(path="/users/add",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply addUser( @RequestBody AddUserRequest req){
        UserListReply rep = new UserListReply();
        try{
           Appuser au;
           au = userService.addUser(userMapper.toInternal(req.user));
           rep.users.add(userMapper.fromInternal(au));
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding user. Expetion: "+e.getMessage(),e);
        }
        return rep;
    } 
    
    @RequestMapping(path="/users/del/{userid}",  method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delUser(@PathVariable Integer userid ){
            GenericReply rep = new GenericReply();
        try{
            userService.delUser(userid);
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding user. Expetion: "+e.getMessage(),e);
        }
        return rep;       
    }
    
}

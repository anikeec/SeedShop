/*
 * 
 * 
 */
package com.apu.seedshop.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.apu.seedshopapi.LoginReply;
import com.apu.seedshopapi.LoginRequest;
import com.apu.seedshop.auth.AuthUser;
import com.apu.seedshop.auth.TokenProvider;
import com.apu.seedshop.jpa.Appuser;
import com.apu.seedshop.services.AppuserMapper;
import com.apu.seedshop.services.AppuserService;

/**
 *
 * @author al
 */
@RestController
public class AuthController {
private static final Logger logger =  LoggerFactory.getLogger(AuthController.class);
    @Autowired         
    AppuserService userService;
    @Autowired
    AppuserMapper userMapper;    
    @Autowired
    TokenProvider tokenProvider;

    @RequestMapping(path="/auth",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LoginReply authUser( @RequestBody LoginRequest req){
        LoginReply rep = new LoginReply();
           Appuser au;
           au = userService.authUser(req.login,req.password);
           if(au!=null){
              String token = tokenProvider.newToken();
              tokenProvider.put(token, new AuthUser(au));
              rep.user = userMapper.fromInternal(au);
              rep.token = token;
           }else{
              logger.error("Error loggin in user. User: "+req.login);
           }
        return rep;
    }

}

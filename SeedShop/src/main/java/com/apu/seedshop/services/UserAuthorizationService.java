/*
 * 
 * 
 */
package com.apu.seedshop.services;
import com.apu.seedshop.jpa.UserAuthorization;
import com.apu.seedshop.repository.UserAuthorizationRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserAuthorizationService {
    private static final Logger logger =  LoggerFactory.getLogger(UserAuthorizationService.class);   

    @Autowired
    UserAuthorizationRepository uaRepository;
  
    public List<UserAuthorization> getAllUserAuthorization(){
        return  uaRepository.findAll();
    }

    public UserAuthorization getUserAuthorizationById(Long userId) {
        UserAuthorization ua = uaRepository.findOne(userId);
        return ua;
    }
    
    public UserAuthorization addUserAuthorization(UserAuthorization ua) {        
        logger.debug(String.format("Adding userAuthorization %s, %s with id %s", 
                        ua.getLogin(), ua.getPasswdHash(), ua.getUserId()));
        ua = uaRepository.save(ua);
        return ua;
    }
    
    public void delUserAuthorization(Long userId){
        UserAuthorization ua = uaRepository.findOne(userId);
        if(ua!=null){
            logger.debug(String.format("Deleting userAuthorization with id %s", userId));
            ua.setUsed(false);
            uaRepository.save(ua);
        }
    }
    
    public void delTestUserAuthorization(Long userId){
        UserAuthorization ua = uaRepository.findOne(userId);
        if(ua!=null){
            logger.debug(String.format("Deleting test userAuthorization with id %s", userId));
            uaRepository.delete(userId);
        }
    }
    
}

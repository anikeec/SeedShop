/*
 * 
 * 
 */
package com.apu.seedshop.auth;

import org.springframework.security.core.GrantedAuthority;
import com.apu.seedshop.jpa.Appuser;


/**
 *
 * @author al
 */
public class UserAuthority implements GrantedAuthority{
    private String autority; 
    
    public UserAuthority(AuthorityName n) {
        autority = n.name();
    }
      
    @Override
    public String getAuthority() {
       return autority;
    }
    
}

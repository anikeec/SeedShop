/*
 * 
 * 
 */
package com.apu.seedshop.auth;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.apu.seedshop.jpa.Appuser;

/**
 *
 * @author al
 */
public class AuthUser implements UserDetails{
    private final Appuser user;
    Collection<UserAuthority> authorities = new ArrayList<>();
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    public Appuser getAppUser(){
        return user;
    }
    
    public AuthUser(Appuser user){
        this.user = user;
        authorities.add(new UserAuthority(AuthorityName.ROLE_USER));
        if(user.getRole().equals("ROLE_ADMIN")){
            authorities.add(new UserAuthority(AuthorityName.ROLE_ADMIN));
        }
        if(user.getRole().equals("ROLE_MANAGER") ){
            authorities.add(new UserAuthority(AuthorityName.ROLE_MANAGER));
        }
    }
    
    @Override
    public String getPassword() {
        return user.getPasswdHash();
    }

    @Override
    public String getUsername() {
        return user.getFirstName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
       return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}

package com.example.tacos.domain.reactive.mongo;

import java.util.Arrays;
import java.util.Collection;

import com.example.tacos.domain.UserInfo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@Document(collection = "users")
public class User implements UserDetails {

    @Id
    private Long id;

    private final String username;
    private final String password;
    
    private final UserInfo userInfo;

    public String getFullname()
    {
        return userInfo.getFullname();
    }

    public String getPhoneNumber()
    {
        return userInfo.getFullname();
    }

    public String getStreet()
    {
        return userInfo.getStreet();
    }

    public String getCity()
    {
        return userInfo.getCity();
    }

    public String getState()
    {
        return userInfo.getState();
    }

    public String getZip()
    {
        return userInfo.getZip();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
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
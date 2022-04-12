package com.example.tacos.domain;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final String username;
    private final String password;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "fullname", column = @Column(name = "fullname"))
    })
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

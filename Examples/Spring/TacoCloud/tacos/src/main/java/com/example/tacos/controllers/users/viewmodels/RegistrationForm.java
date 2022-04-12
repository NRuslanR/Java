package com.example.tacos.controllers.users.viewmodels;

import com.example.tacos.domain.User;
import com.example.tacos.domain.UserInfo;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class RegistrationForm {
    
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder)
    {
        return 
            new User(
                username,
                passwordEncoder.encode(password),
                new UserInfo(fullname, phone, street, city, state, zip)
            );
    }
}

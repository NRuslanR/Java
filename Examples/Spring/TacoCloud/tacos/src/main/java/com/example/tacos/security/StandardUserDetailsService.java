package com.example.tacos.security;

import com.example.tacos.data.jpa.UserRepository;
import com.example.tacos.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StandardUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public StandardUserDetailsService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        User user = userRepository.findByUsername(username);

        if (user != null)
            return user;

        throw new UsernameNotFoundException(String.format("User with name %s not found", username));
    }
    
}

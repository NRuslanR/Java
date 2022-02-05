package com.example.tacos.controllers.users;

import com.example.tacos.controllers.users.viewmodels.RegistrationForm;
import com.example.tacos.data.jpa.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/register")
public class UserRegistrationController {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRegistrationController(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder
    )
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registrationForm()
    {
        return "register";
    }

    @PostMapping
    public String processRegistration(RegistrationForm registrationForm)
    {
        userRepository.save(registrationForm.toUser(passwordEncoder));
        
        return "redirect:/login";
    }
}

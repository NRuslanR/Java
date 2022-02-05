package com.example.tacos.data.jpa;

import com.example.tacos.domain.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    
}

package com.example.tacos.data.jpa;

import com.example.tacos.domain.Taco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TacoRepository extends JpaRepository<Taco, Long> {
    
}

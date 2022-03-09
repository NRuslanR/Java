package com.example.tacos.data.jpa;

import com.example.tacos.domain.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
    
}

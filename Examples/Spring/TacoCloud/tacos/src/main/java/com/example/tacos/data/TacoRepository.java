package com.example.tacos.data;

import com.example.tacos.domain.Taco;

public interface TacoRepository {

    Taco save(Taco taco);
    
}

package org.example.spring_aop.domain;

import java.util.stream.IntStream;

public class Reducer {
    
    public Reducer()
    {

    }
    
    public int sum(int... items)
    {
        return IntStream.of(items).sum();
    }
}

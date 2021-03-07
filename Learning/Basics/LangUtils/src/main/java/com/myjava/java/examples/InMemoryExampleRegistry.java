/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.java.examples;

import java.util.*;
/**
 *
 * @author ruslan
 */
public class InMemoryExampleRegistry implements ExampleRegistry {

    private Map<String, Class<?>> exampleMap;
    
    public InMemoryExampleRegistry()
    {
       exampleMap = new HashMap<>();
    }
    
    @Override
    public <T> void register(String exampleName, Class<? extends T> exampleType) {
        
        exampleMap.putIfAbsent(exampleName, exampleType);
    }

    @Override
    public Class<?> get(String exampleName) {
        
        return exampleMap.get(exampleName);
    }
    
}

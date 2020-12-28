/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.java.examples;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myjava.java.ProgramExample;
import com.myjava.java.hibernate.examples.SimpleCRUDHibernateExample;
import org.reflections.*;
import java.util.*;
import java.util.stream.Collectors;

class ExampleConfig
{
    public String id;
    public String type;
}

class ExamplesConfig
{
    public ExampleConfig[] examples;
}

public abstract class AbstractExampleRegistryFactory implements ExampleRegistryFactory {

    @Override
    public ExampleRegistry createExampleRegistry() throws Exception {
        
        ExampleRegistry exampleRegistry = createExampleRegistryInstance();
        
        fillExampleRegistry(exampleRegistry);
        
        return exampleRegistry;
    }
    
    protected abstract ExampleRegistry createExampleRegistryInstance();

    private void fillExampleRegistry(ExampleRegistry exampleRegistry) throws Exception {
     
        Reflections reflections = new Reflections("com.myjava.java");

        reflections.getSubTypesOf(ProgramExample.class)
            .stream()
                .filter(
                    et -> !java.lang.reflect.Modifier.isAbstract(
                        et.getModifiers()
                    )
                )
                .forEach(et -> {
                        
                    exampleRegistry.register(et.getSimpleName(), et);
                });
    }
                
    
}

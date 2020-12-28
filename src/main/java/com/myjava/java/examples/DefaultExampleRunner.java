/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.java.examples;

/**
 *
 * @author ruslan
 */
public class DefaultExampleRunner implements ExampleRunner {
    
    private ExampleRegistry exampleRegistry;
    
    public DefaultExampleRunner(ExampleRegistry exampleRegistry)
    {
        this.exampleRegistry = exampleRegistry;
    }

    @Override
    public void runExample(String exampleName, Object... parameters) throws ExampleRunnerException {
        
        Class<?> exampleType = exampleRegistry.get(exampleName);
        
        try
        {
            Object exampleInstance = 
                    exampleType.getDeclaredConstructor().newInstance();
            
            exampleType
                .getMethod("run", Object[].class)
                    .invoke(exampleInstance, (Object)parameters);
        }
        
        catch (Exception ex)
        {
            throw new ExampleRunnerException(
                    String.format("Failed to run example \"%s\"", exampleName), 
                    ex
            );
        }
    } 
    
}

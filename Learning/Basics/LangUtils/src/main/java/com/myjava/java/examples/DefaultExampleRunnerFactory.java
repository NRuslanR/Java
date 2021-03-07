/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.java.examples;

import com.myjava.java.hibernate.examples.SimpleCRUDHibernateExample;

/**
 *
 * @author ruslan
 */

public class DefaultExampleRunnerFactory implements ExampleRunnerFactory {

    private ExampleRegistryFactory exampleRegistryFactory;
    
    public DefaultExampleRunnerFactory(ExampleRegistryFactory exampleRegistryFactory)
    {
        this.exampleRegistryFactory = exampleRegistryFactory;
    }
    
    @Override
    public ExampleRunner createExampleRunner() throws Exception {
        
        return new DefaultExampleRunner(exampleRegistryFactory.createExampleRegistry());
    }
    
}

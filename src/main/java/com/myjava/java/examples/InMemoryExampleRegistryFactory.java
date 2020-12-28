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
public class InMemoryExampleRegistryFactory extends AbstractExampleRegistryFactory {

    @Override
    protected ExampleRegistry createExampleRegistryInstance() {
        
        return new InMemoryExampleRegistry();
    }
 
}

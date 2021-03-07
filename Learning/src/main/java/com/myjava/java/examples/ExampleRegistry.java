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
public interface ExampleRegistry {
    
    <T> void register(String exampleName, Class<? extends T> exampleType);
    Class<?> get(String exampleName);
}

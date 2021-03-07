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

class ExampleRunnerException extends Exception
{
    public ExampleRunnerException(String message, Throwable cause)
    {
        super(message, cause);
    }
}

public interface ExampleRunner {
    
    void runExample(String exampleName, Object... parameters) throws ExampleRunnerException;
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.java;

import com.myjava.java.examples.*;
import java.util.*;
import java.lang.*;
import java.util.stream.*;
import static java.lang.System.out;
import java.util.function.BiFunction;
import com.myjava.java.streams.*;
/**
 *
 * @author ruslan
 */
public class Program {
    
    public static void main(String[] args) throws Exception
    {
        ExampleRunnerFactory exampleRunnerFactory =
                ExampleRunnerFactories.getExampleRunnerFactory(args[0]);
        
        ExampleRunner exampleRunner = exampleRunnerFactory.createExampleRunner();
        
        exampleRunner.runExample(args[1], Stream.of(args).skip(2).toArray());
    }
    
}

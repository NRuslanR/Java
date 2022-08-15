package org.learning.examples;

import java.io.FileNotFoundException;

public class ExampleException extends Exception {
    
    public ExampleException(String message)
    {
        super(message);
    }

    public ExampleException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}

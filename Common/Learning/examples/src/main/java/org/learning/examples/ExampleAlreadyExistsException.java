package org.learning.examples;

public class ExampleAlreadyExistsException extends ExampleException {

    public ExampleAlreadyExistsException()
    {
        this((Throwable)null);
    }

    public ExampleAlreadyExistsException(Throwable throwable) 
    {
        super("Example is already exists", throwable);
    }

    public ExampleAlreadyExistsException(String message)
    {
        super(message);
    }

    public ExampleAlreadyExistsException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}

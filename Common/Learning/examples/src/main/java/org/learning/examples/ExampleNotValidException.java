package org.learning.examples;

public class ExampleNotValidException extends ExampleException {

    public ExampleNotValidException()
    {
        this((Throwable)null);
    }

    public ExampleNotValidException(Throwable throwable)
    {
        super("Example isn't valid", throwable);
    }

    public ExampleNotValidException(String message)
    {
        super(message);
    }

    public ExampleNotValidException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}

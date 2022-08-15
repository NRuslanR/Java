package org.learning.examples;

public interface Example {

    default String getName()
    {
        return "Unknown Example";
    }

    default void setName(String name)
    {

    }

    void run();
    void run(Object data);

}

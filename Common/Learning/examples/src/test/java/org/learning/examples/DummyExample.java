package org.learning.examples;

import static java.lang.System.out;

public class DummyExample extends AbstractExample {

    public DummyExample()
    {
        this("DummyExample");
    }

    public DummyExample(String name) {
        super(name);
    }

    @Override
    public void run(Object data) {
        
        out.println(getName() + " completed");
        
    }
    
}

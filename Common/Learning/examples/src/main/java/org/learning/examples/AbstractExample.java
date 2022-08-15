package org.learning.examples;

import java.util.Objects;

public abstract class AbstractExample implements Example {

    private String name;

    protected AbstractExample(String name)
    {
        this.name = name;
    }

    @Override
    public String getName() {

        return name;

    }
    
    @Override
    public void setName(String name) {

        this.name = name;

    }

    @Override
    public boolean equals(Object obj) {

        return (obj != null) && equals((Example) obj);
    }
    
    public boolean equals(Example example)
    {
        return getName() == example.getName();
    }

    @Override
    public int hashCode() {
        
        return Objects.hash(name);

    }

    @Override
    public String toString() {
        
        return name;
    }

    @Override
    public void run() {
        
        run(null);
        
    }
}

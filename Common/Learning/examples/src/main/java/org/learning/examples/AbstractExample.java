package org.learning.examples;

import java.util.Objects;

public abstract class AbstractExample implements Example {

    private String name;

    public AbstractExample()
    {

    }
    
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
        
        return !Objects.isNull(obj) && getClass().equals(obj.getClass()) && equals((Example) obj);
    }
    
    protected boolean equals(Example example)
    {
        return getName().equals(example.getName());
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

package org.learning.examples;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

public class ExamplesTests {
    
    @Test()
    public void shouldAddExampleWhenItDataCorrect() throws ExampleException
    {
        Examples examples = new Examples();

        Example example = new DummyExample();

        examples.add(new DummyExample());

        assertEquals(examples.getExampleCount(), 1);
        assertEquals(example, examples.getExample(example.getName()));
    }

    @Test(expected = ExampleNotValidException.class)
    public void shouldNotAddExampleWhenItNotValid() throws ExampleException
    {
        Examples examples = new Examples();

        examples.add(new DummyExample(""));
    }

    @Test(expected = ExampleAlreadyExistsException.class)
    public void shouldNotAddExampleWhenItAlreadyExists() throws ExampleException
    {
        Examples examples = Examples.of(
                    new DummyExample("Dummy1"),
                    new DummyExample("Dummy2"),
                    new DummyExample("Dummy3")
                );
        
        examples.add(new DummyExample("Dummy2"));
    }
    
    @Test
    public void shouldRemoveExampleWhenItExists() throws ExampleException
    {
        Examples examples = Examples.of(Arrays.asList(new DummyExample("Dummy")));

        examples.remove("Dummy");

        assertEquals(examples.getExampleCount(), 0);
    }

    @Test(expected = ExampleException.class)
    public void shouldNotRemoveWhenExampleNotFound() throws ExampleException
    {
        Examples examples = new Examples();

        examples.remove("Dummy");
    }
}

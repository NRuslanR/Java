package org.learning.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

public class Examples implements Iterable<Example> {

    private Collection<Example> examples;

    public static Examples of(Example... examples) throws ExampleException
    {
        return of(Arrays.asList(examples));
    }

    public static Examples of(Collection<Example> initial) throws ExampleException
    {
        return new Examples(initial);
    }

    public Examples() {

        createExampleList();
    }

    public Examples(Collection<Example> examples) throws ExampleException {

        Objects.requireNonNull(examples, "Initial examples is null");

        createExampleList();
        fillExampleListFrom(examples);
    }

    private void createExampleList()
    {
        examples = new ArrayList<>();
    }

    private void fillExampleListFrom(Collection<Example> examples) throws ExampleException {

        for (Example example : examples)
            add(example);
    }


    @Override
    public Iterator<Example> iterator() {

        return examples.iterator();
    }
    
    public boolean contains(String name)
    {
        return examples.stream().anyMatch(e -> e.getName() == name);
    }

    public void add(Example example) throws ExampleException {

        Objects.requireNonNull(example, "Example is null");

        if (example.getName().trim() == "")
            throw new ExampleNotValidException("Example name is empty");

        if (contains(example.getName()))
            throw new ExampleAlreadyExistsException();

        examples.add(example);
    }

    public void remove(String name) throws ExampleException {

        if (!contains(name))
        {
            throw new ExampleException(
                String.format("Example %s not found for removing", name)
            );
        }

        examples.removeIf(e -> e.getName() == name);
    }
 
    public Example getExample(String name) throws ExampleException
    {
        return 
            findExample(name)
                .orElseThrow(
                    () -> new ExampleException(
                        String.format("Example %s not found", name)
                    )
                );
                
    }

    public Optional<Example> findExample(String name)
    {
        return examples.stream().filter(e -> e.getName().equals(name)).findFirst();
    }

    public void run(String name) throws ExampleException
    {
        getExample(name).run();
    }

    public void run(String name, Object data) throws ExampleException
    {
        getExample(name).run(data);
    }
    
    public int getExampleCount()
    {
        return examples.size();
    }
}

package com.learning.hibernate.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.learning.examples.Example;
import org.learning.examples.Examples;

import com.learning.hibernate.examples.EntityLifeCycleExample;

public class HibernateExamplesUtils {
    
    private static Examples examples;

    public static Examples getExamples() throws Exception
    {
        if (!Objects.isNull(examples))
            return examples;

        examples = Examples.of(new EntityLifeCycleExample());

        return examples;
    }
    
    public static void runExample(String[] args) throws Exception
    {
        if(!ArrayUtils.isEmpty(args))
            runExample(args[0], Arrays.stream(args).skip(1).toArray(String[]::new));

        else runRandomExample();
    }

    private static void runExample(String name, String[] params) throws Exception {

        getExamples().run(name);

    }

    private static void runRandomExample() throws Exception {

        List<Example> exampleList = IterableUtils.toList(getExamples());

        exampleList.get(
            RandomUtils.nextInt(0, exampleList.size())
        ).run();
    }
}

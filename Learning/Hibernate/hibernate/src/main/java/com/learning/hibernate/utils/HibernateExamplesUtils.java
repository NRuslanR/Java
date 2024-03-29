package com.learning.hibernate.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.learning.examples.Example;
import org.learning.examples.Examples;

import com.learning.hibernate.examples.AttributeConvertersExample;
import com.learning.hibernate.examples.BatchOperationsExample;
import com.learning.hibernate.examples.CriteriaAPIExample;
import com.learning.hibernate.examples.EntityGraphExample;
import com.learning.hibernate.examples.EntityIdentifyingStrategiesExample;
import com.learning.hibernate.examples.EntityLifeCycleEventsExample;
import com.learning.hibernate.examples.EntityLifeCycleExample;
import com.learning.hibernate.examples.FetchModeExample;
import com.learning.hibernate.examples.InheritanceExample;
import com.learning.hibernate.examples.JPAQueryTypesExample;
import com.learning.hibernate.examples.MappingEntityToMultipleTablesExample;
import com.learning.hibernate.examples.OneToManyExample;
import com.learning.hibernate.examples.OneToOneExample;
import com.learning.hibernate.examples.SortingAndPaginationExample;

public class HibernateExamplesUtils {
    
    private static Examples examples;

    public static Examples getExamples() throws Exception
    {
        if (!Objects.isNull(examples))
            return examples;

        examples = 
                Examples.of(
                    new EntityLifeCycleExample(),
                    new EntityLifeCycleEventsExample(),
                    new AttributeConvertersExample(),
                    new OneToOneExample(),
                    new OneToManyExample(),
                    new InheritanceExample(),
                    new MappingEntityToMultipleTablesExample(),
                    new EntityIdentifyingStrategiesExample(),
                    new JPAQueryTypesExample(),
                    new SortingAndPaginationExample(),
                    new BatchOperationsExample(),
                    new CriteriaAPIExample(),
                    new FetchModeExample(),
                    new EntityGraphExample()
                );

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

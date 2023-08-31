package tat.examples.bestpractices;

import java.time.LocalDate;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class RootDependency {
    
    private final Dependency1 dependency1;
    private final  Dependency2 dependency2;
    private final Converter<Mappee, Mapped> converter;
    private final PrototypeUtilizer prototypeUtilizer;

    @PostConstruct
    public void onConstructed()
    {
        log.info("Dependency1 class name: " + dependency1.getClass().getSimpleName());
        log.info("Dependency2 class name: " + dependency2.getClass().getSimpleName());

        Mappee mappee = new Mappee();

        mappee.setFirst(5);
        mappee.setSecond("tests");
        
        var subordinate = new Subordinate();
        
        subordinate.setCreatedAt(LocalDate.now());
        subordinate.setWeight(1.43f);
        
        mappee.setSubordinate(subordinate);

        var mapped = converter.convert(mappee);

        log.info(mapped.toString());

        var projectBuilder = prototypeUtilizer.createProjectBuilder();

        log.info("addr#1:" + projectBuilder);

        projectBuilder = prototypeUtilizer.createProjectBuilder();

        log.info("addr#2: " + projectBuilder);
    }
}

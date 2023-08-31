package tat.examples.bestpractices;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@Scope("prototype")
@RequiredArgsConstructor
public class ProjectBuilder {
    
    private final Dependency1 dependency1;
}

package tat.examples.bestpractices;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PrototypeUtilizer {
    
    private final ObjectFactory<ProjectBuilder> projectBuilderFactory;

    public ProjectBuilder createProjectBuilder()
    {
        return projectBuilderFactory.getObject();
    }
}

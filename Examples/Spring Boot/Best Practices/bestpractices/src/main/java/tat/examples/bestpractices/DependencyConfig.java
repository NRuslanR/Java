package tat.examples.bestpractices;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    basePackages = "tat.examples",
    basePackageClasses = { ProjectBuilder.class, PrototypeUtilizer.class }
)
public class DependencyConfig {
    
    @Bean
    public Dependency1 dependency1()
    {
        return new Dependency1Impl();
    }

    @Bean
    public Dependency2 dependency2()
    {
        return new Dependency2Impl();
    }

    @Bean
    public ModelMapper modelMapper()
    {
        var modelMapper = new ModelMapper();
        
        modelMapper.typeMap(Mappee.class, Mapped.class).addMappings(mapper -> {

            mapper.map(src -> src.getSubordinate().getCreatedAt(), Mapped::setSubordinateCreationDate);
            mapper.map(src -> src.getSubordinate().getWeight(), Mapped::setSubordinateVolume);

        });

        return modelMapper;
    }
}

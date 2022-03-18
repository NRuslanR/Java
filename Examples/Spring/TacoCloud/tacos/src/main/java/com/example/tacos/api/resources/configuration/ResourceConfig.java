package com.example.tacos.api.resources.configuration;

import com.example.tacos.api.resources.TacoModel;
import com.example.tacos.domain.Taco;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;

@Configuration
@ComponentScan
public class ResourceConfig {

    @Bean
    public RepresentationModelProcessor<PagedModel<TacoModel>> 
        tacoModelProcessor(EntityLinks links)
    {
        return new RepresentationModelProcessor<PagedModel<TacoModel>>() {

            @Override
            public PagedModel<TacoModel> process(PagedModel<TacoModel> model) {
                
                model.add(
                    links.linkFor(Taco.class)
                    .slash("recent")
                    .withRel("recents")
                );
                
                return model;
            }
            
        };
    }
}

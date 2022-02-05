package com.example.tacos;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.tacos.converters.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {
 
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("logout");
    }

    @Override
    public void addFormatters(FormatterRegistry registry)
    {
        registry.addConverter(new StringToIngredientConverter());
    }
}

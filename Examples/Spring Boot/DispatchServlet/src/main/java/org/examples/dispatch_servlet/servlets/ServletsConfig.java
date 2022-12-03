package org.examples.dispatch_servlet.servlets;

import org.apache.catalina.core.ApplicationContext;
import org.examples.dispatch_servlet.filters.CustomFilter;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.http.HttpServlet;

@Configuration
@ComponentScan(basePackages = { "org.examples.dispatch_servlet.servlets" })
public class ServletsConfig {

    @Bean
    public ServletRegistrationBean<HttpServlet> customServletBean()
    {
        return new ServletRegistrationBean<HttpServlet>(new CustomServlet(), "/servlet");
    }
    
}

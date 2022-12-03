package org.examples.dispatch_servlet.listeners;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenersConfig {
    
    @Bean
    public ServletListenerRegistrationBean customListenerBean()
    {
        return new ServletListenerRegistrationBean(new CustomListener());
    }
}

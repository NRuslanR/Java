package com.example.tacos.security;

import com.example.tacos.data.jpa.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

//@EnableWebFluxSecurity
public class ReactiveSecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http)
    {
        return
        http
            .authorizeExchange()
            .pathMatchers("/design", "/orders")
            .hasAuthority("USER")
            .anyExchange()
            .permitAll()
            .and()
            .formLogin()
                .loginPage("/login")         
            .and()
            .logout()
                .logoutUrl("/logout")
            .and()
            .csrf()
            .disable()
            .headers()
            .frameOptions()
            .disable()
            .and()
            .build();
    }
    
    @Bean
    public ReactiveUserDetailsService userDetailsService(UserRepository userRepository)
    {
        return 
            new ReactiveUserDetailsService() {

                @Override
                public Mono<UserDetails> findByUsername(String username) {
                    
                    return 
                        Mono.defer(
                            () -> Mono.just(userRepository.findByUsername(username))
                        )
                        .map(u -> u);
                }
                
            };
    }
}

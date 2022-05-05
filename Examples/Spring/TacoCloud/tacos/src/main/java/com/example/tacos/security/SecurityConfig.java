package com.example.tacos.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
 
        http
            .authorizeRequests()
            .antMatchers("/design", "/orders")
                .hasRole("USER")
            .antMatchers("/", "/**")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/design")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
            .httpBasic();
             
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        //configureInMemoryAuthentication(auth);
        //configureJdbcAuthentication(auth);
        configureUserDetailsServiceAuthentication(auth);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public PasswordEncoder encoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }

    private void configureUserDetailsServiceAuthentication(
            AuthenticationManagerBuilder auth
    ) throws Exception {
        
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(encoder());
    }

    private void configureJdbcAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth
            .jdbcAuthentication()
                .dataSource(dataSource)
                    .usersByUsernameQuery(
                        "select username, password, enabled " +
                        "from users " +
                        "where username=?"
                    )
                    .authoritiesByUsernameQuery(
                        "select username, authority " +
                        "from users_authorities " + 
                        "where username=?"
                )
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
                ;
    }

    private void configureInMemoryAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        
        auth
            .inMemoryAuthentication()
                .withUser("ruslan")
                .password("{noop}123456")
                    .authorities("ROLE_ADMIN")
                .and()
                .withUser("vitaly")
                .password("{noop}123456")
                    .authorities("ROLE_USER");
    }
}

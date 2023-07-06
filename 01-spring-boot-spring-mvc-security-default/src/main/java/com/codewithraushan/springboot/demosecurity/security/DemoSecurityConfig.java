package com.codewithraushan.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails ravi = User.builder()
                .username("ravi")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();
        UserDetails raushan = User.builder()
                .username("raushan")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER")
                .build();

        UserDetails sumit = User.builder()
                .username("sumit")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(ravi,raushan,sumit);

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(configure ->
                configure
                        .anyRequest().authenticated()
                )
                .formLogin(form ->
                          form
                                  .loginPage("/showMyLoginPage")
                                  .loginProcessingUrl("/authenticateTheUser")
                                  .permitAll()
                        );
        return http.build();
    }
}

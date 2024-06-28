package com.travelagency.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/user/register", "/user/login").permitAll()
                .antMatchers("/travel/**").hasRole("MANAGER")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/user/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }
}

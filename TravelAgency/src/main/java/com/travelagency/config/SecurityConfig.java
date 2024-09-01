package com.travelagency.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .antMatchers("/public/**", "/user/register", "/user/login", "/travel/all").permitAll()
                    .antMatchers("/manager/**").hasRole("MANAGER")
                    .anyRequest().authenticated()
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/user/login")
                    .permitAll()
            )
            .logout(logout ->
                logout
                    .permitAll()
            )
            .csrf().disable(); // Opcionalno: Uklanjanje CSRF zaštite u slučaju da koristiš REST API bez session-based autentifikacije
        return http.build();
    }
}

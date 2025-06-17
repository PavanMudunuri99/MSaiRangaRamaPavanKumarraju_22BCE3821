package com.hospital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Allow public access to register and login
                .requestMatchers("/api/user/register", "/api/user/login").permitAll()

                // Require ADMIN or STAFF roles for anything else under /api/user/**
                .requestMatchers("/api/user/**").hasAnyRole("ADMIN", "STAFF")

                // Allow public access to other APIs like /api/doctors, /api/patients
                .anyRequest().permitAll()
            )
            .httpBasic(Customizer.withDefaults()); // for testing with Postman

        return http.build();
    }
}

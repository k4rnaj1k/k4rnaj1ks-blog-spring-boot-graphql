package com.k4rnaj1k.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


// pls don't hack this, I'm sure I've left a few vulnerabilities,
// but I don't have time to fix them right now
@Configuration
@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @DependsOnDatabaseInitialization
    public SecurityFilterChain securityFilterChain(HttpSecurity http, @Value("${blog.default.page}") String defaultHomePage) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/graphiql").hasRole("ADMIN")
                        .requestMatchers("/graphql").permitAll()
                        .anyRequest().authenticated())
                .formLogin(login -> login.defaultSuccessUrl(defaultHomePage))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

}

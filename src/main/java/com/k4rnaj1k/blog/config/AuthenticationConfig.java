package com.k4rnaj1k.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

//separate config to avoid recursive dependency =(
@Configuration
public class AuthenticationConfig {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
                                DataSource dataSource,
                                PasswordEncoder passwordEncoder,
                                @Value("${blog.default-admin.username}") String username,
                                @Value("${blog.default-admin.password}") String password)
            throws Exception {
        auth.jdbcAuthentication()
                .passwordEncoder(passwordEncoder)
                .dataSource(dataSource)
                .withUser(User.withUsername(username)
                        .password(passwordEncoder.encode(password))
                        .roles("ADMIN"));
    }

}

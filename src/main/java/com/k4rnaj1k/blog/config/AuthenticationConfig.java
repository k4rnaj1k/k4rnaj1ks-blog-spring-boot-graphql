package com.k4rnaj1k.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

//separate config to avoid recursive dependency =(
@Configuration
public class AuthenticationConfig {

    @Autowired
    @DependsOnDatabaseInitialization
    public void configureGlobal(AuthenticationManagerBuilder auth,
                                DataSource dataSource,
                                PasswordEncoder passwordEncoder)
            throws Exception {
        auth.jdbcAuthentication()
                .passwordEncoder(passwordEncoder)
                .dataSource(dataSource);
    }

}

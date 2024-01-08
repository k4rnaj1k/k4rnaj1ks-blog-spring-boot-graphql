package com.k4rnaj1k.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

//TODO: create mappers, possibly services
//TODO: better error handling
@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class BlogSpringBootGraphqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogSpringBootGraphqlApplication.class, args);
    }

}

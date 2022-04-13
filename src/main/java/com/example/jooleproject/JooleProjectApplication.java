package com.example.jooleproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JooleProjectApplication {

    public static void main(String[] args) {

        SpringApplication.run(JooleProjectApplication.class, args);
    }

}

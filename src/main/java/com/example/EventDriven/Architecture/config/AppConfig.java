package com.example.EventDriven.Architecture.config;

import com.example.EventDriven.Architecture.dto.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public User user() {
        return new User();
    }

}

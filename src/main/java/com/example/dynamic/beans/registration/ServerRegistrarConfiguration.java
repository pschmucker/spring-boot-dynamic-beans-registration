package com.example.dynamic.beans.registration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
public class ServerRegistrarConfiguration {

    @Bean
    public static ServerRegistrar serverRegistrar(Environment environment) {
        return new ServerRegistrar(environment);
    }
}

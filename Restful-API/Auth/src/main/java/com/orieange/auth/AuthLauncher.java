package com.orieange.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableDiscoveryClient
@ImportResource("classpath:dubbo-consumer.xml")
public class AuthLauncher {

    public static void main(String[] args) {
        SpringApplication.run(AuthLauncher.class, args);
    }
}

package com.thesis.serverfurnitureecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ServerFurnitureEcommerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerFurnitureEcommerceApplication.class, args);
    }
}

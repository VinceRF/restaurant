package com.sparta.apiprac.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantApplication.class, args);
    }

}

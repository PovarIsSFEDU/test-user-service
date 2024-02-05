package com.plukash.testuserservice;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TestUserServiceApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @Bean
    public ApplicationRunner init() {

        return args -> {
        };
    }

}

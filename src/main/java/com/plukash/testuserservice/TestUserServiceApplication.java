package com.plukash.testuserservice;

import com.plukash.testuserservice.entities.PhoneData;
import com.plukash.testuserservice.entities.User;
import com.plukash.testuserservice.repositories.UserRepository;
import com.plukash.testuserservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TestUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TestUserServiceApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }


    @Bean
    public ApplicationRunner init(UserService userService) {

        userService.init();
        return args -> {
        };
    }

}

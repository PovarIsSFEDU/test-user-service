package com.plukash.testuserservice;

import com.plukash.testuserservice.Jobs.QuartzTask;
import com.plukash.testuserservice.services.UserService;
import org.quartz.SchedulerException;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TestUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TestUserServiceApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }


    @Bean
    public ApplicationRunner init(UserService userService, QuartzTask task) throws SchedulerException {
        userService.init();
        task.scheduleTask();
        return args -> {
        };
    }

}

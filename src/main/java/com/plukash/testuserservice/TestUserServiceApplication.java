package com.plukash.testuserservice;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(TestUserServiceApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

}

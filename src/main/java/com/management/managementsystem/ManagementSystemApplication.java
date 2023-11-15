package com.management.managementsystem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class ManagementSystemApplication {


	public static void main(String[] args) {
		SpringApplication.run(ManagementSystemApplication.class, args);
	}

}

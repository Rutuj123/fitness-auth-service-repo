package com.gym.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gym.entity.User;
import com.gym.repository.UserRepository;

@Configuration
public class AdminInitializer {

	@Bean
CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		System.out.println("in AdminInitializer");
	return args -> {
		// Check if admin already exists
        if (userRepository.findByUsername("admin").isEmpty()) {

            User admin = new User();

            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");

            userRepository.save(admin);

            System.out.println("Default admin created");
        }
	};
	
}	
}

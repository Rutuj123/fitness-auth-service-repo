package com.gym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SmartGymManagementPlatformApplication {
    
	@Bean
	@LoadBalanced //It is use when we calling services by using name and not with hardcoded port no
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(SmartGymManagementPlatformApplication.class, args);
	}

}

package com.gym.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.gym.dto.AuthResponse;
import com.gym.dto.LoginRequest;
import com.gym.dto.RegisterRequest;
import com.gym.dto.Trainer;
import com.gym.service.AuthService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
    private AuthService service;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger=LoggerFactory.getLogger(AuthController.class);
	  int retryCount=1;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
    	System.out.println(" request     password "+request.getPassword());
        service.register(request);
    }

    @PostMapping("/login")
   // @CircuitBreaker(name="trainerServiceBreaker",fallbackMethod ="trainerserviceFallback")
   // @Retry(name="trainerserviceRetry",fallbackMethod = "trainerserviceFallback")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
    	AuthResponse auth= service.login(request);
    	//HttpHeaders headers=new HttpHeaders();
    	//headers.set("Authorization", "Bearer "+auth.getToken());
    	//HttpEntity<String> entity=new HttpEntity<>(headers);
    /*	ResponseEntity<List<Trainer>> response=restTemplate.exchange("http://localhost:8083/trainers", HttpMethod.GET,
    			                                                         entity,
    			                                                         new ParameterizedTypeReference<List<Trainer>>(){}
    	                                                             );*/
    
	/*
	 * logger.info("retryCount  :  "+retryCount++); ResponseEntity<List<Trainer>>
	 * response=restTemplate.exchange("http://TRAINER-SERVICE/trainers",
	 * HttpMethod.GET, entity, new ParameterizedTypeReference<List<Trainer>>(){} );
	 * 
	 * 
	 * List<Trainer> trainers=response.getBody(); auth.setList(trainers);
	 * logger.info("",trainers);
	 */
    	return ResponseEntity.ok(auth);
    }
  
    //this method is for circuitbreaker, resillience4j,retry
    public ResponseEntity<AuthResponse> trainerserviceFallback(LoginRequest request, Exception e){
    
    	AuthResponse auth= new AuthResponse();
    	auth.setUserId(1111L);
    	auth.setRole("DummyRole");
    	auth.setToken("DummyToken");
    	return  ResponseEntity.ok(auth);
    }
}

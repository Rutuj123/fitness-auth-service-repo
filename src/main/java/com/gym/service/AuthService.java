 package com.gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gym.dto.AuthResponse;
import com.gym.dto.LoginRequest;
import com.gym.dto.RegisterRequest;
import com.gym.entity.User;
import com.gym.repository.UserRepository;
import com.gym.security.JwtUtil;

@Service
public class AuthService {
	@Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;
    
    
    public void register(RegisterRequest req) {
    	User user=new User();
    	 user.setUsername(req.getUsername());
         user.setPassword(encoder.encode(req.getPassword()));
         user.setRole(req.getRole());
         repo.save(user);
    }
    public AuthResponse login(LoginRequest req) {
        User user = repo.findByUsername(req.getUsername())
                .orElseThrow();

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        return new AuthResponse(token, user.getRole(),user.getId());
    }
}

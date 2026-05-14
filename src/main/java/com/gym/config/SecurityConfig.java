package com.gym.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//@Autowired	
//private JwtAuthenticationFilter JwtAuthenticationFilter;  //this should not use when using API Gateway

@Bean
SecurityFilterChain filterChain(HttpSecurity http) {
	/*
	 * http.csrf(csrf->csrf.disable()) .authorizeHttpRequests(auth->
	 * auth.requestMatchers("/auth/**").permitAll() .anyRequest().authenticated() )
	 * .sessionManagement(sess->
	 * sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //
	 * .addFilterBefore(JwtAuthenticationFilter,UsernamePasswordAuthenticationFilter
	 * .class);
	 * 
	 * return http.build();
	 */
	
	http
    .csrf(csrf -> csrf.disable())
    .authorizeHttpRequests(auth -> auth
        .requestMatchers("/auth/**").permitAll()
        .requestMatchers("/trainers/**").permitAll() // ADD THIS
        .anyRequest().authenticated()
    )
    .formLogin(form -> form.disable())
    .httpBasic(Customizer.withDefaults());

return http.build();
}

@Bean
PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}

}

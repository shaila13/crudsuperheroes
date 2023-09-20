package com.hiberus.crudsuperheroes.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hiberus.crudsuperheroes.controller.SuperHeroeController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@AllArgsConstructor
public class WebSecurityConfig {
	
	@Value("${security.pass}")
 	private static String pass;
	private final UserDetailsService userDetailsService;
	private final JwtAuthorizationFilter jwtAuthorizationFilter;
	
    @Bean
    SecurityFilterChain filterChain(AuthenticationManager auth,HttpSecurity http) throws Exception  {
    	
    	JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
    	
    	jwtAuthenticationFilter.setAuthenticationManager(auth);
    	jwtAuthenticationFilter.setFilterProcessesUrl("/login");
    	
    	return http.csrf().disable()
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)				
		.and()
		.addFilter(jwtAuthenticationFilter)
		.addFilterBefore(jwtAuthorizationFilter,UsernamePasswordAuthenticationFilter.class)
		.build();

    }

    @Bean
    AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder ,HttpSecurity http ) throws Exception{

    	return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService)
    			.passwordEncoder(passwordEncoder())
    			.and().build();
    }
    
    @Bean
    PasswordEncoder passwordEncoder(){
    	return new BCryptPasswordEncoder();
    }
    
    
	public static void main(String[] args) {
        log.error("pass ",new BCryptPasswordEncoder().encode(pass));
	}
    
}

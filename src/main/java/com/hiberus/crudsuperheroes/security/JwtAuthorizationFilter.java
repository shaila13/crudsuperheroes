package com.hiberus.crudsuperheroes.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hiberus.crudsuperheroes.common.JwtConstant;


@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
 

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

        String bearerToken = request.getHeader(JwtConstant.AUTHORIZATION_HEADER_STRING);
        
        if (bearerToken != null && !bearerToken.isBlank() && bearerToken.startsWith(JwtConstant.TOKEN_BEARER_PREFIX)) {
        	String token = bearerToken.replace(JwtConstant.TOKEN_BEARER_PREFIX, "");
            UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(usernamePAT);
            
        }

    	filterChain.doFilter(request, response);
		
	}

}
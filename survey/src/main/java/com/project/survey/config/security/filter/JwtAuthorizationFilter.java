package com.project.survey.config.security.filter;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.survey.config.security.jwt.JwtTokenProvider;
import com.project.survey.config.security.jwt.JwtTokenValidator;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter{

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenValidator jwtTokenValidator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);
        if(token != null){
            if(jwtTokenValidator.isValidToken(token)){
                Authentication authenticationToken = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }else{
                log.debug("Invalid JWT token: {}", token);
            }
        }else{
            log.debug("No JWT token found in request headers");
        }
        filterChain.doFilter(request, response);
    }
    
}

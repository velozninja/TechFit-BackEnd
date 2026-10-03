package com.TechFit.TechFit.config;

import com.TechFit.TechFit.service.RateLimitService;
import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component

public class RateLimitFilter extends OncePerRequestFilter {

    private final RateLimitService rateLimitService;

    public RateLimitFilter(RateLimitService rateLimitService) {
        this.rateLimitService = rateLimitService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String key = getKey(request);
        String path = request.getRequestURI();
        Bucket bucket = null;

        if(path.startsWith("/v1/auth/")){
            bucket = rateLimitService.getBucket(key,10, 10, 1);
        } else if(path.startsWith("/v1/personal/")){
            bucket = rateLimitService.getBucket(key,60, 60, 1);
        } else if(path.startsWith("/v1/workout/")){
            bucket = rateLimitService.getBucket(key,30, 30, 1);
        } else if(path.startsWith("/v1/User/")){
            bucket = rateLimitService.getBucket(key,10, 10, 1);
        }
        

        if (!bucket.tryConsume(1)) {

            response.setStatus(429);
            response.setContentType("application/json");

            response.getWriter().write("""
                    {
                        "error": "Too many requests"
                    }
                    """);

            return;
        }

        filterChain.doFilter(request, response);
    }

    private String getKey(HttpServletRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null &&
                authentication.isAuthenticated() &&
                authentication.getName() != null) {

            return "USER:" + authentication.getName();
        }

        return "IP:" + request.getRemoteAddr();
    }
}
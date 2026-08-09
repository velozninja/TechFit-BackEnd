package com.TechFit.TechFit.config;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenprovider;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String authorizedHeader = request.getHeader("Authorization");
       if(StringUtils.isNotBlank(authorizedHeader) && authorizedHeader.startsWith("Bearer ")){

           //validar token
          String token = authorizedHeader.substring(7);
          if (tokenprovider.isTokenValid(token)) {
              String username = tokenprovider.getUsername(token);
              UserDetails userDetails = userDetailsService.loadUserByUsername(username);
              UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
              SecurityContextHolder.getContext().setAuthentication(authenticationToken);
          }
       }
        filterChain.doFilter(request,response);
    }


}

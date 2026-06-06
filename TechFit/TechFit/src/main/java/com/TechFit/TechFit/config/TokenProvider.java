package com.TechFit.TechFit.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Component
public class TokenProvider {
    @Value("${jwt.key}")
    private String token;
    @Value("${jwt.expiration}")
    private long Expiration_time;
    //criar token
    public String gerarToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
         return buildtoken(userDetails.getUsername());


    }
    private String buildtoken(String Username){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Expiration_time);

        return Jwts.builder()
                .subject(Username)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSingingkey())
                .compact();
    }
    private SecretKey getSingingkey(){
        return Keys.hmacShaKeyFor(token.getBytes());
    }

    //validar token
    public Boolean isTokenValid(String token){
        try {
            parseToken(token);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }
    //extrair infos
    public String getUsername(String token){
        return parseToken(token).getSubject();
    }
    private Claims parseToken(String token){
        return Jwts.parser()
                .verifyWith(getSingingkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}

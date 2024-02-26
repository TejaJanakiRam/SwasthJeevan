package com.example.backend.config;

import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private static final String SECRET_KEY = "hnrwksbf3ytb3bufygubcyfhufhwoifjeqoifjwefbwhiuwiolfwiefhweuifhuiwejfipow";
    private SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());


    public String extractUsername(String jwt){
        jwt = jwt.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        String username = String.valueOf(claims.get("username"));
        return(username);
    }
    public String extractAuthority(String jwt){
        jwt = jwt.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        String authority = String.valueOf(claims.get("authority"));
        return(authority);
    }


    public String generateToken(Authentication auth) {
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populateAuthorities(authorities);
        String jwt = Jwts.builder().setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("username", auth.getName())
                .claim("authority", roles)
                .signWith(key)
                .compact();

        return jwt;
    }

    // public String getUsernameFromJwtToken(String jwt) {
    //     jwt = jwt.substring(7);
    //     Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
    //     String username = String.valueOf(claims.get("username"));
    //     return (username);
    // }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auth = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            auth.add(authority.getAuthority());
        }
        return (String.join(",", auth));
    }
}

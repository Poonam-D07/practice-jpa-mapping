package com.poonam.jpamapping.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-ms}")
    private long expirationMs;

    // Gupt key ko Java ki "key" cheez me badalna
    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    // Token banao
    public String generateToken(String username, String role){
        return Jwts.builder()
                .subject(username)
                .claim("role", role )
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ expirationMs))
                .signWith(getKey())
                .compact();
    }

    // Token kholo: mohar aur expiry yahin check hoti hai
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Pass pe likha naam nikalo
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Pass pe likha role nikalo
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // Token sahi hai ya nahi?
    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}

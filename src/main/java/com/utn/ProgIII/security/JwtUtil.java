package com.utn.ProgIII.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.expires}")
    private long expirationTime;

    public JwtUtil(){
        this.secret = System.getenv("SECRET_KEY");
        this.expirationTime = 28800000;
    }

    public String generateToken(UserDetails userDetails) {

        long currentMillis = System.currentTimeMillis();
        long expirationMillis = currentMillis + expirationTime;

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(expirationMillis))
                .signWith(getSignInKey())
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

//    public String extractRole(String token){
//        return extractAllClaims(token).get("roles").toString();
//    }

    public String extractRole(String token) {
        Claims claims = extractAllClaims(token);

        Object rolesObject = claims.get("roles");

        if (rolesObject instanceof List<?> rolesList && !rolesList.isEmpty()) {
            Object roleMap = rolesList.get(0);
            if (roleMap instanceof Map<?, ?> map) {
                Object authority = map.get("authority");
                return authority != null ? authority.toString() : null;
            }
        }

        return null;
    }

    private Claims extractAllClaims(String token) {

            return Jwts.parser()
//                    .clockSkewSeconds(30)
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
    }

    private boolean isTokenExpired(String token){
       Date expiration = extractAllClaims(token).getExpiration();
       Date now = new Date();

       return expiration.before(now);
    }

}
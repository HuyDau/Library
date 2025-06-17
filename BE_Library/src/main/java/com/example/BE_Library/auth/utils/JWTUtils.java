package com.example.BE_Library.auth.utils;

import com.example.BE_Library.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTUtils {
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;

    @Value("${application.security.jwt.secret-key}")
    private String secretStringValue; // Biến này sẽ nhận giá trị từ application.yml

    private SecretKey Key;

    @PostConstruct // Đảm bảo phương thức này chạy sau khi Spring đã inject các dependencies
    public void init() {
        if (secretStringValue == null || secretStringValue.isEmpty()) {
            System.err.println("WARNING: JWT secret-key is not configured. Using a default or throwing an error.");
        }
        byte[] keyBytes = Base64.getDecoder().decode(secretStringValue.getBytes(StandardCharsets.UTF_8));
        this.Key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("role", user.getRole());

        return Jwts.builder()
                .claims(claims)
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Key)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public String extractUserId(String token) {
        return extractClaims(token, claims -> claims.get("userId", String.class));
    }

    public String extractRole(String token) {
        return extractClaims(token, claims -> (String) claims.get("role"));
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(Jwts.parser().verifyWith(Key).build().parseSignedClaims(token).getPayload());
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().verifyWith(Key).build().parseSignedClaims(token); // Dòng này sẽ ném SignatureException
            return !isTokenExpired(token);
        } catch (Exception e) {
            System.err.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}

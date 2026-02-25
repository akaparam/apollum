package com.example.apollum.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Service
public class JwtServiceImpl implements JwtService {

    private final JwtConfig jwtConfig;
    private SecretKey key;

    public JwtServiceImpl(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @PostConstruct
    void init() {
        if (jwtConfig.getSecret() == null) {
            throw new IllegalStateException("JWT secret not configured");
        }
        this.key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());
    }

    @Override
    public String generateAccessToken(String userId, List<String> roles) {
        return generateToken(userId, roles, jwtConfig.expiry.accessToken, JwtTokenType.ACCESS);
    }

    @Override
    public String generateRefreshToken(String userId) {
        return generateToken(userId, null, jwtConfig.expiry.refreshToken, JwtTokenType.REFRESH);
    }

    private String generateToken(String userId, List<String> roles, long expiry, JwtTokenType tokenType) {
        JwtBuilder builder = Jwts.builder()
                .subject(userId)
                .claim("tkn", tokenType.toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiry))
                .issuer(jwtConfig.issuer)
                .audience().add(jwtConfig.audiences).and()
                .signWith(key);

        if (roles != null) {
            builder.claim("roles", roles);
        }

        return builder.compact();
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            fetchClaims(token);
            return true;
        }
        catch (JwtException ex) {
            return false;
        }
    }

    @Override
    public String extractUserId(String token) {
        return fetchClaims(token).getSubject();
    }

    @Override
    public List<String> extractRoles(String token) {
        return fetchClaims(token).get("roles", List.class);
    }

    @Override
    public boolean isAccessToken(String token) {
        return fetchClaims(token).get("tkn", String.class) == JwtTokenType.ACCESS.toString();
    }

    @Override
    public boolean isRefreshToken(String token) {
        return !isAccessToken(token);
    }

    private Claims fetchClaims(String token) throws JwtException {
        JwtParser jwtParser = Jwts.parser()
                .verifyWith(key)
                .requireIssuer(jwtConfig.issuer)
                .build();

        Claims claims = jwtParser.parseSignedClaims(token).getPayload();
        return claims;
    }
}

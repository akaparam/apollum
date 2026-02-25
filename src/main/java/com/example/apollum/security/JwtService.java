package com.example.apollum.security;

import java.util.List;

public interface JwtService {

    String generateAccessToken(String userId, List<String> roles);

    String generateRefreshToken(String userId);

    boolean isTokenValid(String token);

    String extractUserId(String token);

    List<String> extractRoles(String token);

    boolean isAccessToken(String token);

    boolean isRefreshToken(String token);
}

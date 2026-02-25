package com.example.apollum.security;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {
}

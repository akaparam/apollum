package com.example.apollum.security;

public interface AuthService {

    LoginResponse login(AuthRequest request);

    TokenResponse token(String refreshToken);

    void register(AuthRequest request);
}


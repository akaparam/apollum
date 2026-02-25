package com.example.apollum.security;

public record AuthRequest(
        String username,
        String password
) {
}

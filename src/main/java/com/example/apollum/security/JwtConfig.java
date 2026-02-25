package com.example.apollum.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
@ConfigurationProperties("app.security.jwt")
@Getter
@Setter
public class JwtConfig {

    JwtExpiry expiry;

    String issuer;

    List<String> audiences;

    String secret;

    @Getter
    @Setter
    public static class JwtExpiry {
        long accessToken;
        long refreshToken;
    }

}
package com.example.apollum.security;


import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtServ;

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(AuthRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        Account account = (Account) authentication.getPrincipal();
        List<String> roles = List.of(account.getRole());

        String accessToken = jwtServ.generateAccessToken(account.getId().toString(), roles);
        String refreshToken = jwtServ.generateRefreshToken(account.getId().toString());

        return new LoginResponse(accessToken, refreshToken);
    }

    @Override
    public TokenResponse token(String refreshToken) {

        if (!jwtServ.isTokenValid(refreshToken) || !jwtServ.isRefreshToken(refreshToken)) {
            throw new JwtException("Invalid Refresh token... Please login again");
        }

        String userId = jwtServ.extractUserId(refreshToken);

        Optional<Account> account = accountRepo.findById(UUID.fromString(userId));
        List<String> roles = List.of(account.get().getRole()); // TODO: account.get().getRoles();

        return new TokenResponse(jwtServ.generateAccessToken(userId, roles));
    }

    @Override
    public void register(AuthRequest request) {

        Optional<Account> account = accountRepo.findByUsername(request.username());

        if (account.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        Account newAccount = new Account();
        newAccount.setUsername(request.username());
        newAccount.setPassword(passwordEncoder.encode(request.password()));
        newAccount.setRole("PATIENT");

        accountRepo.save(newAccount);
    }
}

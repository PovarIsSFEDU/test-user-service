package com.plukash.testuserservice.services;


import com.plukash.testuserservice.entities.DTO.Auth.AuthMailRequest;
import com.plukash.testuserservice.entities.DTO.Auth.AuthResponse;
import com.plukash.testuserservice.entities.DTO.Auth.AuthPhoneRequest;
import com.plukash.testuserservice.entities.User;
import com.plukash.testuserservice.repositories.TokenRepository;
import com.plukash.testuserservice.repositories.UserRepository;
import com.plukash.testuserservice.utilities.CustomExceptions.IncorrectPasswordException;
import com.plukash.testuserservice.utilities.CustomExceptions.UserNotFoundException;
import com.plukash.testuserservice.utilities.Token.Token;
import com.plukash.testuserservice.utilities.Token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse authenticateEmail(AuthMailRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new IncorrectPasswordException();
        }
        try {
            var user = repository.findByEmail(request.getEmail())
                    .orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);
            return AuthResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException("User with such email not found!");
        }
    }

    public AuthResponse authenticatePhone(AuthPhoneRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getPhone(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new IncorrectPasswordException();
        }
        try {
            var user = repository.findByPhone(request.getPhone())
                    .orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);
            return AuthResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException("User with such phone not found!");
        }
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}

package com.plukash.testuserservice.controllers;


import com.plukash.testuserservice.entities.DTO.Auth.AuthMailRequest;
import com.plukash.testuserservice.entities.DTO.Auth.AuthResponse;
import com.plukash.testuserservice.entities.DTO.Auth.AuthUNameRequest;
import com.plukash.testuserservice.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateEmail(
            @RequestBody AuthMailRequest request
    ) {
        return ResponseEntity.ok(service.authenticateEmail(request));
    }

    @PostMapping("/login/uname")
    public ResponseEntity<AuthResponse> authenticateUName(
            @RequestBody AuthUNameRequest request
    ) {
        return ResponseEntity.ok(service.authenticateUName(request));
    }

}

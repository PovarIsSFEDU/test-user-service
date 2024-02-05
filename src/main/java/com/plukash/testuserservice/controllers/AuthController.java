package com.plukash.testuserservice.controllers;


import com.plukash.testuserservice.entities.DTO.Auth.AuthMailRequest;
import com.plukash.testuserservice.entities.DTO.Auth.AuthResponse;
import com.plukash.testuserservice.entities.DTO.Auth.AuthPhoneRequest;
import com.plukash.testuserservice.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateEmail(
            @RequestBody AuthMailRequest request,
            HttpServletResponse response
    ) {
        try {
            var resp = service.authenticateEmail(request);
            response.addCookie(new Cookie("Authorization", resp.getToken()));
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/login/uname")
    public ResponseEntity<AuthResponse> authenticatePhone(
            @RequestBody AuthPhoneRequest request,
            HttpServletResponse response
    ) {
        try {
            var resp = service.authenticatePhone(request);
            response.addCookie(new Cookie("Authorization", resp.getToken()));
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}

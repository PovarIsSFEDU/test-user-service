package com.plukash.testuserservice.controllers;


import com.plukash.testuserservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/email")
    public ResponseEntity<?> createEmail() {
        return null;
    }

    @PostMapping("/phone")
    public ResponseEntity<?> createPhone() {
        return null;
    }

    @PutMapping("/email")
    public ResponseEntity<?> changeEmail() {
        return null;
    }

    @PutMapping("/phone")
    public ResponseEntity<?> changePhone() {
        return null;
    }

    @DeleteMapping("/email")
    public ResponseEntity<?> deleteEmail() {
        return null;
    }

    @DeleteMapping("/phone")
    public ResponseEntity<?> deletePhone() {
        return null;
    }


    @GetMapping("/find")
    public ResponseEntity<?> findUsers() {
        return null;
    }

    @GetMapping("/transfer")
    public ResponseEntity<?> transferMoney() {
        return null;
    }

}

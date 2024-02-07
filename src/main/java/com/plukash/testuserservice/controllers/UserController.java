package com.plukash.testuserservice.controllers;


import com.plukash.testuserservice.entities.DTO.CRUD.Create.CreateEmailDTO;
import com.plukash.testuserservice.entities.DTO.CRUD.Create.CreatePhoneDTO;
import com.plukash.testuserservice.entities.DTO.CRUD.Delete.DeleteEmailDTO;
import com.plukash.testuserservice.entities.DTO.CRUD.Delete.DeletePhoneDTO;
import com.plukash.testuserservice.entities.DTO.CRUD.Update.UpdateEmailDTO;
import com.plukash.testuserservice.entities.DTO.CRUD.Update.UpdatePhoneDTO;
import com.plukash.testuserservice.entities.DTO.Errors.Error;
import com.plukash.testuserservice.entities.User;
import com.plukash.testuserservice.services.JwtService;
import com.plukash.testuserservice.services.UserService;
import com.plukash.testuserservice.utilities.CustomExceptions.AccessViolationException;
import com.plukash.testuserservice.utilities.CustomExceptions.DuplicateException;
import com.plukash.testuserservice.utilities.CustomExceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final JwtService jwtService;

//    Для создания RBMS достаточно зааннотировать все методы с помощью:
//    @PreAuthorize("hasRole(T(com.plukash.jwtauthbase.entities.Role).USER)")

    @PostMapping("/email")
    public ResponseEntity<?> createEmail(@NonNull @RequestBody CreateEmailDTO dto) {
        //Тянем ID пользователя из аутентифицированной сессии, можно тянуть из claims jwt, реализовано в следующем методе
        var authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            service.createEmail(authUser.getId(), dto);
            return ResponseEntity.ok(null);
        } catch (DuplicateException d) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(d.getMessage()));
        }
    }

    @PostMapping("/phone")
    public ResponseEntity<?> createPhone(@NonNull HttpServletRequest request, @NonNull @RequestBody CreatePhoneDTO dto) {
        // Реализация посредством вытаскивания из токена username и поиска в базе (security-проверки не требуются,
        // так как производятся в цепочке фильтров)
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userCredential;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        jwt = authHeader.substring(7);
        userCredential = jwtService.extractUsername(jwt);
        assert (userCredential != null);
        try {
            // Здесь происходит двойное обращение к базе, это плохо, можно оптимизировать, но поломает логику представления.
            // Хороший вариант, чтобы продемонстрировать кэширование.
            var user = service.loadByCred(userCredential);
            service.createPhone(user.getId(), dto);
            return null;
        } catch (UserNotFoundException | DuplicateException | AccessViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
        }
    }

    @PutMapping("/email")
    public ResponseEntity<?> changeEmail(@NonNull HttpServletRequest request, @NonNull @RequestBody UpdateEmailDTO dto) {
        // Реализация посредством вытаскивания из токена additional claim id и поиска в базе (security-проверки не требуются,
        // так как производятся в цепочке фильтров)
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final Long userId;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        jwt = authHeader.substring(7);
        userId = jwtService.extractId(jwt);
        assert (userId != null);
        try {
            service.changeEmail(userId, dto);
            return null;
        } catch (DuplicateException | AccessViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
        }
    }

    // Далее будет использоваться самый компактный способ
    @PutMapping("/phone")
    public ResponseEntity<?> changePhone(@NonNull @RequestBody UpdatePhoneDTO dto) {
        var authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            service.changePhone(authUser, dto);
            return null;
        } catch (DuplicateException | AccessViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
        }
    }

    @DeleteMapping("/email")
    public ResponseEntity<?> deleteEmail(@NonNull @RequestBody DeleteEmailDTO dto) {
        var authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            service.deleteEmail(authUser, dto);
            return null;
        } catch (DuplicateException | AccessViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
        }
    }

    @DeleteMapping("/phone")
    public ResponseEntity<?> deletePhone(@NonNull @RequestBody DeletePhoneDTO dto) {
        var authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            service.deletePhone(authUser, dto);
            return null;
        } catch (DuplicateException | AccessViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
        }
    }


    @GetMapping("/find")
    public ResponseEntity<?> findUsers() {
        var authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return null;
    }

    @GetMapping("/transfer")
    public ResponseEntity<?> transferMoney() {
        var authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return null;
    }

}

package com.springboot.todos.controllers;

import com.springboot.todos.request.LoginRequest;
import com.springboot.todos.request.RegisterRequest;
import com.springboot.todos.response.LoginResponse;
import com.springboot.todos.service.AuthService;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication Rest REST API Endpoints", description = "Operations related to register & login")
public class AuthenticationController {
    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }
 @ResponseStatus(HttpStatus.CREATED)
@PostMapping("/register")
 public void register(@Valid @RequestBody RegisterRequest registerRequest) throws Exception{
        authService.register(registerRequest);
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) throws Exception{
       return  authService.login(loginRequest);
    }
}

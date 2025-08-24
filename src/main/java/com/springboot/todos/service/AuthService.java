package com.springboot.todos.service;

import com.springboot.todos.request.LoginRequest;
import com.springboot.todos.request.RegisterRequest;
import com.springboot.todos.response.LoginResponse;

public interface AuthService {
    void register (RegisterRequest input) throws  Exception;
    LoginResponse login(LoginRequest loginrequest);
}

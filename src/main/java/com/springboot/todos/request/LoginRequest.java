package com.springboot.todos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginRequest {
    @Email(message = "invalid email format")
    @NotEmpty(message = "Email is mandatory")
    private String email ;

    @NotEmpty(message = "Password is mandatory")
    @Size(min = 5, max= 30, message = "Password must be atleast 5 words")
    private String password ;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

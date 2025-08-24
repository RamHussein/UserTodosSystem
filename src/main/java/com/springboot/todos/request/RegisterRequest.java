package com.springboot.todos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotEmpty(message = "First Name is mandatory")
    @Size(min = 3, max =30, message= "First name must be at least 3 char long")
    private String firstName ;

    @NotEmpty(message = "last Name is mandatory")
    @Size(min = 3, max =30, message= "last name must be at least 3 char long")
    private String lastName ;

    @NotEmpty(message = "Email is mandatory")
    @Email(message= "Invalid Email format")
    private String email ;

    @NotEmpty(message = "Password is mandatory")
    @Size(min = 3, max =30, message= "Password must be at least 3 char long")
    private String password ;

    public RegisterRequest(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

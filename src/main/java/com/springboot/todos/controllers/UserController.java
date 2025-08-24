package com.springboot.todos.controllers;

import com.springboot.todos.entity.User;
import com.springboot.todos.request.UpdatePasswordRequest;
import com.springboot.todos.response.UserResponse;
import com.springboot.todos.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService ;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping
    public UserResponse getUserInfo(){
        return userService.getUserinfo() ;
    }

@DeleteMapping
    public void deleteUser(){
        userService.deleteUser();
    }

    @PutMapping("/password")
    public void updatePassword(@Valid @RequestBody UpdatePasswordRequest updatePasswordRequest) throws Exception{
        userService.updatePassword(updatePasswordRequest);
    }

}

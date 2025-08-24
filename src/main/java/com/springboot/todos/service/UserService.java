package com.springboot.todos.service;


import com.springboot.todos.request.UpdatePasswordRequest;
import com.springboot.todos.response.UserResponse;

public interface UserService {
    UserResponse getUserinfo() ;
    void deleteUser() ;
    void updatePassword(UpdatePasswordRequest updatePasswordRequest);

}

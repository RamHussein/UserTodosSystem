package com.springboot.todos.service;

import com.springboot.todos.entity.User;
import com.springboot.todos.response.UserResponse;

import java.util.List;

public interface AdminService {
    List<UserResponse> getAllUsers();
    UserResponse promoteToAdmin(long id);
    void deleteUser(long id);
}

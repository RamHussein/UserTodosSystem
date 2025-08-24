package com.springboot.todos.controllers;

import com.springboot.todos.response.UserResponse;
import com.springboot.todos.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Tag(name = "Admin  REST API Endpoint")
@RequestMapping("/api/admin")
@RestController
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getAllusers")
    public List<UserResponse> getAllUsers(){
        return adminService.getAllUsers();
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable @Min(1) long id){
         adminService.deleteUser(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/updateRole/{id}")
    public UserResponse updateUser(@PathVariable @Min(1) long id){
      return  adminService.promoteToAdmin(id);
    }

}

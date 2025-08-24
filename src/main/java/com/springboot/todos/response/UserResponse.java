package com.springboot.todos.response;

import com.springboot.todos.entity.Authority;

import java.util.List;

public class UserResponse {
    private long id  ;
    private String fullname ;
    private String email ;
    private List<Authority> authorities ;

    public UserResponse(long id, String fullname, List<Authority> authorities, String email) {
        this.id = id;
        this.fullname = fullname;
        this.authorities = authorities;
        this.email = email ;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}

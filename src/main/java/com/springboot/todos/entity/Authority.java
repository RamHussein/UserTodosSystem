package com.springboot.todos.entity;

import jakarta.persistence.Embeddable;
import org.springframework.security.core.GrantedAuthority;
@Embeddable
public class Authority implements GrantedAuthority {

public String authority ;
public Authority(){}

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}

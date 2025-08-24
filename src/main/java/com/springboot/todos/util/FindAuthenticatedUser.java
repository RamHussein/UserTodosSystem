package com.springboot.todos.util;

import com.springboot.todos.entity.User;

public interface FindAuthenticatedUser {
    User getAuthenticatedUser();
}

package com.springboot.todos.service;

import com.springboot.todos.entity.Todo;
import com.springboot.todos.request.TodoRequest;
import com.springboot.todos.response.TodoResponse;

import java.util.List;

public interface TodoService {
    TodoResponse createTodo(TodoRequest todoRequest);
    List<TodoResponse> getAllTodos();
    TodoResponse toggleTodoCompletion(long id);
    void deleteTodo(long id);
}

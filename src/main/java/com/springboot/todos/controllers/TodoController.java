package com.springboot.todos.controllers;

import com.springboot.todos.entity.Todo;
import com.springboot.todos.request.TodoRequest;
import com.springboot.todos.response.TodoResponse;
import com.springboot.todos.service.TodoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.apache.coyote.Request;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService ;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @Tag(name = "Todo REST API endpoints")
@ResponseStatus(HttpStatus.CREATED)
@PostMapping
public TodoResponse createTodo(@Valid @RequestBody TodoRequest todoRequest){
    return todoService.createTodo(todoRequest);

}
@ResponseStatus(HttpStatus.OK)
@GetMapping("/getTodos")
public List<TodoResponse> getTodos() {
    return todoService.getAllTodos();

}
@ResponseStatus(HttpStatus.OK)
@PutMapping("/updateComplete/{id}")
    public TodoResponse updateCompelete(@PathVariable @Min(1) long id ){
        return todoService.toggleTodoCompletion(id);

    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/deleteTodo/{id}")
    public void deleteTodo(@PathVariable @Min(1) long id ){
todoService.deleteTodo(id);
    }
    }

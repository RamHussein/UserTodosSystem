package com.springboot.todos.service;

import com.springboot.todos.entity.Todo;
import com.springboot.todos.entity.User;
import com.springboot.todos.repository.TodoRepository;
import com.springboot.todos.request.TodoRequest;
import com.springboot.todos.response.TodoResponse;
import com.springboot.todos.util.FindAuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImp implements TodoService {
    private final TodoRepository todoRepository;
    private final FindAuthenticatedUser findAuthenticatedUser;

    public TodoServiceImp(TodoRepository todoRepository, FindAuthenticatedUser findAuthenticatedUser) {
        this.todoRepository = todoRepository;
        this.findAuthenticatedUser = findAuthenticatedUser;
    }

    @Override
    public TodoResponse createTodo(TodoRequest todoRequest) {
User currentUser = findAuthenticatedUser.getAuthenticatedUser();
Todo todo = new Todo(todoRequest.getTitle(), todoRequest.getDescription(), todoRequest.getPriority(), false, currentUser);

Todo savedTodo = todoRepository.save(todo);

        return convertToTodoResponse(savedTodo );

    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoResponse> getAllTodos() {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();

        return todoRepository.findByOwner(currentUser).stream().map(this::convertToTodoResponse).toList();
    }
@Transactional
    @Override
    public TodoResponse toggleTodoCompletion(long id) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();
        Optional<Todo> todo = todoRepository.findByIdAndOwner(id, currentUser);
        if (todo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There's no todo with this name");
        }
        todo.get().setComplete(!todo.get().isComplete());
        Todo updatedTodo = todoRepository.save(todo.get());
        return convertToTodoResponse(updatedTodo);
    }
@Transactional
    @Override
    public void deleteTodo(long id) {
    User currentUser = findAuthenticatedUser.getAuthenticatedUser();
    Optional<Todo> todo = todoRepository.findByIdAndOwner(id, currentUser);
    if (todo.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There's no todo with this name");
    }
    todoRepository.delete(todo.get() );

    }

    private TodoResponse convertToTodoResponse(Todo todo){
        return  new TodoResponse(todo.getId(), todo.getDescription(), todo.getTitle(), todo.isComplete(), todo.getPriority());
    }
}

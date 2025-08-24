package com.springboot.todos.repository;

import com.springboot.todos.entity.Todo;
import com.springboot.todos.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

    List<Todo> findByOwner(User owner);
    Optional<Todo> findByIdAndOwner(Long i, User owner );

}

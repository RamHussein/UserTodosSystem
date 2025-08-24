package com.springboot.todos.service;

import com.springboot.todos.entity.Authority;
import com.springboot.todos.entity.User;
import com.springboot.todos.repository.UserRepository;
import com.springboot.todos.response.TodoResponse;
import com.springboot.todos.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
@Service
public class AdminServiceImpl implements AdminService {
    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private  final UserRepository userRepository ;
    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {

        return StreamSupport.stream(userRepository.findAll().spliterator(), false).map(this::convertToUserResponse).toList();
    }

    @Override
    @Transactional
    public UserResponse promoteToAdmin(long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty() ||
                user.get().getAuthorities().
                        stream().
                        anyMatch(authority -> "ROLE_ADMIN".
                                equals(authority.getAuthority()) )){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "User doesn't exist or is already an admin");
        }
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority("ROLE_EMPLOYEE"));
        authorities.add(new Authority("ROLE_ADMIN"));
        user.get().setAuthorities(authorities);
        User savedUser = userRepository.save(user.get());

        return convertToUserResponse(savedUser);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty() ||
                user.get().getAuthorities().
                        stream().
                        anyMatch(authority -> "ROLE_ADMIN".
                                equals(authority.getAuthority()) )){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't delete an admin");
        }
        userRepository.delete(user.get());
    }

    public UserResponse convertToUserResponse(User user){
        return  new UserResponse(      user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getAuthorities().stream().map(auth -> (Authority) auth).toList(),
                user.getEmail());

    }
}

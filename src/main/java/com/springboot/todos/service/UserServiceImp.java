package com.springboot.todos.service;

import com.springboot.todos.entity.Authority;
import com.springboot.todos.entity.User;
import com.springboot.todos.repository.UserRepository;

import com.springboot.todos.request.UpdatePasswordRequest;
import com.springboot.todos.response.UserResponse;
import com.springboot.todos.util.FindAuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
private final FindAuthenticatedUser findAuthenticatedUser ;
    private final PasswordEncoder passwordEncoder ;


    public UserServiceImp(UserRepository userRepository, FindAuthenticatedUser findAuthenticatedUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.findAuthenticatedUser = findAuthenticatedUser;
        this.passwordEncoder = passwordEncoder;
    }




    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserinfo() {
      User user = findAuthenticatedUser.getAuthenticatedUser();
        return new UserResponse(
                user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getAuthorities().stream().map(auth -> (Authority) auth).toList(),
                user.getEmail()
        );
    }

    @Override
    public void deleteUser() {
        User user = findAuthenticatedUser.getAuthenticatedUser();

        if(isLastAdmin(user)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin  cannot delete itself");
        }
        userRepository.delete(user);
    }
@Transactional
@Override
    public void updatePassword(UpdatePasswordRequest updatePasswordRequest) {

        User user = findAuthenticatedUser.getAuthenticatedUser();
        if (!isOldPasswordCorrect(user.getPassword(), updatePasswordRequest.getOldPassword() )){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Current password is not correct");

        }
        if (!isNewPasswordConfirmed(updatePasswordRequest.getNewPassword(), updatePasswordRequest.getConfirmPassword() )){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "confirmed password doesn't match your new password");

        }
        if (!isNewPasswordDifferent(updatePasswordRequest.getOldPassword(), updatePasswordRequest.getNewPassword() )){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your old and new passwords are the same");

        }
user.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
        userRepository.save(user);

    }
 private boolean isOldPasswordCorrect(String currentPassword, String oldPassword){
                 return passwordEncoder.matches(oldPassword, currentPassword);
}
    private boolean isNewPasswordConfirmed(String newPassword, String confirmedPassword) {
        return newPassword.equals(confirmedPassword);
    }
    private boolean isNewPasswordDifferent(String newPassword, String oldPassword) {
        return !oldPassword.equals(newPassword);
    }
    private boolean isLastAdmin(User user){

        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(authority -> "ROLE_ADMIN"
                .equals(authority.getAuthority()));

        if(isAdmin){
            long adminCount = userRepository.countAdminUsers();
                    return adminCount <= 1 ;

        }
        return false ;
    }

}

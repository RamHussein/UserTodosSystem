package com.springboot.todos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UpdatePasswordRequest {

    @NotEmpty(message = "Old Password is mandatory")
    @Size(min = 5, max= 30, message = "Old Password must be atleast 5 words")
    private String oldPassword ;

    @NotEmpty(message = "new Password is mandatory")
    @Size(min = 5, max= 30, message = "new Password must be atleast 5 words")
    private String newPassword ;

    @NotEmpty(message = "Confirmed Password is mandatory")
    @Size(min = 5, max= 30, message = "Confirmed Password must be atleast 5 words")
    private String confirmPassword ;

    public UpdatePasswordRequest(String oldPassword, String newPassword, String confirmPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

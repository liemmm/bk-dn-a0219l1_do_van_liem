package com.lmao.areas.users.models.bindingModels;

import com.lmao.areas.users.customValidations.password.ArePasswordsMatching;
import com.lmao.areas.users.customValidations.username.IsUsernameExistent;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ArePasswordsMatching
public class UserBindingModel {

    @Pattern(regexp = "^[a-z0-9_-]{3,16}$",
            message = "Username can have letters, numbers, underscores or hyphens and must be between 3 and 16 symbols.")
    @NotEmpty(message = "Cannot be empty")
    @NotNull(message = "Cannot be null")
    @IsUsernameExistent(message = "This username already exists")
    private String username;

    @Pattern(regexp =
            "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$",
            message = "Invalid Email")
    @NotEmpty(message = "Cannot be empty")
    private String email;

    @Pattern(regexp = "^[a-z0-9_-]{6,18}$",
            message = "Password can have letters, numbers, underscores or hyphens and must be between 6 and 18 symbols.")
    @NotEmpty(message = "Cannot be empty")
    @NotNull(message = "Cannot be null")
    private String password;

    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

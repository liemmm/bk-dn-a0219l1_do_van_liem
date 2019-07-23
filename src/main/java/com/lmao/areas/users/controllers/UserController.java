package com.lmao.areas.users.controllers;

import com.lmao.areas.users.models.bindingModels.UserBindingModel;
import com.lmao.areas.users.models.viewModels.UserViewModel;
import com.lmao.areas.users.services.UserService;
import com.lmao.errors.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    @PreAuthorize("!isAuthenticated()")
    public String getRegisterPage(@ModelAttribute UserBindingModel userBindingModel) {
        return "user/register";
    }

    @PostMapping("/register")
    @PreAuthorize("!isAuthenticated()")
    public String registerUser(@Valid @ModelAttribute UserBindingModel userBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/register";
        }

        if (this.userService.register(userBindingModel)) {
            return "redirect:/login";
        }

        return "user/register";
    }

    @GetMapping("/login")
    @PreAuthorize("!isAuthenticated()")
    public String getLoginPage(@RequestParam(required = false, name = "error") String error, Model model) {
        if (error != null) {
            if (error.equals("Bad credentials")) {
                model.addAttribute("error", Errors.INVALID_CREDENTIALS);
            } else {
                model.addAttribute("error", error);
            }
        }

        return "user/login";
    }

    @GetMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public String getEditUserPage(@ModelAttribute UserBindingModel userBindingModel) {
        return "user/edit";
    }

    @PostMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public String editUser(@Valid @ModelAttribute UserBindingModel userBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }

        if (this.userService.edit(userBindingModel)) {
            return "redirect:/logout";
        }

        return "user/edit";
    }
}

package com.lmao.areas.users.controllers;

import com.lmao.areas.categories.models.CategoryViewModel;
import com.lmao.areas.categories.services.CategoryService;
import com.lmao.areas.users.models.viewModels.UserViewModel;
import com.lmao.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final CategoryService categoryService;

    @Autowired
    public AdminController(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @ModelAttribute(name = "users")
    public List<UserViewModel> getMagnitude() {
        List<UserViewModel> userViewModelList = this.userService.getAllUsers();

        return userViewModelList;
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllUsersPage() {

        return "admin/users";
    }

    @PostMapping("/users/lock/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String lockUser(@PathVariable String id) {
        this.userService.lockUserById(id);

        return "redirect:/admin/users";
    }

    @PostMapping("/users/unlock/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String unlockUser(@PathVariable String id) {
        this.userService.unlockUserById(id);

        return "redirect:/admin/users";
    }

    @GetMapping("/categories")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllCategoriesPage(Model model) {
        List<CategoryViewModel> categoryViewModels = this.categoryService.getAllCategories();
        model.addAttribute("categories", categoryViewModels);

        return "admin/categories";
    }

    @PostMapping("/users/make-admin/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String makeAdmin(@PathVariable String id) {
        this.userService.addRoleAdmin(id);

        return "redirect:/admin/users";
    }

    @PostMapping("/users/make-user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String makeUser(@PathVariable String id) {
        this.userService.addRoleUser(id);

        return "redirect:/admin/users";
    }

}

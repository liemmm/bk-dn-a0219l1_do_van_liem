package com.lmao.areas.categories.controllers;

import com.lmao.areas.categories.entities.Category;
import com.lmao.areas.categories.models.CategoryBindingModel;
import com.lmao.areas.categories.models.CategoryViewModel;
import com.lmao.areas.categories.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getCategoryCreatePage(@ModelAttribute CategoryBindingModel categoryBindingModel) {
        return "category/add-category";
    }

    @PostMapping("/category/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createCategory(@Valid @ModelAttribute CategoryBindingModel categoryBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "category/add-category";
        }

        this.categoryService.save(categoryBindingModel);

        return "redirect:/admin/categories";
    }

    @GetMapping("/category/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getCategoryEditPage(@ModelAttribute CategoryBindingModel categoryBindingModel, @PathVariable(name = "id") String id, Model model) {
        Category category = this.categoryService.findById(id);
        model.addAttribute("categoryName", category.getName());

        return "category/edit-category";
    }

    @PostMapping("/category/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editCategory(@Valid @ModelAttribute CategoryBindingModel categoryBindingModel, BindingResult bindingResult, @PathVariable(name = "id") String id) {
        if (bindingResult.hasErrors()) {
            return "category/edit-category";
        }

        this.categoryService.editCategory(categoryBindingModel, id);

        return "redirect:/admin/categories";
    }

}

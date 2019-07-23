package com.lmao.controllers;

import com.lmao.areas.categories.models.CategoryViewModel;
import com.lmao.areas.categories.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class HomeController {

    private final CategoryService categoryService;

    @Autowired
    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String getIndexPage() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String getHomePage(Model model) {
        List<CategoryViewModel> categories = this.categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "home";
    }
}

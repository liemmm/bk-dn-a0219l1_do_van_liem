package com.lmao.areas.images.controllers;

import com.lmao.areas.categories.models.CategoryViewModel;
import com.lmao.areas.categories.services.CategoryService;
import com.lmao.areas.images.customValidations.multipartFile.MultipartFileIsImage;
import com.lmao.areas.images.models.bindingModels.UploadImageBindingModel;
import com.lmao.areas.images.models.bindingModels.UploadImageFileBindingModel;
import com.lmao.areas.images.models.viewModels.ImageLaughViewModel;
import com.lmao.areas.images.services.ImageService;
import com.lmao.areas.users.entities.User;
import com.lmao.areas.users.services.UserService;
import com.lmao.errors.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    private final UserService userService;

    private final CategoryService categoryService;

    @Autowired
    public ImageController(ImageService imageService, UserService userService, CategoryService categoryService) {
        this.imageService = imageService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @ModelAttribute(name = "categories")
    public List<CategoryViewModel> getCategories() {
        List<CategoryViewModel> categories = this.categoryService.getAllCategories();

        return categories;
    }

    @GetMapping("/upload-url")
    @PreAuthorize("isAuthenticated()")
    private String getUploadImageUrlPage(@ModelAttribute UploadImageBindingModel uploadImageBindingModel) {

        return "images/upload-image-url";
    }

    @PostMapping("/upload-url")
    @PreAuthorize("isAuthenticated()")
    public String uploadImageUrl(@Valid @ModelAttribute UploadImageBindingModel uploadImageBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "images/upload-image-url";
        }

        this.imageService.save(uploadImageBindingModel);

        return "redirect:/";
    }

    @GetMapping("/upload-file")
    @PreAuthorize("isAuthenticated()")
    private String getUploadImageFilePage(@ModelAttribute UploadImageFileBindingModel uploadImageFileBindingModel) {

        return "images/upload-image-file";
    }

    @PostMapping("/upload-file")
    @PreAuthorize("isAuthenticated()")
    @MultipartFileIsImage(allowedExtensions = {"jpeg", "jpg", "png", "gif","bmp"})
    public String uploadImageFile(@Valid @ModelAttribute UploadImageFileBindingModel uploadImageFileBindingModel, BindingResult bindingResult, @RequestParam("file")MultipartFile file) {
        if (!file.getContentType().startsWith("image/")) {
            bindingResult.addError(new ObjectError("imageFile", Errors.INVALID_IMAGE));
        }

        if (bindingResult.hasErrors()) {
            return "images/upload-image-file";
        }

        this.imageService.save(uploadImageFileBindingModel, file);

        return "redirect:/";
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public String getCurrentImageLaughPage(@PathVariable(name = "id") String imageId, Model model) {
        ImageLaughViewModel imageLaughViewModel = this.imageService.getImageLaughViewModelById(imageId);
        model.addAttribute("imageLaughViewModel", imageLaughViewModel);
        return "images/image-details";
    }
}

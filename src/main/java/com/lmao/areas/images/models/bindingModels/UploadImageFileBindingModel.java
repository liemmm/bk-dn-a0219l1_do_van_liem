package com.lmao.areas.images.models.bindingModels;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotEmpty;

public class UploadImageFileBindingModel {
    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotEmpty(message = "Category cannot be empty")
    private String categoryId;

    public UploadImageFileBindingModel() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}

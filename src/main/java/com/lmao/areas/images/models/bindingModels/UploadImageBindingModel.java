package com.lmao.areas.images.models.bindingModels;

import com.lmao.areas.images.customValidations.urls.IsUrlValid;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UploadImageBindingModel {

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @Pattern(regexp = "^((https?|ftp):)?\\/\\/.*(jpeg|jpg|png|gif|bmp|webp)$",
            message = "URL must have jpeg, jpg, png, gif or bmp file extentions")
    @IsUrlValid(message = "Invalid Image URL")
    @NotEmpty(message = "Image URL cannot be empty")
    private String imageUrl;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

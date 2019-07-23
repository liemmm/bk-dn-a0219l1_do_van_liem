package com.lmao.areas.categories.services;

import com.lmao.areas.categories.entities.Category;
import com.lmao.areas.categories.models.CategoryBindingModel;
import com.lmao.areas.categories.models.CategoryViewModel;

import java.util.List;

public interface CategoryService {
    void save(CategoryBindingModel categoryBindingModel);

    List<CategoryViewModel> getAllCategories();

    Category findById(String id);

    void removeById(String id);

    void editCategory(CategoryBindingModel categoryBindingModel, String id);
}

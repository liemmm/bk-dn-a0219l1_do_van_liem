package com.lmao.areas.categories.servicesImpl;

import com.lmao.areas.categories.entities.Category;
import com.lmao.areas.categories.models.CategoryBindingModel;
import com.lmao.areas.categories.models.CategoryViewModel;
import com.lmao.areas.categories.repositories.CategoryRepository;
import com.lmao.areas.categories.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(CategoryBindingModel categoryBindingModel) {
        Category category = this.modelMapper.map(categoryBindingModel, Category.class);
        this.categoryRepository.save(category);
    }

    @Override
    public List<CategoryViewModel> getAllCategories() {
        return this.categoryRepository
                .findAll()
                .stream().map(x -> this.modelMapper.map(x, CategoryViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Category findById(String id) {
        return this.categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void removeById(String id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    public void editCategory(CategoryBindingModel categoryBindingModel, String id) {

        Category category = this.categoryRepository.findById(id).orElse(null);
        category.setName(categoryBindingModel.getName());
        this.categoryRepository.save(category);

    }
}

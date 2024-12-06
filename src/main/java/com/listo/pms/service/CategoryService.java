package com.listo.pms.service;

import com.listo.pms.dto.CategoryBody;
import com.listo.pms.model.Category;
import com.listo.pms.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category addCategory(CategoryBody categoryBody){
        String name = categoryBody.getName();
        Category newCategory = new Category();
        newCategory.setName(name);
        categoryRepository.save(newCategory);

        return newCategory;
    }

    public Optional<Category> findById(int id){
        return categoryRepository.findById(id);
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategory(int id){
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category updateCategory(int id, CategoryBody categoryBody){
        Category oldCategory = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        oldCategory.setName(categoryBody.getName());
        return categoryRepository.save(oldCategory);
    }

    public void deleteCategory(int id){
        categoryRepository.deleteById(id);
    }
}

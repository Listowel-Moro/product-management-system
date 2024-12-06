package com.listo.pms.controller;

import com.listo.pms.dto.CategoryBody;
import com.listo.pms.model.Category;
import com.listo.pms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @PostMapping
    public Category postCategory(@RequestBody CategoryBody categoryBody){
        return categoryService.addCategory(categoryBody);
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable int id){
        return categoryService.getCategory(id);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody CategoryBody categoryBody){
        return categoryService.updateCategory(id, categoryBody);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
    }
}

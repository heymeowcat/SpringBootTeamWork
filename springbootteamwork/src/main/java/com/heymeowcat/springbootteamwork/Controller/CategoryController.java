package com.heymeowcat.springbootteamwork.Controller;

import java.util.List;

import com.heymeowcat.springbootteamwork.Service.CategoryService;
import com.heymeowcat.springbootteamwork.models.Categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/Categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/getAllCategories")
    public List<Categories> getAllUsers() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/addCategories")
    public List<Categories> addUsers(@RequestBody List<Categories> cList) {
        return categoryService.addCategories(cList);
    }

    @PostMapping("/addCategory")
    public Categories addCategory(@RequestBody Categories category) {
        return categoryService.addCategory(category);
    }


    @PutMapping("/updateCategory")
    public Categories updateCategory(@RequestBody Categories category) {
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("{id}")
    public String deleteCategory(@PathVariable int id) {
        return categoryService.deleteCategory(id);
    }

    @GetMapping("{id}")
    public Categories getCategory(@PathVariable int id) {
        return categoryService.getCategoryByid(id);
    }


}

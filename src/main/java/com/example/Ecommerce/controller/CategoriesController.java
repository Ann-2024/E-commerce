package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Categories.Categories;
import com.example.Ecommerce.Model.Users.Addresses.Addresses;
import com.example.Ecommerce.Model.Users.Users;
import com.example.Ecommerce.Service.AddressService;
import com.example.Ecommerce.Service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping(path = "/api/categories")
public class  CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping
    public List<Categories> getCategories() {
        return categoriesService.getCategories();
    }

    @PostMapping("/add")
    public void registerNewCategories(@RequestBody Categories categories) {
        categoriesService.addNewCategories(categories);
    }

    @DeleteMapping(path = "{categoriesId}")
    public void deleteCategories(@PathVariable("categoriesId") Long addressId) {
        categoriesService.deleteCategories(addressId);
    }

    @PutMapping(path = "{categoriesId}")
    public void updateCategories(@RequestBody Categories categories, @PathVariable("categoriesId") Long categoriesId) {
        categoriesService.updateCategories(categoriesId, categories);
    }
}

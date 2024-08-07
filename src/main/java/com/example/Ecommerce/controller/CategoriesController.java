package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Categories.Categories;
import com.example.Ecommerce.Model.Seller.BankDetails_seller.BankDetails;
import com.example.Ecommerce.Service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/categories")
public class  CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/getall")
    public List<Categories> getCategories() {
        return categoriesService.getCategories();
    }
    @GetMapping("/getall/{id}")
    public Optional<Categories> getCategoriesBYId(@PathVariable(value = "id") Long categoriesId) {
        return categoriesService.getCategoriesBYId(categoriesId);
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

package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Categories.sub_categories.SubCategories;
import com.example.Ecommerce.Model.Users.Addresses.Addresses;
import com.example.Ecommerce.Service.AddressService;
import com.example.Ecommerce.Service.SubCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/subCategories")
public class  SubCategoriesController {
    @Autowired
    private SubCategoriesService subCategoriesService;

    @GetMapping
    public List<SubCategories> getSubCategories() {
        return subCategoriesService.getSubCategories();
    }

    @PostMapping("/add")
    public void registerNewSubCategories(@RequestParam(name ="id") @PathVariable Long id, @RequestBody SubCategories  subCategories) {
        subCategoriesService.addNewSubCategories(id,subCategories);
    }

    @DeleteMapping(path = "{subCategoriesId}")
    public void deleteSubCategories(@PathVariable("subCategoriesId") Long subCategoriesId) {
        subCategoriesService.deleteSubCategories(subCategoriesId);
    }

    @PutMapping(path = "{subCategoriesId}")
    public void updateSubCategories(@RequestBody SubCategories subCategories, @PathVariable("subCategoriesId") Long subCategoriesId) {
        subCategoriesService.updateSubCategories(subCategoriesId, subCategories);
    }
}

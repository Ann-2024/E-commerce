package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Categories.sub_categories.SubCategories;
import com.example.Ecommerce.Model.Seller.BankDetails_seller.BankDetails;
import com.example.Ecommerce.Service.SubCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/subCategories")
@CrossOrigin(value="*")

public class  SubCategoriesController {
    @Autowired
    private SubCategoriesService subCategoriesService;

    @GetMapping("/getall")
    public List<SubCategories> getSubCategories() {
        return subCategoriesService.getSubCategories();
    }
    @GetMapping("/getall/{id}")
    public Optional<SubCategories> getSubCategoriesBYId(@PathVariable(value = "id") Long subCategoriesId) {
        return subCategoriesService.getSubCategoriesBYId(subCategoriesId);
    }
    @PostMapping("/add")
    public void registerNewSubCategories(@RequestParam(name = "id") Long id, @RequestBody List<SubCategories> subCategories) {
        subCategoriesService.addNewSubCategories(id, subCategories);
    }


    @DeleteMapping(path = "{subCategoriesId}")
    public void deleteSubCategories(@PathVariable("subCategoriesId") Long subCategoriesId) {
        subCategoriesService.deleteSubCategories(subCategoriesId);
    }

    @PutMapping()
    public void updateSubCategories(@RequestParam(name="subCategoriesId") Long subCategoriesId,@RequestParam(name="id") Long id
                                ,@RequestBody SubCategories subCategories) {
        subCategoriesService.updateSubCategories(subCategoriesId,id, subCategories);
    }
}

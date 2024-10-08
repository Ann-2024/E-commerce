package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Categories.sub_categories.SubCategories;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Seller.BankDetails_seller.BankDetails;
import com.example.Ecommerce.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/products")
@CrossOrigin(value="*")

public class  ProductsController {
    @Autowired
    private   ProductsService productsService;

    @GetMapping("/getall")
    public List<Products> getProducts() {
        return productsService.getProducts();
    }
    @GetMapping("/getall/{id}")
    public Optional<Products> getProductsBYId(@PathVariable(value = "id") Long productsId) {
        return productsService.getProductsBYId(productsId);
    }

    @PostMapping("/add")
    public void registerNewProducts(
                                    @RequestParam(name = "sellerId") Long sellerId,
                                    @RequestParam(name ="subcategoriesId") Long subCategoriesId,
                                    @RequestBody List<Products> productsList) {
        productsService.addNewProducts(subCategoriesId, sellerId, productsList);
    }

    @DeleteMapping(path = "{productsId}")
    public void deleteProducts(@PathVariable("productsId") Long productsId) {
        productsService.deleteProducts(productsId);
    }

    @PutMapping()
    public void updateProducts(@RequestBody Products products, @RequestParam("productsId") Long productsId
                                ,@RequestParam("id") Long id) {
        productsService.updateProducts(productsId,id, products);
    }
}
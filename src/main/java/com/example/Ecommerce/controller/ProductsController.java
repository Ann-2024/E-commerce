package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Users.Addresses.Addresses;
import com.example.Ecommerce.Service.AddressService;
import com.example.Ecommerce.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/products")
public class  ProductsController {
    @Autowired
    private   ProductsService productsService;

    @GetMapping
    public List<Products> getProducts() {
        return productsService.getProducts();
    }

    @PostMapping("/add")
    public void registerNewProducts(@RequestParam(name ="id") @PathVariable Long id, @RequestBody Products products) {
        productsService.addNewProducts(id, products);
    }

    @DeleteMapping(path = "{productsId}")
    public void deleteProducts(@PathVariable("productsId") Long productsId) {
        productsService.deleteProducts(productsId);
    }

    @PutMapping(path = "{productsId}")
    public void updateProducts(@RequestBody Products products, @PathVariable("productsId") Long productsId) {
        productsService.updateProducts(productsId, products);
    }
}
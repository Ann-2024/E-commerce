package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Products.productsSkus.ProductsSkus;
import com.example.Ecommerce.Model.Users.Addresses.Addresses;
import com.example.Ecommerce.Service.AddressService;
import com.example.Ecommerce.Service.ProductsSkusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/productsSkus")
public class  ProductsSkusController {
    @Autowired
    private ProductsSkusService productsSkusService;

    @GetMapping
    public List<ProductsSkus> getProductsSkus() {
        return productsSkusService.getProductsSkus();
    }

    @PostMapping("/add")
    public void registerNewProductsSkus(@RequestParam(name ="id") @PathVariable Long id, @RequestBody ProductsSkus productsSkus) {
        productsSkusService.addNewProductsSkus(id,productsSkus);
    }

    @DeleteMapping(path = "{productsSkusId}")
    public void deleteProductsSkus(@PathVariable("productsSkusId") Long productsSkusId) {
        productsSkusService.deleteProductsSkus(productsSkusId);
    }

    @PutMapping(path = "{productsSkusId}")
    public void updateProductsSkus(@RequestBody ProductsSkus productsSkus, @PathVariable("productsSkusId") Long productsSkusId) {
        productsSkusService.updateProductsSkus(productsSkusId, productsSkus);
    }
}

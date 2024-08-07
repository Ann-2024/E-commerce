package com.example.Ecommerce.controller;


import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Products.ProductsAttributes.ProductsAttributes;
import com.example.Ecommerce.Service.ProductsAttributesService;
import com.example.Ecommerce.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/productsAttributes")
public class  ProductsAttributeController {
    @Autowired
    private ProductsAttributesService productsAttributesService;

    @GetMapping
    public List<ProductsAttributes> getProductsAttributes() {
        return productsAttributesService.getProductsAttributes();
    }

    @PostMapping("/add")
    public void registerNewProductAttributes(@RequestParam(name ="id") @PathVariable Long id, @RequestBody ProductsAttributes  productsAttributes) {
        productsAttributesService.addNewProductsAttributes( id,productsAttributes);
    }

    @DeleteMapping(path = "{productsAttributesId}")
    public void deleteProducts(@PathVariable("productsAttributesId") Long productsAttributesId) {
        productsAttributesService.deleteProductsAttributes(productsAttributesId);
    }

    @PutMapping(path = "{productsAttributesId}")
    public void updateProductsAttributes(@RequestBody ProductsAttributes productsAttributes, @PathVariable("productsAttributesId") Long productsAttributesId) {
        productsAttributesService.updateProductsAttributes(productsAttributesId, productsAttributes);
    }
}

package com.example.Ecommerce.controller;


import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Products.ProductsAttributes.ProductsAttributes;
import com.example.Ecommerce.Model.Seller.BankDetails_seller.BankDetails;
import com.example.Ecommerce.Service.ProductsAttributesService;
import com.example.Ecommerce.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/productsAttributes")
@CrossOrigin(value="*")
public class  ProductsAttributeController {
    @Autowired
    private ProductsAttributesService productsAttributesService;

    @GetMapping("/getall")
    public List<ProductsAttributes> getProductsAttributes() {
        return productsAttributesService.getProductsAttributes();
    }
    @GetMapping("/getall/{id}")
    public Optional<ProductsAttributes> getProductsAttributesBYId(@PathVariable(value = "id") Long productsAttributesId) {
        return productsAttributesService.getProductsAttributesBYId(productsAttributesId);
    }

    @PostMapping("/add")
    public void registerNewProductAttributes(@RequestBody List<ProductsAttributes> productsAttributesList) {
        productsAttributesService.addNewProductsAttributes(productsAttributesList);
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

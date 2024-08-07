package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Categories.sub_categories.SubCategories;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.repository.ProductsRepository;
import com.example.Ecommerce.repository.SubCategoriesRepository;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private SubCategoriesRepository subCategoriesRepository;

    public List<Products> getProducts() {
        return productsRepository.findAll();
    }

    public void addNewProducts(Long id, Products products) {
        SubCategories subCategories = subCategoriesRepository.findById(id).get();
        System.out.println("product service");

        if (subCategories.equals(null)) {
            throw new RuntimeException("user not found");
        } else {
            products.setCreatedAt(LocalDateTime.now());
            products.setDeletedAt(LocalDateTime.now());
            products.setSubCategories( subCategories);
            productsRepository.save(products);
        }
    }

    public void deleteProducts(Long productsId) {
        boolean exists = productsRepository.existsById(productsId);
        if (!exists) {
            throw new IllegalStateException("Product with id " + productsId + " does not exist");
        }
        productsRepository.deleteById(productsId);
    }

    public void updateProducts(Long productsId, Products updatedProducts) {
        Products existingProducts = productsRepository.findById(productsId)
                .orElseThrow(() -> new IllegalStateException("Product with id " + productsId + " does not exist"));
        String name = updatedProducts.getName();
        String description = updatedProducts.getDescription();
        String summary = updatedProducts.getSummary();
        String cover = updatedProducts.getCover();
        String sku = updatedProducts.getSku();
        String price = updatedProducts.getPrice();

        existingProducts.setName(name);
        existingProducts.setDescription(description);
        existingProducts.setSummary(summary);
        existingProducts.setCover(cover);
        existingProducts.setSku(sku);
        existingProducts.setPrice(price);
        existingProducts.setCreatedAt(LocalDateTime.now());
        existingProducts.setDeletedAt(LocalDateTime.now());

        productsRepository.save(existingProducts);
    }
}


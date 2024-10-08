package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Categories.sub_categories.SubCategories;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Seller.Seller;
import com.example.Ecommerce.repository.ProductsRepository;
import com.example.Ecommerce.repository.SellerRepository;
import com.example.Ecommerce.repository.SubCategoriesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional

public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SubCategoriesRepository subCategoriesRepository;

    public List<Products> getProducts() {
        return productsRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
    public Optional<Products> getProductsBYId(Long productsId) {

        return productsRepository.findById(productsId);
    }


    public void addNewProducts(Long subCategoriesId, Long sellerId, List<Products> productsList) {

        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
        SubCategories subCategoriess = subCategoriesRepository.findById(subCategoriesId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        System.out.println("Adding products");

        for (Products products : productsList) {

            products.setCreatedAt(LocalDateTime.now());
            products.setDeletedAt(LocalDateTime.now());
            products.setSubCategories(subCategoriess);
            products.setSeller(seller);
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

    public void updateProducts(Long productsId,Long id, Products updatedProducts) {

        Products existingProducts = productsRepository.findById(productsId)
                .orElseThrow(() -> new IllegalStateException("Product with id " + productsId + " does not exist"));

        SubCategories subCategories1 = subCategoriesRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("category with the given id is not given" +id));


        String name = updatedProducts.getName();
        String description = updatedProducts.getDescription();
        String summary = updatedProducts.getSummary();
        String cover = updatedProducts.getCover();
        String sku = updatedProducts.getSku();
        BigDecimal price = updatedProducts.getPrice();

        existingProducts.setName(name);
        existingProducts.setDescription(description);
        existingProducts.setSummary(summary);
        existingProducts.setCover(cover);
        existingProducts.setSku(sku);
        existingProducts.setPrice(price);
        existingProducts.setSubCategories(subCategories1);
        existingProducts.setCreatedAt(LocalDateTime.now());
        existingProducts.setDeletedAt(LocalDateTime.now());

        productsRepository.save(existingProducts);
    }
}


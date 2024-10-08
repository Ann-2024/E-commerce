package com.example.Ecommerce.repository;

import com.example.Ecommerce.Model.Categories.Categories;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Products.productsSkus.ProductsSkus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories(basePackages = "com.example.Ecommerce.repository.jpa")
public interface ProductsSkusRepository extends JpaRepository<ProductsSkus, Long> {
    @Query("SELECT s FROM ProductsSkus s WHERE s.id =?1 ")
    Optional<ProductsSkus> findById(String id);
}

package com.example.Ecommerce.repository;


import com.example.Ecommerce.Model.Products.ProductsAttributes.ProductsAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories(basePackages = "com.example.Ecommerce.repository.jpa")
public interface ProductsAttributesRepository extends JpaRepository<ProductsAttributes,Long> {
    @Query("SELECT s FROM ProductsAttributes s WHERE s.id =?1 ")
    Optional<ProductsAttributes> findById(String id);
}
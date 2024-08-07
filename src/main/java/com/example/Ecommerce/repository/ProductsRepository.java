package com.example.Ecommerce.repository;

import com.example.Ecommerce.Model.Categories.Categories;
import com.example.Ecommerce.Model.Products.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    @Query("SELECT s FROM Products s WHERE s.id =?1 ")
    Optional<Products> findById(String id);
}

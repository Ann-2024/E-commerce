package com.example.Ecommerce.repository;

import com.example.Ecommerce.Model.Categories.Categories;
import com.example.Ecommerce.Model.Users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories(basePackages = "com.example.Ecommerce.repository.jpa")
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    @Query("SELECT s FROM Categories s WHERE s.id =?1 ")
    Optional<Categories> findById(String id);
}

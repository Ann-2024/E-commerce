package com.example.Ecommerce.repository;

import com.example.Ecommerce.Model.Categories.Categories;
import com.example.Ecommerce.Model.Categories.sub_categories.SubCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubCategoriesRepository extends JpaRepository<SubCategories, Long> {
    @Query("SELECT s FROM SubCategories s WHERE s.id =?1 ")
    Optional<SubCategories> findById(String id);
}

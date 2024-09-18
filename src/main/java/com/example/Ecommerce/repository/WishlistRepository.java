package com.example.Ecommerce.repository;

import com.example.Ecommerce.Model.Categories.sub_categories.SubCategories;
import com.example.Ecommerce.Model.wishlist.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    @Query("SELECT s FROM Wishlist s WHERE s.id =?1 ")
    Optional<Wishlist> findById(String id);
}

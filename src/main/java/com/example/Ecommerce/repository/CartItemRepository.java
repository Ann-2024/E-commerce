package com.example.Ecommerce.repository;

import com.example.Ecommerce.Model.Cart.Cart;
import com.example.Ecommerce.Model.Cart.CartItem.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    @Query("SELECT s FROM CartItem s WHERE s.id =?1 ")
    Optional<CartItem> findById(String id);
}
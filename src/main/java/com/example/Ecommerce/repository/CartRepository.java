package com.example.Ecommerce.repository;

import com.example.Ecommerce.Model.Cart.Cart;
import com.example.Ecommerce.Model.Seller.BankDetails_seller.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query("SELECT s FROM Cart s WHERE s.id =?1 ")
    Optional<Cart> findById(String id);
}

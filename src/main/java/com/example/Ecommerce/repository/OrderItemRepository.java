package com.example.Ecommerce.repository;

import com.example.Ecommerce.Model.Addresses.Addresses;
import com.example.Ecommerce.Model.Order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    @Query("SELECT s FROM OrderItem  s WHERE s.id =?1 ")
    Optional<OrderItem> findById(String id);
}

package com.example.Ecommerce.repository;

import com.example.Ecommerce.Model.Addresses.Addresses;
import com.example.Ecommerce.Model.Order.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Long> {
    @Query("SELECT s FROM OrderDetails  s WHERE s.id =?1 ")
    Optional<OrderDetails> findById(String id);
}

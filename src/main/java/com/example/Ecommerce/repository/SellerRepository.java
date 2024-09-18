package com.example.Ecommerce.repository;


import com.example.Ecommerce.Model.Seller.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories(basePackages = "com.example.Ecommerce.repository.jpa")
public interface SellerRepository extends JpaRepository<Seller,Long> {

    @Query("SELECT s FROM Users s WHERE s.email =?1 ")
    Optional<Seller> findByEmail(String email);
}
package com.example.Ecommerce.repository;

import com.example.Ecommerce.Model.Addresses.Addresses;
import com.example.Ecommerce.Model.Order.PaymentDetails.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories(basePackages = "com.example.Ecommerce.repository.jpa")
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails,Long> {
    @Query("SELECT s FROM PaymentDetails  s WHERE s.id =?1 ")
    Optional<PaymentDetails> findById(Long Id);
}
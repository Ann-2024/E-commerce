package com.example.Ecommerce.repository;

import com.example.Ecommerce.Model.Seller.BankDetails_seller.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BankDetailsRepository extends JpaRepository<BankDetails,Long> {
    @Query("SELECT s FROM BankDetails s WHERE s.id =?1 ")
    Optional<BankDetails> findById(String id);
}

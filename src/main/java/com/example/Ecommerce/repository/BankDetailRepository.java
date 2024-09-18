package com.example.Ecommerce.repository;

import com.example.Ecommerce.Model.Seller.BankDetails_seller.BankDetails;
import com.example.Ecommerce.Model.Users.BankDetail.BankDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
@EnableJpaRepositories(basePackages = "com.example.Ecommerce.repository.jpa")
public interface BankDetailRepository extends JpaRepository<BankDetail,Long> {
    @Query("SELECT s FROM BankDetail s WHERE s.id =?1 ")
    Optional<BankDetail> findById(String id);
}

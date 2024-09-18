package com.example.Ecommerce.repository;

import com.example.Ecommerce.Model.Addresses.Addresses;
import com.example.Ecommerce.Model.Seller.BankDetails_seller.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
@EnableJpaRepositories(basePackages = "com.example.Ecommerce.repository.jpa")
public interface AddressRepository extends JpaRepository<Addresses,Long> {
    @Query("SELECT s FROM Addresses  s WHERE s.id =?1 ")
    Optional<Addresses> findById(String id);

}

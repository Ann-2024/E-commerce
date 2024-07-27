package com.example.Ecommerce.repository;

import com.example.Ecommerce.Model.Addresses.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Addresses,Long> {
}

package com.example.Ecommerce.repository;

import com.example.Ecommerce.Model.Addresses.ProductPincodes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductPincodesRepository extends MongoRepository<ProductPincodes, String> {
    ProductPincodes findByProductId(Long productId);
}
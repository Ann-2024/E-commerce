package com.example.Ecommerce.repository;

import com.example.Ecommerce.Model.Addresses.Pincode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@EnableMongoRepositories(basePackages = "com.example.Ecommerce.repository")

public interface PincodeRepository extends MongoRepository<Pincode,String> {

    List<Pincode> findByPincodeIn(List<String> pincodes);

    List<Pincode> findByPincode(String pincode);
}
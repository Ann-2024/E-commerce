package com.example.Ecommerce.user.repository;

import com.example.Ecommerce.Addresses.Addresses;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Addresses,Long> {
}

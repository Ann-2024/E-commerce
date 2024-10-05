package com.example.Ecommerce.repository;

import com.example.Ecommerce.Model.razorpay.PaymentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDetail,Long> {
    Optional<PaymentDetail> findById(Long Id);


}

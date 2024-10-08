package com.example.Ecommerce.repository;

import com.example.Ecommerce.config.razorpay.PaymentStatus;
import com.example.Ecommerce.Model.razorpay.PaymentLinkRequestDto;
import org.springframework.stereotype.Component;

@Component
public interface PaymentGateway {
    String createPaymentLink(PaymentLinkRequestDto paymentLinkRequestDto);
    PaymentStatus getStatus(String paymentId, String orderId);
}
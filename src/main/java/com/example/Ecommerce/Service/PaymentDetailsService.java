package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Order.OrderDetails;
import com.example.Ecommerce.Model.Order.OrderItem;
import com.example.Ecommerce.Model.Order.PaymentDetails.PaymentDetails;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Products.productsSkus.ProductsSkus;
import com.example.Ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentDetailsService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;
    public List<PaymentDetails> getPaymentDetails() {
        return paymentDetailsRepository.findAll();
    }
    public Optional<PaymentDetails> getPaymentDetailsBYId(Long paymentDetailsId) {

        return paymentDetailsRepository.findById(paymentDetailsId);
    }

    public void addNewPaymentDetails( Long orderItemId, PaymentDetails paymentDetails) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).get();

        System.out.println("Wishlist service");

        paymentDetails.setCreatedAt(LocalDateTime.now());
        paymentDetails.setDeletedAt(LocalDateTime.now());
        paymentDetails.setOrderItem(orderItem);


        paymentDetailsRepository.save(paymentDetails);

    }
    public void deletePaymentDetails(Long paymentDetailsId) {
        boolean exists = paymentDetailsRepository.existsById(paymentDetailsId);
        if (!exists) {
            throw new IllegalStateException("Wishlist with id " + paymentDetailsId + " does not exist");
        }
        paymentDetailsRepository.deleteById(paymentDetailsId);
    }

    public void updatePaymentDetails(Long paymentDetailsId, PaymentDetails updatedPaymentDetails) {
        PaymentDetails existingPaymentDetails= paymentDetailsRepository.findById(paymentDetailsId)
                .orElseThrow(() -> new IllegalStateException("Product with id " + paymentDetailsId + " does not exist"));

        String amount = updatedPaymentDetails.getAmount();
        String provider = updatedPaymentDetails.getProvider();
        String status = updatedPaymentDetails.getStatus();

        existingPaymentDetails.setAmount(amount);
        existingPaymentDetails.setProvider(provider);
        existingPaymentDetails.setStatus(status);
        existingPaymentDetails.setCreatedAt(LocalDateTime.now());
        existingPaymentDetails.setDeletedAt(LocalDateTime.now());

        paymentDetailsRepository.save(existingPaymentDetails);
    }
}

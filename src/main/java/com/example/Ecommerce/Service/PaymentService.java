package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Order.OrderDetails;
import com.example.Ecommerce.Model.Order.PaymentDetails.PaymentDetails;
import com.example.Ecommerce.Model.razorpay.PaymentDetail;
import com.example.Ecommerce.Model.razorpay.PaymentLinkRequestDto;
import com.example.Ecommerce.config.razorpay.PaymentStatus;
import com.example.Ecommerce.repository.OrderDetailsRepository;
import com.example.Ecommerce.repository.PaymentDetailsRepository;
import com.example.Ecommerce.repository.PaymentGateway;
import com.example.Ecommerce.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentGateway paymentGateway;
    private PaymentRepository paymentRepository;
    private final PaymentDetailsRepository paymentDetailsRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public PaymentService(PaymentGateway paymentGateway, PaymentDetailsRepository paymentDetailsRepository, OrderDetailsRepository orderDetailsRepository) {
        this.paymentGateway = paymentGateway;
        this.paymentDetailsRepository = paymentDetailsRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public String createLink(String orderId, PaymentDetail paymentDetail) {
        // Retrieve order details to populate the payment request DTO
        OrderDetails orderDetails = orderDetailsRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        PaymentLinkRequestDto paymentLinkRequestDto = new PaymentLinkRequestDto();

        paymentLinkRequestDto.setOrderId(orderId);

        paymentLinkRequestDto.setTotal(Integer.parseInt(orderDetails.getTotal()));

        // Generate payment link using the payment gateway
        String paymentLink = paymentGateway.createPaymentLink(paymentLinkRequestDto);

        // Save payment details in the repository

        paymentDetail.setPaymentLink(paymentLink);
//        paymentResponse.setOrderId(orderId);
        paymentRepository.save(paymentDetail);

        return paymentLink;
    }

    public PaymentStatus getPaymentStatus(Long paymentId, String orderId, PaymentDetail paymentDetail) {
//        Users users = usersRepository.findById(usersId).get();
//        System.out.println("Wishlist service");
//
//        bankDetail.setUsers(users);
//        bankDetailRepository.save(bankDetail);
        // Retrieve payment details by payment ID
        PaymentDetails paymentDetails = paymentDetailsRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        // Get payment status from the payment gateway
        PaymentStatus status = paymentGateway.getStatus(String.valueOf(paymentId), orderId);

        // Update and save payment details with the new status
        paymentDetail.setStatus(status);
         // Assuming paymentId might not have been set previously
        paymentRepository.save(paymentDetail);

        return status;
    }
}

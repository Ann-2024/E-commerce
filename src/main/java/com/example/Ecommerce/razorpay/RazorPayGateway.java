package com.example.Ecommerce.razorpay;

import com.example.Ecommerce.Model.Order.PaymentDetails.PaymentDetails;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class RazorPayGateway implements PaymentGateway {

    private final RazorpayClient razorpayClient;

    @Autowired
    public RazorPayGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String createPaymentLink(PaymentLinkRequestDto paymentLinkRequestDto) {
        // Creating a JSON request for generating the payment link
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount", paymentLinkRequestDto.getTotal());
        paymentLinkRequest.put("currency", "INR");
        paymentLinkRequest.put("expire_by", LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toEpochSecond());
        paymentLinkRequest.put("reference_id", paymentLinkRequestDto.getOrderId());
        paymentLinkRequest.put("description", "Payment for order no " + paymentLinkRequestDto.getOrderId());

        // Adding customer details
        JSONObject customer = new JSONObject();
        customer.put("name", paymentLinkRequestDto.getCustomerName());
        customer.put("contact", paymentLinkRequestDto.getPhone());
        customer.put("email", "geekysanjay@gmail.com");
        paymentLinkRequest.put("customer", customer);

        // Adding additional notes
        JSONObject notes = new JSONObject();
        notes.put("policy_name", "Jeevan Bima");
        paymentLinkRequest.put("notes", notes);


        try {
            // Creating the payment link using Razorpay Orders API or correct module
            JSONObject paymentResponse = razorpayClient.Orders.create(paymentLinkRequest).toJson(); // or razorpayClient.payments.create(paymentLinkRequest)
            return paymentResponse.getString("short_url");
        } catch (RazorpayException e) {
            throw new RuntimeException("Failed to create payment link", e);
        }

    }

    @Override
    public PaymentStatus getStatus(String paymentId, String orderId) {
        try {
            // Fetching payment details by payment ID

            Payment paymentDetails = razorpayClient.Payments.fetch(paymentId);
            String statusType = paymentDetails.get("status");

            // Mapping Razorpay payment status to application-specific payment status
            return switch (statusType) {
                case "captured" -> PaymentStatus.SUCCESS;
                case "failed" -> PaymentStatus.FAILURE;
                default -> PaymentStatus.INITIATED;
            };
        } catch (RazorpayException e) {
            throw new RuntimeException("Unable to fetch the payment details", e);
        }
    }
}

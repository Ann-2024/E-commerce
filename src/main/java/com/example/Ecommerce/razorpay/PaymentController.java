package com.example.Ecommerce.razorpay;


import com.example.Ecommerce.Model.Order.PaymentDetails.PaymentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping(path = "/api/payment")
@CrossOrigin(value="*")
@RestController
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/createLink")
    public String createPaymentLink(@RequestParam String orderId, PaymentDetail paymentDetail){
        return paymentService.createLink(orderId,paymentDetail);
    }

    @GetMapping("/getPaymentStatus")
    public PaymentStatus getPaymentStatus(@RequestParam("paymentId") Long paymentId, @RequestParam("orderId") String orderId, PaymentDetail paymentDetail){
        return paymentService.getPaymentStatus(paymentId, orderId, paymentDetail);
    }
}
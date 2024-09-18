package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Order.OrderDetails;
import com.example.Ecommerce.Model.Order.PaymentDetails.PaymentDetails;
import com.example.Ecommerce.Service.OrderDetailsService;
import com.example.Ecommerce.Service.PaymentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/paymentDetails")
@CrossOrigin(value="*")
public class PaymentDetailsController {
    @Autowired
    private PaymentDetailsService paymentDetailsService;

    @GetMapping("/getall")
    public List<PaymentDetails> getPaymentDetails() {
        return paymentDetailsService.getPaymentDetails();
    }
    @GetMapping("/getall/{id}")
    public Optional<PaymentDetails> getPaymentDetailsBYId(@PathVariable(value = "id") Long paymentDetailsId) {
        return paymentDetailsService.getPaymentDetailsBYId(paymentDetailsId);
    }

    @PostMapping("/add")
    public void registerNewPaymentDetails(@RequestParam(name ="orderDetailsId")@PathVariable Long orderDetailsId, @RequestBody PaymentDetails paymentDetails) {
        paymentDetailsService.addNewPaymentDetails(orderDetailsId, paymentDetails);
    }

    @DeleteMapping(path = "{paymentDetailsId}")
    public void deletePaymentDetails(@PathVariable("paymentDetailsId") Long paymentDetailsId) {
        paymentDetailsService.deletePaymentDetails(paymentDetailsId);
    }

    @PutMapping(path = "{paymentDetailsId}")
    public void updatePaymentDetails(@RequestBody PaymentDetails paymentDetails, @PathVariable("paymentDetailsId") Long paymentDetailsId) {
        paymentDetailsService.updatePaymentDetails(paymentDetailsId, paymentDetails);
    }
}


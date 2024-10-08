package com.example.Ecommerce.Model.razorpay;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PaymentLinkRequestDto {
    private String orderId;
    private String customerName;
    private String phone;
    private int total;
}
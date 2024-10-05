package com.example.Ecommerce.Model.razorpay;

import com.example.Ecommerce.Model.Order.OrderDetails;
import com.example.Ecommerce.Model.Order.PaymentDetails.PaymentDetails;
import com.example.Ecommerce.config.razorpay.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "paymentDetail")
public class PaymentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private OrderDetails orderDetails;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "paymentId", nullable = false)
    private PaymentDetails paymentDetails;
    private String PaymentLink;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}

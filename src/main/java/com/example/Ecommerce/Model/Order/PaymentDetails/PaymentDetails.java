package com.example.Ecommerce.Model.Order.PaymentDetails;


import com.example.Ecommerce.Model.Order.OrderItem;
import com.example.Ecommerce.Model.razorpay.PaymentDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "paymentDetails")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_Id", nullable = false)
    private OrderItem orderItem;

    private String PaymentLink;
    @Enumerated(EnumType.STRING)
    @OneToMany(mappedBy = "paymentDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PaymentDetail> paymentDetail;

    private String amount;
    private String provider;
    private String status;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;


}




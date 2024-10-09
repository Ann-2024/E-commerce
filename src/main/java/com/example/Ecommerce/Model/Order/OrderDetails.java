package com.example.Ecommerce.Model.Order;


import com.example.Ecommerce.Model.Users.Users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orderDetails")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_Id", nullable = false)
    private Users users;

    @OneToMany(mappedBy = "orderDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItem;





    private String total;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;


}




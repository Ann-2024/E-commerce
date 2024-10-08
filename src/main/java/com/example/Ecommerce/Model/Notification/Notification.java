package com.example.Ecommerce.Model.Notification;

import com.example.Ecommerce.Model.Cart.Cart;
import com.example.Ecommerce.Model.Categories.sub_categories.SubCategories;
import com.example.Ecommerce.Model.Order.OrderDetails;
import com.example.Ecommerce.Model.Order.OrderItem;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Products.productsSkus.ProductsSkus;
import com.example.Ecommerce.Model.Seller.Seller;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "notification")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "sellerId", nullable = false)
    private Seller seller;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "orderId", nullable = false)
    private OrderItem orderItem;

    private String currentStatus;
    private String message;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;


}




package com.example.Ecommerce.Model.Order;


import com.example.Ecommerce.Model.Cart.Cart;
import com.example.Ecommerce.Model.Notification.Notification;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Products.productsSkus.ProductsSkus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orderItem")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notification> notification;
    @ManyToOne
    @JsonIgnoreProperties
    @JoinColumn(name = "order_Id", nullable = false)
    private OrderDetails orderDetails;
    @ManyToOne
    @JsonIgnoreProperties
    @JoinColumn(name = "product_Id", nullable = false)
    private Products products;
    @ManyToOne
    @JsonIgnoreProperties
    @JoinColumn(name = "products_sku_Id", nullable = false)
    private ProductsSkus productsSkus;




    private String quantity;
    private String status;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;


}



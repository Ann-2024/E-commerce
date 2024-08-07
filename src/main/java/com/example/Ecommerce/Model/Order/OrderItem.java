package com.example.Ecommerce.Model.Order;


import com.example.Ecommerce.Model.Cart.Cart;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Products.productsSkus.ProductsSkus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_Id", nullable = false)
    private OrderDetails orderDetails;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_Id", nullable = false)
    private Products products;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "products_sku_Id", nullable = false)
    private ProductsSkus productsSkus;




    private String quantity;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;


}



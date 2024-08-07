package com.example.Ecommerce.Model.Products.productsSkus;

import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Products.ProductsAttributes.ProductsAttributes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products_skus")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsSkus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "products_id", nullable = false)
    private Products products;

    private String sizeAttributeId;
    private String colorAttributeId;

    @OneToMany(mappedBy = "productsSkus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductsAttributes> productsAttributes;

    private String sku;
    private String price;
    private String quantity;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}

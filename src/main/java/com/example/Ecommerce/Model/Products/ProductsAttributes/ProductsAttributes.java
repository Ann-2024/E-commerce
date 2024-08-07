package com.example.Ecommerce.Model.Products.ProductsAttributes;

import com.example.Ecommerce.Model.Products.productsSkus.ProductsSkus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "products_attributes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsAttributes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "productsSkus_id", nullable = false)
    private ProductsSkus productsSkus;

    private String value;

    private Date createdAt;
    private Date deletedAt;
}

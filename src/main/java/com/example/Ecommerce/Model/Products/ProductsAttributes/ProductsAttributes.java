package com.example.Ecommerce.Model.Products.ProductsAttributes;

import com.example.Ecommerce.Model.Products.productsSkus.ProductsSkus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "productsAttributes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductsSkus> productsSkus;

    private String value;

    private Date createdAt;
    private Date deletedAt;
}

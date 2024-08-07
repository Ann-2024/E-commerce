package com.example.Ecommerce.Model.Products;

import com.example.Ecommerce.Model.Categories.sub_categories.SubCategories;
import com.example.Ecommerce.Model.Products.productsSkus.ProductsSkus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductsSkus> productsSkus;
    @ManyToOne
    @JoinColumn(name = "subcategories_id", nullable = false)
    private SubCategories subCategories;

//    @ManyToMany
//    @JoinTable(
//            joinColumns = @JoinColumn(name = "subcategories_id"))
//    private Set<SubCategories> subCategories;

    private String name;
    private String description;
    private String summary;
    private String cover;
    private String sku;
    private String price;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;


}

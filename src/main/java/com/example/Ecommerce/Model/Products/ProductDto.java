package com.example.Ecommerce.Model.Products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private  Long id;
    private String name;
    private String description;
    private String summary;
    private String cover;
    private String sku;
    private Long subCategoriesId;

    private BigDecimal price;


}

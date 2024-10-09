package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Categories.sub_categories.SubCategories;
import com.example.Ecommerce.Model.Products.ProductDto;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Seller.Seller;
import com.example.Ecommerce.repository.ProductsRepository;
import com.example.Ecommerce.repository.SellerRepository;
import com.example.Ecommerce.repository.SubCategoriesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional

public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SubCategoriesRepository subCategoriesRepository;

    public List<Products> getProducts() {
        return productsRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Optional<Products> getProductsBYId(Long productsId) {

        return productsRepository.findById(productsId);
    }


    public void addNewProducts(Long sellerId, List<ProductDto> productsList) {

        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));


        // Assuming subCategoriesId is a field inside each Products object, not a separate list
        for (ProductDto products : productsList) {
            // Ensure subCategoriesId is available in each product
            Products product1 = new Products();
            // Retrieve the subcategory using the ID from the product
            SubCategories subCategories = subCategoriesRepository.findById(products.getSubCategoriesId())
                    .orElseThrow(() -> new RuntimeException("Subcategory not found"));


            // Set necessary fields in each product
            product1.setCreatedAt(LocalDateTime.now());
            product1.setDeletedAt(LocalDateTime.now());
            product1.setSubCategories(subCategories); // Associate subcategory with product
            product1.setSeller(seller); // Associate seller with product
            product1.setName(products.getName());
            product1.setSku(products.getSku());
            product1.setPrice(products.getPrice());
            product1.setDescription(products.getDescription());
            product1.setCover(products.getCover());
            product1.setSummary(products.getSummary());


            // Save the product to the repository
            productsRepository.save(product1);
        }
    }


    public void deleteProducts(Long productsId) {
        boolean exists = productsRepository.existsById(productsId);
        if (!exists) {
            throw new IllegalStateException("Product with id " + productsId + " does not exist");
        }
        productsRepository.deleteById(productsId);
    }

    public void updateProducts(Long productsId,Long id, Products updatedProducts) {

        Products existingProducts = productsRepository.findById(productsId)
                .orElseThrow(() -> new IllegalStateException("Product with id " + productsId + " does not exist"));

        SubCategories subCategories1 = subCategoriesRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("category with the given id is not given" +id));


        String name = updatedProducts.getName();
        String description = updatedProducts.getDescription();
        String summary = updatedProducts.getSummary();
        String cover = updatedProducts.getCover();
        String sku = updatedProducts.getSku();
        BigDecimal price = updatedProducts.getPrice();

        existingProducts.setName(name);
        existingProducts.setDescription(description);
        existingProducts.setSummary(summary);
        existingProducts.setCover(cover);
        existingProducts.setSku(sku);
        existingProducts.setPrice(price);
        existingProducts.setSubCategories(subCategories1);
        existingProducts.setCreatedAt(LocalDateTime.now());
        existingProducts.setDeletedAt(LocalDateTime.now());

        productsRepository.save(existingProducts);
    }
}


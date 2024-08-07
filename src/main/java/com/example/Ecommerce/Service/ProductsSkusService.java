package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Categories.Categories;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Products.productsSkus.ProductsSkus;
import com.example.Ecommerce.repository.BankDetailsRepository;
import com.example.Ecommerce.repository.CategoriesRepository;
import com.example.Ecommerce.repository.ProductsRepository;
import com.example.Ecommerce.repository.ProductsSkusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsSkusService {

    private final ProductsSkusRepository productsSkusRepository;
    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    public ProductsSkusService(ProductsSkusRepository productsSkusRepository) {
        this.productsSkusRepository = productsSkusRepository;
    }


    public List<ProductsSkus> getProductsSkus() {
        return productsSkusRepository.findAll();
    }

    public void addNewProductsSkus(Long id,ProductsSkus productsSkus) {
        Products products = productsRepository.findById(id).get();
        if (products.equals(null)) {
            throw new RuntimeException("user not found");
        } else {
            productsSkus.setCreatedAt(LocalDateTime.now());
            productsSkus.setDeletedAt(LocalDateTime.now());
            productsSkus.setProducts(products);

            productsSkusRepository.save(productsSkus);
        }
    }

    public void deleteProductsSkus(Long productsSkusId) {
        boolean exists = productsSkusRepository.existsById(productsSkusId);
        if (!exists) {
            throw new IllegalStateException("User with id " + productsSkusId + " does not exist");
        }

        productsSkusRepository.deleteById(productsSkusId);
    }

    public void updateProductsSkus(Long productsSkusId, ProductsSkus updatedProductsSkus) {
        ProductsSkus existingProductsSkus = productsSkusRepository.findById(productsSkusId)
                .orElseThrow(() -> new IllegalStateException("User with id " + productsSkusId + " does not exist"));


        String sizeAttributeId = updatedProductsSkus.getSizeAttributeId();
        String colorAttributeId = updatedProductsSkus.getColorAttributeId();
        String sku= updatedProductsSkus.getSku();
        String price = updatedProductsSkus.getPrice();
        String quantity = updatedProductsSkus.getQuantity();

        existingProductsSkus.setSizeAttributeId(sizeAttributeId);
        existingProductsSkus.setColorAttributeId(colorAttributeId);
        existingProductsSkus.setSku(sku);
        existingProductsSkus.setPrice(price);
        existingProductsSkus.setQuantity(quantity);

        existingProductsSkus.setCreatedAt(LocalDateTime.now());
        existingProductsSkus.setDeletedAt(LocalDateTime.now());

        productsSkusRepository.save(existingProductsSkus);

    }
}

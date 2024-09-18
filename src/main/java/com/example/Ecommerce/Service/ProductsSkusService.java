package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Categories.Categories;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Products.ProductsAttributes.ProductsAttributes;
import com.example.Ecommerce.Model.Products.productsSkus.ProductsSkus;
import com.example.Ecommerce.Model.wishlist.Wishlist;
import com.example.Ecommerce.repository.*;
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
    private ProductsAttributesRepository productsAttributesRepository;

    @Autowired
    public ProductsSkusService(ProductsSkusRepository productsSkusRepository) {
        this.productsSkusRepository = productsSkusRepository;
    }


    public List<ProductsSkus> getProductsSkus() {
        return productsSkusRepository.findAll();
    }
    public Optional<ProductsSkus> getProductsSkusBYId(Long productsSkusId) {

        return productsSkusRepository.findById(productsSkusId);
    }
    public void addNewProductsSkus(Long productId,Long sizeAttributesId,Long colorAttributesId,ProductsSkus productsSkus) {
        Products products = productsRepository.findById(productId).get();
        ProductsAttributes sizeAttributes  =productsAttributesRepository.findById(sizeAttributesId).get();
        ProductsAttributes colorAttributes  =productsAttributesRepository.findById(colorAttributesId).get();


            productsSkus.setCreatedAt(LocalDateTime.now());
            productsSkus.setDeletedAt(LocalDateTime.now());
            productsSkus.setProducts(products);
            productsSkus.setSizeAttributes(sizeAttributes);
            productsSkus.setColorAttributes(colorAttributes);

            productsSkusRepository.save(productsSkus);

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



        String sku= updatedProductsSkus.getSku();
        String price = updatedProductsSkus.getPrice();
        String quantity = updatedProductsSkus.getQuantity();


        existingProductsSkus.setSku(sku);
        existingProductsSkus.setPrice(price);
        existingProductsSkus.setQuantity(quantity);

        existingProductsSkus.setCreatedAt(LocalDateTime.now());
        existingProductsSkus.setDeletedAt(LocalDateTime.now());

        productsSkusRepository.save(existingProductsSkus);

    }
}

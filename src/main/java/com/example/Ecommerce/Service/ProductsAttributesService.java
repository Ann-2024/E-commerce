package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Cart.CartItem.CartItem;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Products.ProductsAttributes.ProductsAttributes;
import com.example.Ecommerce.Model.Products.productsSkus.ProductsSkus;
import com.example.Ecommerce.repository.BankDetailsRepository;
import com.example.Ecommerce.repository.ProductsAttributesRepository;
import com.example.Ecommerce.repository.ProductsSkusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ProductsAttributesService {

    private final ProductsAttributesRepository productsAttributesRepository;
    @Autowired
    private ProductsSkusRepository productsSkusRepository;

    @Autowired
    public ProductsAttributesService(ProductsAttributesRepository productsAttributesRepository) {
        this.productsAttributesRepository = productsAttributesRepository;
    }


    public List<ProductsAttributes> getProductsAttributes() {
        return productsAttributesRepository.findAll();
    }
    public Optional<ProductsAttributes> getProductsAttributesBYId(Long productsAttributesId) {

        return productsAttributesRepository.findById(productsAttributesId);
    }
    public void addNewProductsAttributes(Long id,ProductsAttributes productsAttributes) {
        productsAttributes.setCreatedAt(new Date());
        productsAttributes.setDeletedAt(new Date());

        productsAttributesRepository.save(productsAttributes);
    }

    public void deleteProductsAttributes(Long productsAttributesId) {
        boolean exists = productsAttributesRepository.existsById(productsAttributesId);
        if (!exists) {
            throw new IllegalStateException("User with id " + productsAttributesId + " does not exist");
        }

        productsAttributesRepository.deleteById(productsAttributesId);
    }

    public void updateProductsAttributes(Long productsAttributesId, ProductsAttributes updatedProductsAttributes) {
        ProductsAttributes existingProductsAttributes =productsAttributesRepository.findById(productsAttributesId)
                .orElseThrow(() -> new IllegalStateException("User with id " + productsAttributesId + " does not exist"));


        String value = updatedProductsAttributes.getValue();

        existingProductsAttributes.setValue(value);

        existingProductsAttributes.setCreatedAt(new Date());
        existingProductsAttributes.setDeletedAt(new Date());

        productsAttributesRepository.save(existingProductsAttributes);

    }
}

package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Addresses.Pincode;
import com.example.Ecommerce.Model.Addresses.ProductPincodes;
import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.repository.ProductPincodesRepository;
import com.example.Ecommerce.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductPincodesService {

    @Autowired
    private ProductPincodesRepository productPincodesRepository;

    @Autowired
    private ProductsRepository productsRepository;

    // Add pincodes to a product
    public void addPincodesToProduct(Long productId, List<ProductPincodes.PincodeDetails> pincodeDetailsList) {
        if (pincodeDetailsList == null || pincodeDetailsList.isEmpty()) {
            throw new IllegalArgumentException("Pincode details list cannot be null or empty");
        }

        // Check if the product exists in PostgreSQL
        Products product = productsRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // Find or create the ProductPincodes entry in MongoDB
        ProductPincodes productPincodes = productPincodesRepository.findByProductId(productId);
        if (productPincodes == null) {
            productPincodes = new ProductPincodes();
            productPincodes.setProductId(productId); // Ensure productId is stored as Long
        }

        productPincodes.setPincodeDetailsList(pincodeDetailsList); // Update the pincode details
        productPincodesRepository.save(productPincodes);
    }

    // Get pincodes for a product
    public ProductPincodes getPincodesForProduct(Long productId) {
        ProductPincodes productPincodes = productPincodesRepository.findByProductId(productId);
        return productPincodes;
    }
    public boolean isDeliveryPossible(Long productId, String pincode) {
        // Fetch pincodes for the given product
        ProductPincodes productPincodes = productPincodesRepository.findByProductId(productId);

        if (productPincodes == null) {
            // Product not found or no pincodes available for this product
            return false;
        }

        List<ProductPincodes.PincodeDetails> pincodeDetails = productPincodes.getPincodeDetailsList();

        // Check if any pincode in the list matches the given pincode
        return pincodeDetails.stream()
                .anyMatch(detail -> detail.getPincode().equals(pincode));
    }



}
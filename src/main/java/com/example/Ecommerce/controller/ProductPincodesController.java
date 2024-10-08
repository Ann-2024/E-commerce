package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Addresses.ProductPincodes;
import com.example.Ecommerce.Model.Addresses.ProductPincodes.PincodeDetails;
import com.example.Ecommerce.Service.ProductPincodesService;
import com.example.Ecommerce.repository.ProductPincodesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/productpincodes")
@CrossOrigin(value="*")

public class ProductPincodesController {

    @Autowired
    private ProductPincodesRepository productPincodesRepository;

    @Autowired
    private ProductPincodesService productPincodesService;

    // Endpoint to add pincodes to a product
    @GetMapping("/checkDelivery")
    public ResponseEntity<Boolean> checkDelivery(@RequestParam(value = "productId") Long productId, @RequestParam(value = "pinCode") String pincode) {
        boolean isAvailable = productPincodesService.isDeliveryPossible(productId, pincode);
        return ResponseEntity.ok(isAvailable);
    }
    @PostMapping("/add/{productId}")
    public ResponseEntity<String> addPincodes(@PathVariable Long productId, @RequestBody List<PincodeDetails> pincodeDetailsList) {
        try {
            System.out.print("hello pincode controller");
            productPincodesService.addPincodesToProduct(productId, pincodeDetailsList);
            return ResponseEntity.ok("Pincodes added successfully to product " + productId);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to add pincodes: " + e.getMessage());
        }
    }

    // Endpoint to get pincodes for a product

    @GetMapping("/getall/{productId}")
    public List<ProductPincodes.PincodeDetails> getPincodesForProduct(@PathVariable(value = "productId") Long productId) {
        System.out.println("API called for productId: " + productId);
        try {
            // Check if the service method is returning the correct data
            ProductPincodes productPincodes = (ProductPincodes) productPincodesService.getPincodesForProduct(productId);
            if (productPincodes == null) {
                System.out.println("No ProductPincodes found for productId: " + productId);
                return Collections.emptyList();
            }
            System.out.println("Pincodes found: " + productPincodes.getPincodeDetailsList());
            return productPincodes.getPincodeDetailsList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

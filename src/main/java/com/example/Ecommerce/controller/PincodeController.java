package com.example.Ecommerce.controller;


import com.example.Ecommerce.Model.Addresses.Pincode;
import com.example.Ecommerce.Model.Order.OrderItem;
import com.example.Ecommerce.Service.PincodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/pincodes")
@CrossOrigin(value="*")

public class PincodeController {

    @Autowired
    private PincodeService pincodeService;

    @GetMapping("/check/{pincode}")
    public ResponseEntity<String> checkPincodeAvailability(@PathVariable String pincode) {
        String result = pincodeService.isPincodeAvailableForDelivery(pincode);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/getall")
    public List<Pincode> getOrderItem() {
        return pincodeService.getPincode();
    }
}

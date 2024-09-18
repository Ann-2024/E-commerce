package com.example.Ecommerce.controller;


import com.example.Ecommerce.Service.PincodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

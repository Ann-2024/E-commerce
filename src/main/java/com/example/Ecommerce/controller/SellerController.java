package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Seller.BankDetails_seller.BankDetails;
import com.example.Ecommerce.Service.SellerService;
import com.example.Ecommerce.Model.Seller.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/seller")
@CrossOrigin(value="*")

public  class  SellerController {
    @Autowired
    private SellerService sellerService;

    @GetMapping("/getall")
    public List<Seller> getSeller() {
        return sellerService.getSeller();
    }
    @GetMapping("/getall/{id}")
    public Optional<Seller> getSellerBYId(@PathVariable(value = "id") Long sellerId) {
        return sellerService.getSellerBYId(sellerId);
    }

    @PostMapping("/add")
    public String registerNewUsers(@RequestBody Seller seller) {
        sellerService.addNewSeller(seller);
        return "successfully added seller";
    }

    @DeleteMapping(path = "{sellerId}")
    public void deleteSeller(@PathVariable("sellerId") Long sellerId) {
        sellerService.deleteSeller(sellerId);
    }

    @PutMapping(path = "{sellerId}")
    public void updateSeller(@RequestBody Seller seller, @PathVariable("sellerId") Long sellerId) {
        sellerService.updateSeller(sellerId, seller);
    }
}
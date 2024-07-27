package com.example.Ecommerce.controller;

import com.example.Ecommerce.Service.SellerService;
import com.example.Ecommerce.Model.Seller.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/seller")
public final class  SellerController {
    @Autowired
    private SellerService sellerService;

    @GetMapping("{bysellerId}")
    public List<Seller> getSeller() {
        return sellerService.getSeller();
    }

    @PostMapping("/addseller")
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
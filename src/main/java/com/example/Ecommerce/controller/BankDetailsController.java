package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.BankDetails_seller.BankDetails;
import com.example.Ecommerce.Service.BankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/bankDetails")
public class  BankDetailsController {
    @Autowired
    private BankDetailsService bankDetailsService;

    @GetMapping("/{id}")
    public List<BankDetails>getBankDetails() {
        return bankDetailsService.getBankDetails();
    }

    @PostMapping("/add")
    public String registerNewBankDetails(@RequestParam (name ="id") @PathVariable Long id, @RequestBody BankDetails bankDetails) {
        bankDetailsService.addNewBankDetails(id,bankDetails);
        return "successfully added bankDetails";
    }

    @DeleteMapping(path = "{bankDetailsId}")
    public void deleteBankDetails(@PathVariable("bankDetailsId") Long bankDetailsId) {
        bankDetailsService.deleteBankDetails(bankDetailsId);
    }

    @PutMapping(path = "{bankDetailsId}")
    public void updateBankDetails(@RequestBody BankDetails bankDetails, @PathVariable("bankDetailsId") Long bankDetailsId) {
        bankDetailsService.updateBankDetails(bankDetailsId, bankDetails);
    }
}


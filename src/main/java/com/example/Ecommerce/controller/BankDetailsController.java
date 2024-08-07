package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Addresses.Addresses;
import com.example.Ecommerce.Model.Seller.BankDetails_seller.BankDetails;
import com.example.Ecommerce.Service.BankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/bankDetails")
public class  BankDetailsController {
    @Autowired
    private BankDetailsService bankDetailsService;

    @GetMapping("/getall")
    public List<BankDetails>getBankDetails() {
        return bankDetailsService.getBankDetails();
    }

    @GetMapping("/getall/{id}")
    public Optional<BankDetails> getBankDetailsBYId(@PathVariable(value = "id") Long bankDetailsid) {
        return bankDetailsService.getBankDetailsBYId(bankDetailsid);
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


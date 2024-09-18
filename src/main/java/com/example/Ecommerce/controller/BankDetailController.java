package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Seller.BankDetails_seller.BankDetails;
import com.example.Ecommerce.Model.Users.BankDetail.BankDetail;
import com.example.Ecommerce.Service.BankDetailService;
import com.example.Ecommerce.Service.BankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/bankDetail")
@CrossOrigin(value="*")

public class  BankDetailController {
    @Autowired
    private BankDetailService bankDetailService;

    @GetMapping("/getall")
    public List<BankDetail> getBankDetail() {
        return bankDetailService.getBankDetail();
    }

    @GetMapping("/getall/{id}")
    public Optional<BankDetail> getBankDetailBYId(@PathVariable(value = "id") Long bankDetailId) {
        return bankDetailService.getBankDetailBYId(bankDetailId);
    }
    @PostMapping("/add")
    public String registerNewBankDetail(@RequestParam (name ="usersId") @PathVariable Long usersId, @RequestBody BankDetail bankDetail) {
        bankDetailService.addNewBankDetail(usersId,bankDetail);
        return "successfully added bankDetail";
    }

    @DeleteMapping(path = "{bankDetailId}")
    public void deleteBankDetail(@PathVariable("bankDetailId") Long bankDetailId) {
        bankDetailService.deleteBankDetail(bankDetailId);
    }

    @PutMapping(path = "{bankDetailId}")
    public void updateBankDetail(@RequestBody BankDetail bankDetail, @PathVariable("bankDetailId") Long bankDetailId) {
        bankDetailService.updateBankDetail(bankDetailId, bankDetail);
    }
}


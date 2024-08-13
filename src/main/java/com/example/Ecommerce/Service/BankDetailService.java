package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Cart.Cart;
import com.example.Ecommerce.Model.Seller.BankDetails_seller.BankDetails;
import com.example.Ecommerce.Model.Seller.Seller;
import com.example.Ecommerce.Model.Users.BankDetail.BankDetail;
import com.example.Ecommerce.Model.Users.Users;
import com.example.Ecommerce.repository.BankDetailRepository;
import com.example.Ecommerce.repository.BankDetailsRepository;
import com.example.Ecommerce.repository.SellerRepository;
import com.example.Ecommerce.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BankDetailService {

    private final BankDetailRepository bankDetailRepository;

    @Autowired
    public BankDetailService(BankDetailRepository bankDetailRepository) {
        this.bankDetailRepository = bankDetailRepository;
    }

    @Autowired
    private UsersRepository usersRepository;

    public List<BankDetail> getBankDetail() {
        return bankDetailRepository.findAll();
    }
    public Optional<BankDetail> getBankDetailBYId(Long bankDetailId) {

        return bankDetailRepository.findById(bankDetailId);
    }
    public void addNewBankDetail( Long usersId, BankDetail bankDetail) {
        Users users = usersRepository.findById(usersId).get();
        System.out.println("Wishlist service");

        bankDetail.setUsers(users);
        bankDetailRepository.save(bankDetail);

    }

    public void deleteBankDetail(Long bankDetailId) {
        boolean exists = bankDetailRepository.existsById(bankDetailId);
        if (!exists) {
            throw new IllegalStateException("User with id " + bankDetailId + " does not exist");
        }

        bankDetailRepository.deleteById(bankDetailId);
    }

    public void updateBankDetail(Long bankDetailId,BankDetail updatedBankDetail) {
        BankDetail existingBankDetail = bankDetailRepository.findById(bankDetailId)
                .orElseThrow(() -> new IllegalStateException("User with id " + bankDetailId + " does not exist"));


        String holderName = updatedBankDetail.getHolderName();
        String bankName= updatedBankDetail.getBankName();
        String accountNumber = updatedBankDetail.getAccountNumber();
        String ifscCode = updatedBankDetail.getIfscCode();
        String upiId = updatedBankDetail.getUpiId();



        existingBankDetail.setHolderName(holderName);
        existingBankDetail.setBankName(bankName);
        existingBankDetail.setAccountNumber(accountNumber);
        existingBankDetail.setIfscCode(ifscCode);
        existingBankDetail.setUpiId(upiId);



        bankDetailRepository.save(existingBankDetail);

    }
}
package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.BankDetails_seller.BankDetails;
import com.example.Ecommerce.Model.Seller.Seller;
import com.example.Ecommerce.repository.BankDetailsRepository;
import com.example.Ecommerce.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankDetailsService {

    private final BankDetailsRepository bankDetailsRepository;

    @Autowired
    public BankDetailsService(BankDetailsRepository bankDetailsRepository) {
        this.bankDetailsRepository = bankDetailsRepository;
    }

    @Autowired
    private SellerRepository sellerRepository;

    public List<BankDetails> getBankDetails() {
        return bankDetailsRepository.findAll();
    }

    public void addNewBankDetails(Long id,BankDetails bankDetails) {
        Optional<BankDetails> bankDetailsOptional = bankDetailsRepository.findByAccountNumber(bankDetails.getAccountNumber());
        if (bankDetailsOptional.isPresent()) {
            throw new IllegalStateException("AccountNumber taken");
        }

        Optional<Seller> seller = sellerRepository.findById(id);
        if (seller.isEmpty()) {
            throw new IllegalStateException("Seller not found");
        }else {
            bankDetails.setSeller(seller.get());
        }


        bankDetailsRepository.save(bankDetails);


    }

    public void deleteBankDetails(Long bankDetailsId) {
        boolean exists = bankDetailsRepository.existsById(bankDetailsId);
        if (!exists) {
            throw new IllegalStateException("User with id " + bankDetailsId + " does not exist");
        }

        bankDetailsRepository.deleteById(bankDetailsId);
    }

    public void updateBankDetails(Long bankDetailsId,BankDetails updatedBankDetails) {
        BankDetails existingBankDetails = bankDetailsRepository.findById(bankDetailsId)
                .orElseThrow(() -> new IllegalStateException("User with id " + bankDetailsId + " does not exist"));


        String holderName = updatedBankDetails.getHolderName();
        String bankName= updatedBankDetails.getBankName();
        String accountNumber = updatedBankDetails.getAccountNumber();
        String ifscCode = updatedBankDetails.getIfscCode();
        String upiId = updatedBankDetails.getUpiId();



        existingBankDetails.setHolderName(holderName);
        existingBankDetails.setBankName(bankName);
        existingBankDetails.setAccountNumber(accountNumber);
        existingBankDetails.setIfscCode(ifscCode);
        existingBankDetails.setUpiId(upiId);



        bankDetailsRepository.save(existingBankDetails);

    }
}

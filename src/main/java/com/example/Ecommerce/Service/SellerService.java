package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Seller.Seller;
import com.example.Ecommerce.repository.BankDetailsRepository;
import com.example.Ecommerce.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class SellerService {

    private final SellerRepository sellerRepository;
    @Autowired
    private  BankDetailsRepository bankDetailsRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }


    public List<Seller> getSeller() {
        return sellerRepository.findAll();
    }
//    Optional<BankDetails> bankDetails = bankDetailsRepository.findById(id);
//        if (seller.isEmpty()) {
//        throw new IllegalStateException("Seller not found");
//    }else {
//        bankDetails.setSeller(seller.get());
//    }

    public void addNewSeller(Seller seller) {
        Optional<Seller> sellerOptional = sellerRepository.findByEmail(seller.getEmail());
        if (sellerOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }

        Seller seller1 = new Seller();
        seller1.setName(seller.getName());
        seller1.setAddress_line_1(seller.getAddress_line_1());
        seller1.setAddress_line_2(seller.getAddress_line_2());
        seller1.setPhoneNumber(seller.getPhoneNumber());
        seller1.setEmail(seller.getEmail());
        seller1.setCountry(seller.getCountry());
        seller1.setState(seller.getState());
        seller1.setCity(seller.getCity());
        seller1.setLandmark(seller.getLandmark());
        seller1.setPostal_code(seller.getPostal_code());
        seller1.setImageUrl(seller.getImageUrl());
        seller1.setCreatedAt(new Date());
        System.out.println(seller1.getAddress_line_1());
        sellerRepository.save(seller1);
    }

    public void deleteSeller(Long sellerId) {
        boolean exists = sellerRepository.existsById(sellerId);
        if (!exists) {
            throw new IllegalStateException("User with id " + sellerId + " does not exist");
        }

        sellerRepository.deleteById(sellerId);
    }

    public void updateSeller(Long sellerId, Seller updatedSeller) {
        Seller existingSeller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new IllegalStateException("User with id " + sellerId + " does not exist"));


        String name = updatedSeller.getName();
        String address_line_1 = updatedSeller.getAddress_line_1();
        String address_line_2 = updatedSeller.getAddress_line_2();
        String phoneNumber = updatedSeller.getPhoneNumber();
        String email = updatedSeller.getEmail();
        String country = updatedSeller.getCountry();
        String city = updatedSeller.getCity();
        String state = updatedSeller.getState();
        String ImageUrl = updatedSeller.getImageUrl();
        String landmark = updatedSeller.getLandmark();
        String postal_code = updatedSeller.getPostal_code();






        existingSeller.setName(name);
        existingSeller.setAddress_line_1(address_line_1);
        existingSeller.setAddress_line_2(address_line_2);
        existingSeller.setPhoneNumber(phoneNumber);
        existingSeller.setEmail(email);
        existingSeller.setCountry(country);
        existingSeller.setCity(city);
        existingSeller.setState(state);
        existingSeller.setImageUrl(ImageUrl);
        existingSeller.setLandmark(landmark);
        existingSeller.setPostal_code(postal_code);
        existingSeller.setCreatedAt(new Date());

        sellerRepository.save(existingSeller);

    }
}



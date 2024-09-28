package com.example.Ecommerce.Service;

import com.example.Ecommerce.Model.Addresses.Pincode;
import com.example.Ecommerce.Model.Order.PaymentDetails.PaymentDetails;
import com.example.Ecommerce.repository.PincodeRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PincodeService {

    @Autowired
    private PincodeRepository pincodeRepository;

    private List<Pincode> availablePincodes;


    public PincodeService() {
        loadPincodesFromJson();
    }
    @PostConstruct
    private void loadPincodesFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("postalcodes.json")) {
            if (inputStream == null) {
                throw new FileNotFoundException("Resource not found: postalcodes.json");
            }
            PincodeList pincodeList = objectMapper.readValue(inputStream, PincodeList.class);
            availablePincodes = pincodeList.getSheet1();
        } catch (IOException e) {
            System.err.println("Error loading postalcodes from JSON: " + e.getMessage());
        }


    }
    public List<Pincode> getPincode() {
        List<Pincode> pincodes = new ArrayList<>(pincodeRepository.findAll());
        if (availablePincodes != null) {
            pincodes.addAll(availablePincodes);
        }
        return pincodes;
    }

    public String isPincodeAvailableForDelivery(String pincode) {
        // Check MongoDB
        List<Pincode> foundPincodes = pincodeRepository.findByPincode(pincode);
        if (!foundPincodes.isEmpty()) {  // Check if the list has any results
            return "Pincode " + pincode + " is available for delivery.";
        }

        // Check JSON data
        if (availablePincodes == null || availablePincodes.isEmpty()) {
            return "Pincode availability data is not loaded.";
        }

        boolean isAvailable = availablePincodes.stream()
                .anyMatch(p -> p.getPincode().equals(pincode));

        return isAvailable ? "Pincode " + pincode + " is available for delivery."
                : "Pincode " + pincode + " is not available for delivery.";
    }

    public static class PincodeList {
        @JsonProperty("Sheet1")
        private List<Pincode> Sheet1;

        public List<Pincode> getSheet1() {
            return Sheet1;
        }

        public void setSheet1(List<Pincode> sheet1) {
            this.Sheet1 = sheet1;
        }
    }
}

package com.example.Ecommerce.Model.Addresses;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.Collection;
import java.util.List;

@Document(collection = "productpincodes")
public class ProductPincodes {

    @Id
    private String id; // MongoDB document ID

    private Long productId; // Product ID from PostgreSQL

    // List of arrays, where each array contains details like district, state, city, and pincode
    private List<PincodeDetails> pincodeDetailsList;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<PincodeDetails> getPincodeDetailsList() {
        return pincodeDetailsList;
    }

    public void setPincodeDetailsList(List<PincodeDetails> pincodeDetailsList) {
        this.pincodeDetailsList = pincodeDetailsList;
    }




    // Inner class to represent the pincode details
    public static class PincodeDetails {
        private String postOfficeName;
        private String pincode;
        private String city;
        private String district;
        private String state;

        // Getters and Setters
        public String getPostOfficeName() {
            return postOfficeName;
        }

        public void setPostOfficeName(String postOfficeName) {
            this.postOfficeName = postOfficeName;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}

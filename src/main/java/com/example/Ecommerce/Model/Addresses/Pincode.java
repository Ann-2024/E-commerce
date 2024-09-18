package com.example.Ecommerce.Model.Addresses;

import com.example.Ecommerce.Model.Users.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Date;

@Document(collection = "pincode")

public class Pincode {


    @JsonProperty("PostOfficeName")
    private String postOfficeName;

    @JsonProperty("Pincode")
    private String pincode;

    @JsonProperty("City")
    private String city;

    @JsonProperty("District")
    private String district;

    @JsonProperty("State")
    private String state;

    @JsonProperty("Id")
    private String id;



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

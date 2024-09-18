package com.example.Ecommerce.Model.Addresses;

import com.example.Ecommerce.Model.Users.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.processing.Pattern;

import java.util.Date;

@Entity
@Table(name = "addresses")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Addresses{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users users;

    private String title;
    private String address_line_1;
    private String address_line_2;
    private String country;
    private String city;
    private String pincode;
    private String landmark;
    private String phoneNumber;
    private Date createdAt;
    private boolean isDeliverable;

//    public Long getaddressId() {
//        return addressid;
//    }

//    public void setId(Long id) {
//        this.addressid = id;
//    }
//
//    public Users getUsers() {
//        return users;
//    }
//
//    public void setUsers(Users users) {
//        this.users = users;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getAddress_line_1() {
//        return address_line_1;
//    }
//
//    public void setAddress_line_1(String address_line_1) {
//        this.address_line_1 = address_line_1;
//    }
//
//    public String getAddress_line_2() {
//        return address_line_2;
//    }
//
//    public void setAddress_line_2(String address_line_2) {
//        this.address_line_2 = address_line_2;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getPostalCode() {
//        return postalCode;
//    }
//
//    public void setPostalCode(String postalCode) {
//        this.postalCode = postalCode;
//    }
//
//    public String getLandmark() {
//        return landmark;
//    }
//
//    public void setLandmark(String landmark) {
//        this.landmark = landmark;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public Long getAddressid() {
//        return addressid;
//    }
//
//    public void setAddressid(Long addressid) {
//        this.addressid = addressid;
//    }
//
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }


}

package com.example.Ecommerce.Model.Addresses;

import com.example.Ecommerce.Model.Users.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
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
    @ToString.Exclude // Prevents infinite recursion in the toString method
    @JoinColumn(name = "userId", nullable = false)
    private Users users;

    private String title;
    private String address_line_1;
    private String address_line_2;
    private String country="INDIA";
    private String city;
    private String postalCode;
    private String landmark;
    private String phoneNumber;
    private Date createdAt;




}

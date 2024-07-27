package com.example.Ecommerce.Model.BankDetails_seller;

import com.example.Ecommerce.Model.Seller.Seller;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bankdetails")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String HolderName;
    private String bankName;
    private String accountNumber;
    private String ifscCode;
    private String UpiId;
    @OneToOne
    @JoinColumn(name = "sellerId", referencedColumnName = "id", unique = true)
    @JsonBackReference
    private Seller seller;

}

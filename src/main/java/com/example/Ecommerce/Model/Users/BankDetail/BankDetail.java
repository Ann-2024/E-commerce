package com.example.Ecommerce.Model.Users.BankDetail;

import com.example.Ecommerce.Model.Seller.Seller;
import com.example.Ecommerce.Model.Users.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bankdetail")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String HolderName;
    private String bankName;
    @Column(unique = true)
    private String accountNumber;
    private String ifscCode;
    private String UpiId;
    @ManyToOne
    @JoinColumn(name = "usersId",  referencedColumnName = "id", unique = true,nullable = false)
    private Users users;
}

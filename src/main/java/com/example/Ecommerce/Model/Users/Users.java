package com.example.Ecommerce.Model.Users;

import com.example.Ecommerce.Model.Addresses.Addresses;
import com.example.Ecommerce.Model.Cart.Cart;
import com.example.Ecommerce.Model.Order.OrderDetails;
import com.example.Ecommerce.Model.Users.BankDetail.BankDetail;
import com.example.Ecommerce.Model.user.Role;
import com.example.Ecommerce.Model.wishlist.Wishlist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class  Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Wishlist> wishlist;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude // Prevents infinite recursion in the toString method
    private List<Cart> cart;

    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude // Prevents infinite recursion in the toString method
    private List<OrderDetails>orderDetails;

    private String avatar;
    private String firstName;
    private String lastName;
//    private String username;
    private String email;
    private LocalDate birthOfDate;
    private String phoneNumber;
    @Getter
    @JsonIgnore
    private String password;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude // Prevents infinite recursion in the toString method
    private List<Addresses> addresses;


    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BankDetail> bankDetail;


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

}




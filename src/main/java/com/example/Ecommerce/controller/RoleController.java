package com.example.Ecommerce.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecommerce/v1")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('admin:read')")
    public String getAdmin() {

        return "Secured Endpoint :: GET - Admin controller";

    }

    @PostMapping("/admin")
    @PreAuthorize("hasAuthority('admin:create')")
    public String postAdmin() {
        return "Secured Endpoint :: POST - Admin controller";

    }
    @PutMapping("/admin/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public String putAdmin() {
        return "Secured Endpoint :: PUT - Admin controller";

    }
    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public String deleteAdmin() {
        return "Secured Endpoint :: DELETE - Admin controller";

    }
    @GetMapping("/customer")
    @PreAuthorize("hasAuthority('customer:read')")
    public String getCustomer() {
        return "Secured Endpoint :: GET - Customer controller";
    }


    @PostMapping("/customer")
    @PreAuthorize("hasAuthority('customer:create')")
    public String postCustomer() {
        return "POST:: Customer controller";

    }
    @PutMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('customer:update')")
    public String putCustomer() {
        return "Secured Endpoint :: PUT - Customer controller";
    }
    @DeleteMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('customer:delete')")
    public String deleteCustomer() {
        return "Secured Endpoint :: delete - Customer controller";
    }
    @GetMapping("/seller")
    @PreAuthorize("hasAuthority('seller:read')")
    public String getSeller() {
        return "Secured Endpoint :: GET - Seller controller";
    }


    @PostMapping("/seller")
    @PreAuthorize("hasAuthority('seller:create')")
    public String postSeller() {
        return "POST:: Seller controller";
    }
    @PutMapping("/seller/{id}")
    @PreAuthorize("hasAuthority('seller:update')")
    public String putSeller() {
        return "Secured Endpoint :: PUT -Seller controller";
    }


    @DeleteMapping("/seller/{id}")
    @PreAuthorize("hasAuthority('seller:delete')")
    public String deleteSeller() {
        return "DELETE:: Seller controller";
    }

}

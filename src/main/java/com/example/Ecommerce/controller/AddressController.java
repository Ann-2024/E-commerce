package com.example.Ecommerce.controller;

import com.example.Ecommerce.Service.AddressService;
import com.example.Ecommerce.Model.Addresses.Addresses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/Address")
public class  AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<Addresses> getAddresses() {
        return addressService.getAddresses();
    }

    @PostMapping("/addAddress")
    public void registerNewAddresses(@RequestParam (name ="id") @PathVariable Long id, @RequestBody Addresses  addresses) {
        addressService.addNewAddresses(id,addresses);
    }

    @DeleteMapping(path = "{addressId}")
    public void deleteAddresses(@PathVariable("addressId") Long addressId) {
        addressService.deleteAddresses(addressId);
    }

    @PutMapping(path = "{addressId}")
    public void updateAddresses(@RequestBody Addresses addresses, @PathVariable("addressId") Long addressId) {
       addressService.updateAddresses(addressId, addresses);
    }
}

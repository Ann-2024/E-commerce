package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.Addresses.Addresses;
import com.example.Ecommerce.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/address")
@CrossOrigin(value="*")


public class  AddressController {
    @Autowired
    private AddressService addressService;


    @PostMapping("/add")
    public String registerNewAddresses(@RequestParam (name ="id") Long id, @RequestBody Addresses addresses) {
        System.out.println("hello addres conntroller");
        addressService.addNewAddresses(id,addresses);
        return "Address added successfully";
    }

    @GetMapping("/getall")
    public List<Addresses> getAddresses() {
        return addressService.getAddresses();
    }

    @GetMapping("/getall/{id}")
    public Optional<Addresses> getAddressesBYId(@PathVariable(value = "id") Long addressId) {
        return addressService.getAddressesBYId(addressId);
    }

    @GetMapping("/getByUser")
    public List<Addresses> getAddressesBYUser(@RequestParam(value = "userId") Long id) {
        return addressService.getAddressesBYUser(id);
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

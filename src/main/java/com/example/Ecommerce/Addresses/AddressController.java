package com.example.Ecommerce.Addresses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/Address")
public class  AddressController {
    @Autowired
    private AddressService addressService;



    @GetMapping("/message")
    public String getMsg(){

        return "welcome to e-commerce";

    }

    @PostMapping("/addAddress")
    public String registerNewAddresses(@RequestParam (name ="id") Long id, @RequestBody Addresses  addresses) {
        System.out.println("hello addres conntroller");
        addressService.addNewAddresses(id,addresses);
        return "Address added successfully";
    }

    @GetMapping("/allAddress")
    public List<Addresses> getAddresses() {
        return addressService.getAddresses();
    }

    @GetMapping("/byId/{id}")
    public Optional<Addresses> getAddressBYId(@PathVariable(value = "id") Long addressid) {
        return addressService.getAddressBYId(addressid);
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

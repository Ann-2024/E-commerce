package com.example.Ecommerce.Addresses;


import com.example.Ecommerce.Users.Users;
import com.example.Ecommerce.repository.AddressRepository;
import com.example.Ecommerce.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    public void addNewAddresses(Long id,Addresses addresses) {

        Users users = usersRepository.findById(id).get();

        if (users == null){

            throw new RuntimeException("user not found");
        }else {


            addresses.setCreatedAt(new Date());
            addresses.setUsers(users);
            addressRepository.save(addresses);
        }
    }

    public List<Addresses> getAddresses() {

        return addressRepository.findAll();

    }

    public Optional<Addresses> getAddressBYId(Long addressid) {

        return addressRepository.findById(addressid);
    }

    public void deleteAddresses(Long addressId) {

        boolean exists = addressRepository.existsById((long) Math.toIntExact(addressId));

        if (!exists) {
            throw new IllegalStateException("Address with id " + addressId + " does not exist");
        }

        addressRepository.deleteById((long) Math.toIntExact(addressId));
    }

    public void updateAddresses(Long addressId, Addresses updatedAddress) {

        Addresses existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalStateException("Address with id " + addressId + " does not exist"));

        existingAddress.setTitle(updatedAddress.getTitle());
        existingAddress.setAddress_line_1(updatedAddress.getAddress_line_1());
        existingAddress.setAddress_line_2(updatedAddress.getAddress_line_2());
        existingAddress.setCountry(updatedAddress.getCountry());
        existingAddress.setCity(updatedAddress.getCity());
        existingAddress.setPostalCode(updatedAddress.getPostalCode());
        existingAddress.setLandmark(updatedAddress.getLandmark());
        existingAddress.setPhoneNumber(updatedAddress.getPhoneNumber());
        existingAddress.setCreatedAt(new Date());

        addressRepository.save(existingAddress);
    }


}

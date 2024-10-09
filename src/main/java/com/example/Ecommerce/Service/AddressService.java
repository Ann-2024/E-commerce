package com.example.Ecommerce.Service;


import ch.qos.logback.classic.boolex.MarkerList;
import com.example.Ecommerce.Model.Addresses.Addresses;
import com.example.Ecommerce.Model.Users.Users;
import com.example.Ecommerce.repository.AddressRepository;
import com.example.Ecommerce.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UsersRepository usersRepository;

    public void addNewAddresses(Long id, Addresses addresses) {


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

    public Optional<Addresses> getAddressesBYId(Long addressId) {
        return addressRepository.findById(addressId);
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
        existingAddress.setCountry(existingAddress.getCountry());
        existingAddress.setCity(updatedAddress.getCity());
        existingAddress.setPostalCode(updatedAddress.getPostalCode());
        existingAddress.setLandmark(updatedAddress.getLandmark());
        existingAddress.setPhoneNumber(updatedAddress.getPhoneNumber());
        existingAddress.setCreatedAt(new Date());

        addressRepository.save(existingAddress);
    }


    public List<Addresses> getAddressesBYUser(Long id) {

        System.out.println("get address by user");
    Users user = usersRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("user with this id "+id+" not present"));

    return addressRepository.findByUsers(user);

    }
}

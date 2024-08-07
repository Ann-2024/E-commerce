package com.example.Ecommerce.Service;


import com.example.Ecommerce.Model.Products.Products;
import com.example.Ecommerce.Model.Users.Users;
import com.example.Ecommerce.Model.wishlist.Wishlist;
import com.example.Ecommerce.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class UserService {

   @Autowired
    private  UsersRepository usersRepository;

   @Autowired
    private  PasswordEncoder passwordEncoder;

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    public Optional<Users> getUsersBYId(Long usersId) {

        return usersRepository.findById(usersId);
    }
    public void addNewUsers(Users users) {
        System.out.println("hello users service");
        Optional<Users> usersOptional = usersRepository.findById(users.getId());
        if (usersOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        users.setCreatedAt(new Date());
        users.setPassword(passwordEncoder.encode(users.getPassword()));

        usersRepository.save(users);
    }


    public void deleteUsers(Long userId) {
        boolean exists = usersRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("User with id " + userId + " does not exist");
        }

        usersRepository.deleteById(userId);
    }

    public void updateUsers(Long userId, Users updatedUsers) {
        Users existingUsers = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist"));


        String avatar = updatedUsers.getAvatar();
        String firstName = updatedUsers.getFirstName();
        String lastName = updatedUsers.getLastName();
        String username = updatedUsers.getUsername();
        String password = updatedUsers.getPassword();
        String email = updatedUsers.getEmail();
        LocalDate birthofDate = updatedUsers.getBirthOfDate();
        String phoneNumber = updatedUsers.getPhoneNumber();





        existingUsers.setAvatar(avatar);
        existingUsers.setEmail(email);
        existingUsers.setFirstName(firstName);
        existingUsers.setLastName(lastName);
        existingUsers.setUsername(username);
        existingUsers.setPassword(passwordEncoder.encode(password));
        existingUsers.setBirthOfDate(birthofDate);
        existingUsers.setPhoneNumber(phoneNumber);
        existingUsers.setCreatedAt(new Date());

        usersRepository.save(existingUsers);
    }


}









package com.example.Ecommerce.Users;


import com.example.Ecommerce.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

   @Autowired
    private  UsersRepository usersRepository;

   @Autowired
    private  PasswordEncoder passwordEncoder;

//    @Autowired
//    public UserService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
//        this.usersRepository = usersRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    public void addNewUsers(Users users) {
        System.out.println("hello users service");
        Optional<Users> usersOptional = usersRepository.findByEmail(users.getEmail());
        if (usersOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        users.setCreatedAt(new Date());
        users.setPassword(passwordEncoder.encode(users.getPassword()));

        usersRepository.save(users);
    }

    public Optional<Users> getUser(Long id) {
        return usersRepository.findById(id);

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
//    if (avatar != null && !avatar.isEmpty() && !Objects.equals(existingUsers.getAvatar(), avatar)) {
//            existingUsers.setAvatar(avatar);
//        }
//
//        if (firstName != null && !firstName.isEmpty() && !Objects.equals(existingUsers.getFirstName(), firstName)) {
//            existingUsers.setFirstName(firstName);
//        }
//
//        if (lastName != null && !lastName.isEmpty() && !Objects.equals(existingUsers.getLastName(), lastName)) {
//            existingUsers.setLastName(lastName);
//        }
//
//        if (username != null && !username.isEmpty() && !Objects.equals(existingUsers.getUsername(), username)) {
//            existingUsers.setUsername(username);
//        }
//
        if (email != null && !email.isEmpty() && !Objects.equals(existingUsers.getEmail(), email)) {
            Optional<Users> usersOptional = usersRepository.findByEmail(email);
            if (usersOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            existingUsers.setEmail(email);
        }
//
//        if (birthofDate != null && !Objects.equals(existingUsers.getBirthOfDate(),birthofDate)) {
//            existingUsers.setBirthOfDate(birthofDate);
//            System.out.println("Updated birthofDate: " + existingUsers.getBirthOfDate());
//        }
//        if (phoneNumber != null && !phoneNumber.isEmpty() && !Objects.equals(existingUsers.getPhoneNumber(), phoneNumber)) {
//            existingUsers.setPhoneNumber(phoneNumber);
//        }
//
//        if (createdAt != null && !Objects.equals(existingUsers.getCreatedAt(), createdAt)) {
//            existingUsers.setCreatedAt(createdAt);
//        }
//
//        usersRepository.save(existingUsers);
    }


}









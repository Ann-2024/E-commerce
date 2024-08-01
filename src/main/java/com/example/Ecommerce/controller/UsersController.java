package com.example.Ecommerce.controller;

import com.example.Ecommerce.Service.UserService;
import com.example.Ecommerce.Model.Users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/allUser")
    public List<Users> getUsers() {
        return userService.getUsers();
    }


    @GetMapping("/byId/{id}")
    public Optional<Users> getUser(@PathVariable("id") Long id) {
       return   userService.getUser(id);

    }


    @PostMapping("/adduser")
    public void registerNewUsers(@RequestBody Users users) {
        userService.addNewUsers(users);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUsers(@PathVariable("userId") Long id) {
        userService.deleteUsers(id);
    }

    @PutMapping(path = "{userId}")
    public void updateUsers(@RequestBody Users users, @PathVariable("userId") Long userId) {
        userService.updateUsers(userId, users);
    }
}

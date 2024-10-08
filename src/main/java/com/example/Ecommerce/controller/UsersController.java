package com.example.Ecommerce.controller;

import com.example.Ecommerce.Model.wishlist.Wishlist;
import com.example.Ecommerce.Service.UserService;
import com.example.Ecommerce.Model.Users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/users")
@CrossOrigin(value="*")

public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/getall")
    public List<Users> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/getall/{id}")
    public Optional<Users> getUsersBYId(@PathVariable(value = "id") Long usersId) {

        return userService.getUsersBYId(usersId);

    }

    @GetMapping("userByEmail")
    public ResponseEntity<Users> getUsersEmail(@RequestParam(value = "email") String email) {
        Optional<Users> user = userService.getUsersEmail(email);
        System.out.println("entered user controller");
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
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

package com.example.Ecommerce.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/users")
public class  UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{byuserId}")
    public List<Users> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/adduser")
    public void registerNewUsers(@RequestBody Users users) {
        userService.addNewUsers(users);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUsers(@PathVariable("userId") Long userId) {
        userService.deleteUsers(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateUsers(@RequestBody Users users, @PathVariable("userId") Long userId) {
        userService.updateUsers(userId, users);
    }
}

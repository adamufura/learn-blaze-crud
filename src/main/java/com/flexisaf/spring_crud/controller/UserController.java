package com.flexisaf.spring_crud.controller;

import com.flexisaf.spring_crud.service.UserService;
import com.flexisaf.spring_crud.view.UserView;
import com.flexisaf.spring_crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all users
    @GetMapping("/get-users")
    public ResponseEntity<List<UserView>> getAllUsers() {
        List<UserView> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get users by name
    @GetMapping("/get-users-by-name")
    public ResponseEntity<List<UserView>> getUsersByName(@RequestParam("name") String name) {
        List<UserView> users = userService.findUsersByName(name);
        return ResponseEntity.ok(users);
    }

    // Create a new user
    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }
}

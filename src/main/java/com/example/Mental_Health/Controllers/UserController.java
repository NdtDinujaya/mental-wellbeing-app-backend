package com.example.Mental_Health.Controllers;

// UserController.java

import com.example.Mental_Health.Models.User;
import com.example.Mental_Health.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {

        System.out.println(user);

        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody User user) {
//        String email = user.getEmail();
//        String password = user.getPassword();
//
//        // Replace with your actual email and password validation logic
//        if (email.equals("user@example.com") && password.equals("password123")) {
//            System.out.println("Success Login");
//            return ResponseEntity.ok(user);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();

        try {
            User loggedInUser = userService.loginUser(email, password);
            System.out.println("Success Login");
            return ResponseEntity.ok(loggedInUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }



    // Implement other API endpoints for user authentication and related functionalities
}

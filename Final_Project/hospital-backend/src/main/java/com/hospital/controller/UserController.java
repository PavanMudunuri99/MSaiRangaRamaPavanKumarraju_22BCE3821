package com.hospital.controller;

import com.hospital.model.User;
import com.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty() ||
            user.getPassword() == null || user.getPassword().trim().isEmpty() ||
            user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("All fields are required.");
        }

        return ResponseEntity.ok(userService.register(user));
    }

    @GetMapping("/register/{username}")
    public ResponseEntity<?> getRegisteredUser(@PathVariable String username) {
        User user = userService.getByUsername(username);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/register/{username}")
    public ResponseEntity<?> updateRegisteredUser(@PathVariable String username, @RequestBody User updatedUser) {
        User user = userService.updateUser(username, updatedUser);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/register/{username}")
    public ResponseEntity<?> deleteRegisteredUser(@PathVariable String username) {
        boolean deleted = userService.deleteUser(username);
        if (!deleted) {
            return ResponseEntity.status(404).body("User not found");
        }
        return ResponseEntity.ok("User deleted successfully");
    }
}

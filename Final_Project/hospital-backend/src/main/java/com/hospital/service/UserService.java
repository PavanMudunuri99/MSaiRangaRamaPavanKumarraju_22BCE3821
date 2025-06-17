package com.hospital.service;

import com.hospital.model.User;
import com.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
    public User updateUser(String username, User updatedUser) {
    Optional<User> userOptional = userRepository.findByUsername(username);
    if (userOptional.isPresent()) {
        User existingUser = userOptional.get();

        // ✅ Check for unique email (only if email is being changed)
        if (updatedUser.getEmail() != null && !updatedUser.getEmail().isBlank()) {
            Optional<User> emailOwner = userRepository.findByEmail(updatedUser.getEmail());
            // If the email exists and it's not the same user
            if (emailOwner.isPresent() && !emailOwner.get().getUsername().equals(username)) {
                return null; // or throw custom exception, or handle conflict
            }
            existingUser.setEmail(updatedUser.getEmail());
        }

        // ✅ Update other fields
        if (updatedUser.getUsername() != null && !updatedUser.getUsername().isBlank()) {
            existingUser.setUsername(updatedUser.getUsername());
        }

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
            existingUser.setPassword(updatedUser.getPassword());
        }

        if (updatedUser.getRole() != null && !updatedUser.getRole().isBlank()) {
            existingUser.setRole(updatedUser.getRole());
        }

        return userRepository.save(existingUser);
    }
    return null;
}


    public boolean deleteUser(String username) {
        if (userRepository.existsByUsername(username)) {
            userRepository.deleteByUsername(username);
            return true;
        }
        return false;
    }
}

package com.hospital.repository;

import com.hospital.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    void deleteByUsername(String username);

    // ✅ ADD THIS:
    Optional<User> findByEmail(String email); // <-- This fixes your build error
}

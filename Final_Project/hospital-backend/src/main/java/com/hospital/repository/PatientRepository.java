package com.hospital.repository;

import com.hospital.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {

    Optional<Patient> findByName(String name);        // ✅ for GET and PUT
    boolean existsByName(String name);                // ✅ for uniqueness check
    void deleteByName(String name);                   // ✅ for DELETE
}

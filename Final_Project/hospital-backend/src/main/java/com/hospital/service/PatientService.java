package com.hospital.service;

import com.hospital.model.Patient;
import com.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // ✅ GET by name
    public Optional<Patient> getPatientByName(String name) {
        return patientRepository.findByName(name);
    }

    // ✅ POST - Add patient only if name is unique
    public Optional<Patient> addPatient(Patient patient) {
        if (patientRepository.existsByName(patient.getName())) {
            return Optional.empty(); // Duplicate
        }
        return Optional.of(patientRepository.save(patient));
    }

    // ✅ PUT - Update by name
    public Optional<Patient> updatePatientByName(String name, Patient updatedPatient) {
        return patientRepository.findByName(name).map(existingPatient -> {
            existingPatient.setAge(updatedPatient.getAge());
            existingPatient.setGender(updatedPatient.getGender());
            existingPatient.setDisease(updatedPatient.getDisease());
            existingPatient.setEmail(updatedPatient.getEmail());
            return patientRepository.save(existingPatient);
        });
    }

    // ✅ DELETE - By name
    public boolean deletePatientByName(String name) {
        Optional<Patient> existing = patientRepository.findByName(name);
        if (existing.isPresent()) {
            patientRepository.deleteByName(name);
            return true;
        }
        return false;
    }
}

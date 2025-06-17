package com.hospital.controller;

import com.hospital.model.Patient;
import com.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // ✅ GET by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Patient> getPatientByName(@PathVariable String name) {
        return patientService.getPatientByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ POST - Add only if name is unique
    @PostMapping
    public ResponseEntity<?> addPatient(@RequestBody Patient patient) {
        Optional<Patient> saved = patientService.addPatient(patient);
        return saved.isPresent()
                ? ResponseEntity.ok(saved.get())
                : ResponseEntity.badRequest().body("Patient with name already exists.");
    }

    // ✅ PUT by name
    @PutMapping("/name/{name}")
    public ResponseEntity<Patient> updatePatient(@PathVariable String name, @RequestBody Patient updatedPatient) {
        return patientService.updatePatientByName(name, updatedPatient)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ DELETE by name
    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deletePatient(@PathVariable String name) {
        boolean deleted = patientService.deletePatientByName(name);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

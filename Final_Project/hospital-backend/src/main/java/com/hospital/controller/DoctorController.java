package com.hospital.controller;

import com.hospital.model.Doctor;
import com.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // ✅ Get doctor by Email
    @GetMapping("/email/{email}")
    public ResponseEntity<Doctor> getDoctorByEmail(@PathVariable String email) {
        Optional<Doctor> doctor = doctorService.getDoctorByEmail(email);
        return doctor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // ✅ Add doctor
    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }

    // ✅ Update doctor by Email
    @PutMapping("/email/{email}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable String email, @RequestBody Doctor updatedDoctor) {
        return doctorService.updateDoctorByEmail(email, updatedDoctor)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Delete doctor by Email
    @DeleteMapping("/email/{email}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable String email) {
        boolean deleted = doctorService.deleteDoctorByEmail(email);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

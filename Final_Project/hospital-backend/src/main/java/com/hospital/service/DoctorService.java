package com.hospital.service;

import com.hospital.model.Doctor;
import com.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Get doctor by Email
    public Optional<Doctor> getDoctorByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }

    // Add a new doctor
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Update doctor by Email
    public Optional<Doctor> updateDoctorByEmail(String email, Doctor updatedDoctor) {
        return doctorRepository.findByEmail(email).map(doctor -> {
            doctor.setName(updatedDoctor.getName());
            doctor.setSpecialization(updatedDoctor.getSpecialization());
            doctor.setPhone(updatedDoctor.getPhone());
            doctor.setEmail(updatedDoctor.getEmail());
            return doctorRepository.save(doctor);
        });
    }

    // Delete doctor by Email
    public boolean deleteDoctorByEmail(String email) {
        Optional<Doctor> doctor = doctorRepository.findByEmail(email);
        if (doctor.isPresent()) {
            doctorRepository.deleteByEmail(email);
            return true;
        }
        return false;
    }
}

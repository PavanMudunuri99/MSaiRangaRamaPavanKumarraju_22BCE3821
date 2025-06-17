package com.hospital.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "patients")
public class Patient {

    @Id
    private String id;

    private String name;     // ✅ Required and must be unique
    private int age;
    private String gender;
    private String disease;
    private String email;    // ✅ Optional but helpful

    // ✅ Constructors
    public Patient() {}

    public Patient(String name, int age, String gender, String disease, String email) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
        this.email = email;
    }

    // ✅ Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public int getAge() {
        return age;
    }

    public void setAge(int age) { this.age = age; }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) { this.gender = gender; }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) { this.disease = disease; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }
}

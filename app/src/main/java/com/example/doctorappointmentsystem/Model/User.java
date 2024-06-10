package com.example.doctorappointmentsystem.Model;

public class User {
    private String email;
    private String role;

    // Required default constructor for Firebase
    public User() {
    }

    // Constructor to initialize User object
    public User(String email, String role) {
        this.email = email;
        this.role = role;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

package com.example.doctorappointmentsystem.Patient;

public class DocProfile {
    private String details;
    private String charges;
    private String specialization;
    private String workingHours;

    public DocProfile() {
        // Default constructor required for Firebase
    }

    public DocProfile(String details, String charges, String specialization, String workingHours) {
        this.details = details;
        this.charges = charges;
        this.specialization = specialization;
        this.workingHours = workingHours;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }
}

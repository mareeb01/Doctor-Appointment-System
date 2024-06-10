package com.example.doctorappointmentsystem.Patient;

public class VisibleDoctorDetails {

        private String name;
        private String email;
        private String docId;
        private String role;
        private String specialization;
        private String workingHours;
        private String description;
        private int charges;
        // You can also use a List<String> for appointments if needed

        public VisibleDoctorDetails(String name, String email, String docId, String role, String specialization) {
            this.name = name;
            this.email = email;
            this.docId = docId;
            this.role = role;
            this.specialization = specialization;
            this.workingHours = workingHours;
            this.description = description;
            this.charges = charges;
        }

        public VisibleDoctorDetails(String details, String charges, String specialization, String workingHours) {

            int c = Integer.parseInt(charges);

            this.name = null;
            this.email = null;
            this.docId = null;
            this.role = null;
            this.specialization = specialization;
            this.workingHours = workingHours;
            this.description = description;
            this.charges = c;


        }

        // Getters and setters (optional)

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDocId() {
            return docId;
        }

        public void setDocId(String docId) {
            this.docId = docId;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getCharges() {
            return charges;
        }

        public void setCharges(int charges) {
            this.charges = charges;
        }
    }
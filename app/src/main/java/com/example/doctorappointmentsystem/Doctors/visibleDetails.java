package com.example.doctorappointmentsystem.Doctors;

public class visibleDetails {
    private String d_name;
    private String d_charges;
    private String d_specialization;
    private String d_workingHourS;
    private String d_about;

    public visibleDetails(String d_name, String d_charges, String d_specialization, String d_workingHourS, String d_about) {
        this.d_name = d_name;
        this.d_charges = d_charges;
        this.d_specialization = d_specialization;
        this.d_workingHourS = d_workingHourS;
        this.d_about = d_about;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getD_charges() {
        return d_charges;
    }

    public void setD_charges(String d_charges) {
        this.d_charges = d_charges;
    }

    public String getD_specialization() {
        return d_specialization;
    }

    public void setD_specialization(String d_specialization) {
        this.d_specialization = d_specialization;
    }

    public String getD_workingHourS() {
        return d_workingHourS;
    }

    public void setD_workingHourS(String d_workingHourS) {
        this.d_workingHourS = d_workingHourS;
    }

    public String getD_about() {
        return d_about;
    }

    public void setD_about(String d_about) {
        this.d_about = d_about;
    }
}

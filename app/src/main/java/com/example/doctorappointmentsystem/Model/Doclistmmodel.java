package com.example.doctorappointmentsystem.Model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.doctorappointmentsystem.Authentication.Pod;
import com.example.doctorappointmentsystem.R;

public class Doclistmmodel {

    String name,specialization,email,id;

    public Doclistmmodel() {
    }

    public Doclistmmodel(String name, String specialization, String email,String id) {
        this.name = name;
        this.specialization = specialization;
        this.email = email;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class MainActivity extends Activity {

        // Splash screen timer
        private static int SPLASH_TIME_OUT = 1000; // 2 seconds

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            new Handler().postDelayed(new Runnable() {

                /*
                 * Showing splash screen with a timer. This will be useful when you
                 * want to show case your app logo / company
                 */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent i = new Intent(MainActivity.this, Pod.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }
    }

    // Create a Doctor class to hold doctor information
    public static class Doctor {
        String email;
        String password;
        String name;
        String about;
        String charges;
        String specialization;
        String workingHourStart;
        String workingHourEnd;
        String address;
        String id;

        public Doctor(String email, String password, String name, String about, String charges, String specialization, String workingHourStart, String workingHourEnd, String address, String id) {
            this.email = email;
            this.password = password;
            this.name = name;
            this.about = about;
            this.charges = charges;
            this.specialization = specialization;
            this.workingHourStart = workingHourStart;
            this.workingHourEnd = workingHourEnd;
            this.address = address;
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
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

        public String getWorkingHourStart() {
            return workingHourStart;
        }

        public void setWorkingHourStart(String workingHourStart) {
            this.workingHourStart = workingHourStart;
        }

        public String getWorkingHourEnd() {
            return workingHourEnd;
        }

        public void setWorkingHourEnd(String workingHourEnd) {
            this.workingHourEnd = workingHourEnd;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        // Getters and setters (optional)
    }
}

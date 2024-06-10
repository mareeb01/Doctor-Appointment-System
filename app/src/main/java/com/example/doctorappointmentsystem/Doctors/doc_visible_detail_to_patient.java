package com.example.doctorappointmentsystem.Doctors;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorappointmentsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class doc_visible_detail_to_patient extends AppCompatActivity {

    private EditText etDetails, etCharges, etSpecialization, etWorkingHours, etName;
    private DatabaseReference mDatabase;
    private Button savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_visible_detail_to_patient);

        // Initialize Firebase connection and get reference for logged-in doctor
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser != null) {
                mDatabase = database.getReference("Doctors/" + currentUser.getUid());
            } else {
                // Handle case where user is not logged in (e.g., show error message)
                Toast.makeText(this, "Please log in to save details", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error connecting to Firebase", Toast.LENGTH_SHORT).show();
            return;
        }

        // Find EditText views by their IDs
        etDetails = findViewById(R.id.et_details);
        etName = findViewById(R.id.et_name);
        etCharges = findViewById(R.id.et_charges);
        etSpecialization = findViewById(R.id.et_specialization);
        etWorkingHours = findViewById(R.id.et_working_hours);

        savebtn = findViewById(R.id.btn_save);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDoctorDetails();
            }
        });
    }

    private void saveDoctorDetails() {
        String name = etName.getText().toString().trim();
        String details = etDetails.getText().toString().trim();
        String charges = etCharges.getText().toString().trim();
        String specialization = etSpecialization.getText().toString().trim();
        String workingHours = etWorkingHours.getText().toString().trim();

        if (name.isEmpty() || details.isEmpty() || charges.isEmpty() || specialization.isEmpty() || workingHours.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.i("value", "Name: " + name);
        Log.i("value", "Details: " + details);
        Log.i("value", "Charges: " + charges);
        Log.i("value", "Specialization: " + specialization);
        Log.i("value", "Working Hours: " + workingHours);

        if (mDatabase != null) {
            mDatabase.child("d_name").setValue(name);
            mDatabase.child("d_about").setValue(details);
            mDatabase.child("d_charges").setValue(charges);
            mDatabase.child("d_specialization").setValue(specialization);
            mDatabase.child("d_workingHourS").setValue(workingHours);
            Toast.makeText(getApplicationContext(), "Details updated", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Database reference is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void doc_profile(View view) {
        startActivity(new Intent(this, doc_profile.class));
    }

    public void doc_dashboard(View view) {
        startActivity(new Intent(this, doc_homescreen.class));
    }
}

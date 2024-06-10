package com.example.doctorappointmentsystem.Patient;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorappointmentsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class doctor_profile extends AppCompatActivity {

    TextView docname,docabout;
    private FirebaseAuth mAuth;
    private DatabaseReference doctorsRef;
    private TextView textViewDetails, textViewCharges, textViewSpecialization, textViewWorkingHours,locat;
    String email,doctorid;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        docname = findViewById(R.id.docname);
        docabout = findViewById(R.id.docabout);
        locat = findViewById(R.id.locat);

        //Receive Data from first page of papular items
        Intent intent = getIntent();
        String data1 = intent.getStringExtra("name");
        docname.setText(data1);
        String data2 = intent.getStringExtra("specialization");
        docabout.setText(data2);
        String data3 = intent.getStringExtra("email");
        email = data3;
        String data4 = intent.getStringExtra("docid");
        doctorid = data4;
//        docabout.setText(data2);
//        Toast.makeText(this, "adfadasdf"+data3, Toast.LENGTH_SHORT).show();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            String userId = currentUser.getUid();
            doctorsRef = FirebaseDatabase.getInstance().getReference().child("Doctors").child(doctorid);
        }

        textViewDetails = findViewById(R.id.tv_details);
        textViewCharges = findViewById(R.id.tv_charges);
        textViewSpecialization = findViewById(R.id.tv_specialization);
        textViewWorkingHours = findViewById(R.id.tv_working_hours);

        // Load doctor information from Firebase
        loadDoctorInformation();
    }

    private void loadDoctorInformation() {
        doctorsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Map<String, Object> doctorData = (Map<String, Object>) dataSnapshot.getValue();
                    if (doctorData != null) {
                        String details = (String) doctorData.get("d_about");
                        String charges = (String) doctorData.get("d_charges");
                        String specialization = (String) doctorData.get("d_specialization");
                        String workingHours = (String) doctorData.get("d_workingHourS");
                        String address = (String) doctorData.get("d_address");
                        doctorid = (String) doctorData.get("d_id");

                        // Display doctor information
                        textViewDetails.setText(details);
                        textViewCharges.setText(charges);
                        textViewSpecialization.setText(specialization);
                        textViewWorkingHours.setText(workingHours);
                        locat.setText(address);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    public void book_to_patient_detail(View view){
        Intent intent = new Intent(doctor_profile.this, book_appointment.class);
        intent.putExtra("doctorid",doctorid);
        startActivity(intent);
    }
}







//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class doctor_profile extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_doctor_profile);
//    }
//    public void book_to_patient_detail(View view){
//
//        startActivity(new Intent(this, book_appointment.class));
//
//
//    }
//
//}
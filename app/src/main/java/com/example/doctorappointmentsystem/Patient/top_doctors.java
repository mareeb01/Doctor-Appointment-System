package com.example.doctorappointmentsystem.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorappointmentsystem.Patient.doctor_profile;
import com.example.doctorappointmentsystem.Patient.home_screen;
import com.example.doctorappointmentsystem.R;

public class top_doctors extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_doctors);



    }
    public void btn_home(View view){

        startActivity(new Intent(this, home_screen.class));
    }

    public void doc_profile(View view){

        startActivity(new Intent(this, doctor_profile.class));
    }
}
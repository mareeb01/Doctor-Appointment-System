package com.example.doctorappointmentsystem.Authentication;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorappointmentsystem.Patient.home_screen;
import com.example.doctorappointmentsystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Plogin extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword;
    Button buttonLogin;
    ProgressBar progressBar;
    TextView textView;

    FirebaseAuth mAuth;

    TextView tv_forgotPassword;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), home_screen.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plogin);

        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.et_email);
        editTextPassword = findViewById(R.id.et_password);
        buttonLogin = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.registerNow);
        tv_forgotPassword = findViewById(R.id.tv_forgotPassword);

        tv_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Plogin.this, PatientResetPassword.class));
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), psignup.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Plogin.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Plogin.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Check if the signed-in user is a patient
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null) {
                                        String userId = user.getUid();
                                        DatabaseReference patientRef = FirebaseDatabase.getInstance().getReference().child("Patients").child(userId);
                                        patientRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()) {
                                                    // User is a patient
                                                    Toast.makeText(getApplicationContext(),"Login Successfull", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(getApplicationContext(), home_screen.class);
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    // User is not a patient, display error message
                                                    Toast.makeText(Plogin.this, "Only patients can login through this form.", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                // Handle error
                                            }
                                        });
                                    }
                                } else {
                                    Toast.makeText(Plogin.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}














//
//public class Plogin extends AppCompatActivity {
//    TextInputEditText editTextEmail, editTextPassword;
//    Button buttonLogin;
//    ProgressBar progressBar;
//    TextView textView;
//
//    FirebaseAuth mAuth;
//
//    TextView tv_forgotPassword;
//
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//
//            Intent intent = new Intent(getApplicationContext(), home_screen.class);
//            startActivity(intent);
//            finish();
//        }
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_plogin);
//
//        mAuth = FirebaseAuth.getInstance();
//        editTextEmail = findViewById(R.id.et_email);
//        editTextPassword = findViewById(R.id.et_password);
//        buttonLogin = findViewById(R.id.btn_login);
//        progressBar = findViewById(R.id.progressBar);
//        textView = findViewById(R.id.registerNow);
//        tv_forgotPassword = findViewById(R.id.tv_forgotPassword);
//
//        tv_forgotPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Plogin.this, PatientResetPassword.class));
//            }
//        });
//
//
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), psignup.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                progressBar.setVisibility(View.VISIBLE);
//
//                String email;
//                String password;
//                email = String.valueOf(editTextEmail.getText());
//                password = String.valueOf(editTextPassword.getText());
//
//                if (TextUtils.isEmpty(email)){
//
//                    Toast.makeText(Plogin.this, "Enter Email", Toast.LENGTH_SHORT).show();
//                    return;
//
//                }
//
//                if (TextUtils.isEmpty(password)){
//
//                    Toast.makeText(Plogin.this, "Enter Password", Toast.LENGTH_SHORT).show();
//                    return;
//
//                }
//
//                mAuth.signInWithEmailAndPassword(email, password)
//                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                progressBar.setVisibility(View.GONE);
//                                if (task.isSuccessful()) {
//                                   Toast.makeText(getApplicationContext(),"Login Successfull", Toast.LENGTH_SHORT).show();
//                                   Intent intent = new Intent(getApplicationContext(), home_screen.class);
//                                   startActivity(intent);
//                                   finish();
//
//                                } else {
//                                    Toast.makeText(Plogin.this, "Authentication failed.",
//                                            Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//
//
//            }
//
//        });
//
//
//
//    }
//}
package com.example.doctorappointmentsystem.Doctors;

import android.annotation.SuppressLint;
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

import com.example.doctorappointmentsystem.Authentication.PatientResetPassword;
import com.example.doctorappointmentsystem.Authentication.Plogin;
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

public class doc_login extends AppCompatActivity {
    TextInputEditText deditTextEmail, deditTextPassword;
    Button dbuttonLogin,doc_signup;
    ProgressBar progressBar;

    FirebaseAuth dmAuth;
    TextView tv_forgotPassword;



    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = dmAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), doc_homescreen.class);
            startActivity(intent);
            finish();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_login);

        dmAuth = FirebaseAuth.getInstance();
        deditTextEmail = findViewById(R.id.et_doc_email);
        deditTextPassword = findViewById(R.id.et_doc_password);
        dbuttonLogin = findViewById(R.id.btn_doc_login);
        doc_signup = findViewById(R.id.doc_signup);
        progressBar = findViewById(R.id.progressBar);
        tv_forgotPassword = findViewById(R.id.tv_forgotPassword);

        tv_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(doc_login.this, PatientResetPassword.class));
            }
        });


        doc_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(doc_login.this, doc_signup.class);
                startActivity(intent);
            }
        });

        dbuttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email = deditTextEmail.getText().toString().trim();
                String password = deditTextPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(doc_login.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(doc_login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                dmAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Check if the signed-in user is a doctor
                                    FirebaseUser user = dmAuth.getCurrentUser();
                                    if (user != null) {
                                        String userId = user.getUid();
                                        DatabaseReference doctorRef = FirebaseDatabase.getInstance().getReference().child("Doctors").child(userId);
                                        doctorRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()) {
                                                    // User is a doctor
                                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(getApplicationContext(), doc_homescreen.class);
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    // User is not a doctor, display error message
                                                    Toast.makeText(getApplicationContext(), "Only doctors can login through this form.", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                // Handle error (e.g., display toast message)
                                                Toast.makeText(getApplicationContext(), "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        doc_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(doc_login.this, doc_signup.class);
                startActivity(intent);
            }
        });

    }

}



























//package com.example.doctorappointmentsystem;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//public class doc_login extends AppCompatActivity {
//    TextInputEditText deditTextEmail, deditTextPassword;
//    Button dbuttonLogin;
//    ProgressBar progressBar;
//
//    FirebaseAuth dmAuth;
//
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = dmAuth.getCurrentUser();
//        if(currentUser != null){
//
//            Intent intent = new Intent(getApplicationContext(), doc_homescreen.class);
//            startActivity(intent);
//            finish();
//        }
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_doc_login);
//
//        dmAuth = FirebaseAuth.getInstance();
//        deditTextEmail = findViewById(R.id.et_doc_email);
//        deditTextPassword = findViewById(R.id.et_doc_password);
//        dbuttonLogin = findViewById(R.id.btn_doc_login);
//        progressBar = findViewById(R.id.progressBar);
//
//
//
//        dbuttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                progressBar.setVisibility(View.VISIBLE);
//
//                String email;
//                String password;
//                email = String.valueOf(deditTextEmail.getText());
//                password = String.valueOf(deditTextPassword.getText());
//
//                if (TextUtils.isEmpty(email)){
//
//                    Toast.makeText(doc_login.this, "Enter Email", Toast.LENGTH_SHORT).show();
//                    return;
//
//                }
//
//                if (TextUtils.isEmpty(password)){
//
//                    Toast.makeText(doc_login.this, "Enter Password", Toast.LENGTH_SHORT).show();
//                    return;
//
//                }
//
//                dmAuth.signInWithEmailAndPassword(email, password)
//                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                progressBar.setVisibility(View.GONE);
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(getApplicationContext(),"Login Successfull", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(getApplicationContext(), doc_homescreen.class);
//                                    startActivity(intent);
//                                    finish();
//
//                                } else {
//                                    Toast.makeText(doc_login.this, "Authentication failed.",
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
//    public void doc_signup(View view) {
//
//        startActivity(new Intent(this, doc_signup.class));
//
//    }
//
//
//}
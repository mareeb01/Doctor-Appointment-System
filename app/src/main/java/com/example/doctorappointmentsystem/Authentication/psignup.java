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
import com.example.doctorappointmentsystem.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class psignup extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword;
    Button buttonReg;
    ProgressBar progressBar;
    TextView textView;

    FirebaseAuth mAuth;

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
        setContentView(R.layout.activity_psignup);

        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.et_email);
        editTextPassword = findViewById(R.id.et_password);
        buttonReg = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginNow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Plogin.class);
                startActivity(intent);
                finish();
            }
        });

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(psignup.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(psignup.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()){
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null) {
                                        String userId = user.getUid();
                                        String role = "Patient"; // Set role as "patient" for now, you can change this later

                                        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child(role + "s");

                                        // Create a new user object with email and role
                                        User newUser = new User(email, role);

                                        // Store user data in the database
                                        usersRef.child(userId).setValue(newUser)
                                                .addOnSuccessListener(aVoid -> {
                                                    // Registration success
                                                    Toast.makeText(psignup.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                                    // Redirect user to appropriate activity (doctor/patient dashboard)
                                                })
                                                .addOnFailureListener(e -> {
                                                    // Registration failed
                                                    Toast.makeText(psignup.this, "Registration failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                });
                                    }
                                } else {
                                    // Registration failed
                                    Toast.makeText(psignup.this, "Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
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
//import android.widget.TextView;
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
//public class psignup extends AppCompatActivity {
//    TextInputEditText editTextEmail, editTextPassword;
//    Button buttonReg;
//    ProgressBar progressBar;
//    TextView textView;
//
//    FirebaseAuth mAuth;
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
//        setContentView(R.layout.activity_psignup);
//
//        mAuth = FirebaseAuth.getInstance();
//        editTextEmail = findViewById(R.id.et_email);
//        editTextPassword = findViewById(R.id.et_password);
//        buttonReg = findViewById(R.id.btn_register);
//        progressBar = findViewById(R.id.progressBar);
//        textView = findViewById(R.id.loginNow);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), Plogin.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        buttonReg.setOnClickListener(new View.OnClickListener() {
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
//                    Toast.makeText(psignup.this, "Enter Email", Toast.LENGTH_SHORT).show();
//                    return;
//
//                }
//
//                if (TextUtils.isEmpty(password)){
//
//                    Toast.makeText(psignup.this, "Enter Password", Toast.LENGTH_SHORT).show();
//                    return;
//
//                }
//
//
//                mAuth.createUserWithEmailAndPassword(email, password)
//                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                progressBar.setVisibility(View.GONE);
//
//                                if (task.isSuccessful()){
//
//
//                                    Toast.makeText(psignup.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
//
//
//                                }
//                                else {
//                                    Toast.makeText(psignup.this, "Authentication FAiled", Toast.LENGTH_SHORT).show();
//
//                                }
//                            }
//                        });
//
//
//
//            }
//        });
//
//    }
//}
package com.example.doctorappointmentsystem.Doctors;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorappointmentsystem.Model.Doclistmmodel;
import com.example.doctorappointmentsystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.Timer;
import java.util.TimerTask;

public class doc_signup extends AppCompatActivity {
    private EditText emailEditText, passwordEditText, nameEditText, aboutEditText, charges, specialization, workingHourS, workingHourE, address;
    private Button registerButton;
    private ProgressBar progressBar;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference doctorReference;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    String current_userID;
    String chargesEditText, specializationEditText, workingHourStartEditText, workingHourEndEditText, addressEditText;

    private DatabaseReference storeDefaultDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_signup); // Replace R.layout.doc_signup with your actual layout file name

        // Initialize UI elements
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        nameEditText = findViewById(R.id.name);
        aboutEditText = findViewById(R.id.about);
        charges= findViewById(R.id.charges);
        specialization = findViewById(R.id.specialization);
        workingHourS = findViewById(R.id.workingHourS);
        workingHourE = findViewById(R.id.workingHourE);
        address = findViewById(R.id.address);
        registerButton = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressBar);



        // Initialize Firebase Database connection

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


        firebaseDatabase = FirebaseDatabase.getInstance();
        doctorReference = firebaseDatabase.getReference("Doctors");

        // Register button click listener
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String dname = nameEditText.getText().toString();
                final String demail = emailEditText.getText().toString();
                final String dabout = aboutEditText.getText().toString();
                chargesEditText = charges.getText().toString();
                specializationEditText = specialization.getText().toString();
                workingHourStartEditText = workingHourS.getText().toString();
                workingHourEndEditText = workingHourE.getText().toString();
                addressEditText = address.getText().toString();
                String password = passwordEditText.getText().toString();

                // pass input parameter through this Method
                registerAccount(dname, demail, dabout, password,chargesEditText,specializationEditText,workingHourStartEditText,workingHourEndEditText,addressEditText);
            }
        });
        progressDialog = new ProgressDialog(doc_signup.this);

    }


    private void registerAccount(final String dname, final String demail, final String dabout, String password, String chargesEditText, String specializationEditText, String workingHourStartEditText, String workingHourEndEditText, String addressEditText) {

        //Validation for empty fields
        if (TextUtils.isEmpty(dname)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
        } else if (dname.length() < 3 || dname.length() > 40){
            Toast.makeText(this, "Your name should be 3 to 40 numbers of characters.", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(demail)){
            Toast.makeText(this, "Your email is required.", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(demail).matches()){
            Toast.makeText(this, "Your email is not valid.", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(dabout)){
            Toast.makeText(this, "Your mobile number is required.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please fill this password field", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6){
            Toast.makeText(this, "Create a password at least 6 characters long.", Toast.LENGTH_SHORT).show();
        } else {
            //NOw ready to create a user a/c
            mAuth.createUserWithEmailAndPassword(demail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
//                                String deviceToken = FirebaseInstanceId.getInstance().getToken();

                        // get and link storage
                        String current_userID =  mAuth.getCurrentUser().getUid();
                        storeDefaultDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Doctors").child(current_userID);

                        storeDefaultDatabaseReference.child("d_name").setValue(dname);
                        storeDefaultDatabaseReference.child("verified").setValue("false");
                        storeDefaultDatabaseReference.child("search_name").setValue(dname.toLowerCase());
                        storeDefaultDatabaseReference.child("d_about").setValue(dabout);
                        storeDefaultDatabaseReference.child("d_email").setValue(demail);
                        storeDefaultDatabaseReference.child("d_charges").setValue(chargesEditText);
                        storeDefaultDatabaseReference.child("d_specialization").setValue(specializationEditText);
                        storeDefaultDatabaseReference.child("d_workingHourS").setValue(workingHourStartEditText);
                        storeDefaultDatabaseReference.child("d_workingHourE").setValue(workingHourEndEditText);
                        storeDefaultDatabaseReference.child("created_at").setValue(ServerValue.TIMESTAMP);
                        storeDefaultDatabaseReference.child("d_address").setValue(addressEditText);
                        storeDefaultDatabaseReference.child("d_id").setValue(current_userID);
                        storeDefaultDatabaseReference.child("user_image").setValue("default_image")
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            // SENDING VERIFICATION EMAIL TO THE REGISTERED USER'S EMAIL
                                            user = mAuth.getCurrentUser();
                                            if (user != null){
                                                user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()){

                                                            registerSuccessPopUp();

                                                            // LAUNCH activity after certain time period
                                                            new Timer().schedule(new TimerTask(){
                                                                public void run() {
                                                                    doc_signup.this.runOnUiThread(new Runnable() {
                                                                        public void run() {
                                                                            mAuth.signOut();

                                                                            Intent mainIntent = new Intent(doc_signup.this, doc_login.class);
                                                                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                                            startActivity(mainIntent);
                                                                            finish();

                                                                            Toast.makeText(doc_signup.this, "Please check your email & verify.", Toast.LENGTH_SHORT).show();

                                                                        }
                                                                    });
                                                                }
                                                            }, 4000);


                                                        } else {
                                                            mAuth.signOut();
                                                        }
                                                    }
                                                });
                                            }

                                        }
                                    }
                                });

                    } else {
                        String message = task.getException().getMessage();
                        Toast.makeText(doc_signup.this, "Email Already Exist :-", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }
            });

            //config progressbar
            progressDialog.setTitle("Creating new account");
            progressDialog.setMessage("Please wait a moment....");
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);


        }

    }


    private void registerSuccessPopUp() {
        // Custom Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(doc_signup.this);
        View view = LayoutInflater.from(doc_signup.this).inflate(R.layout.register_success_popup, null);

//        ImageButton imageButton = view.findViewById(R.id.successIcon);
//        imageButton.setImageResource(R.drawable.logout);
        builder.setCancelable(false);

        builder.setView(view);
        builder.show();
    }
}



/*
public class doc_signup extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword;
    Button buttonReg;
    ProgressBar progressBar;
    TextView textView;

    FirebaseAuth mAuth;
//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            Intent intent = new Intent(getApplicationContext(), home_screen.class);
//            startActivity(intent);
//            finish();
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_signup);
        Log.d("TAG", "Entry: ");
/*
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
                    Toast.makeText(doc_signup.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(doc_signup.this, "Enter Password", Toast.LENGTH_SHORT).show();
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
                                        String role = "Doctor"; // Set role as "patient" for now, you can change this later

                                        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child(role + "s");

                                        // Create a new user object with email and role
                                        User newUser = new User(email, role);

                                        // Store user data in the database
                                        usersRef.child(userId).setValue(newUser)
                                                .addOnSuccessListener(aVoid -> {
                                                    // Registration success
                                                    Toast.makeText(doc_signup.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                                    // Redirect user to appropriate activity (doctor/patient dashboard)
                                                })
                                                .addOnFailureListener(e -> {
                                                    // Registration failed
                                                    Toast.makeText(doc_signup.this, "Registration failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                });
                                    }
                                } else {
                                    // Registration failed
                                    Toast.makeText(doc_signup.this, "Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        //
    }

    public void doc_signin(View view) {

        startActivity(new Intent(this, doc_login.class));
    }
}




*/

















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
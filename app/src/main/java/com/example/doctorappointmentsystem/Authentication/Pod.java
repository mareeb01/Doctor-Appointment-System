package com.example.doctorappointmentsystem.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorappointmentsystem.Doctors.doc_homescreen;
import com.example.doctorappointmentsystem.Doctors.doc_login;
import com.example.doctorappointmentsystem.Model.User;
import com.example.doctorappointmentsystem.Patient.home_screen;
import com.example.doctorappointmentsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//public class Pod extends AppCompatActivity {
//
//    FirebaseAuth auth = FirebaseAuth.getInstance();
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        FirebaseUser user = auth.getCurrentUser();
//        String ID = user.getUid();
//        DatabaseReference patientsRef = database.getReference("Patients");
//        patientsRef.child(ID).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    // User is a Patient
//                    Intent intent = new Intent(getApplicationContext(), home_screen.class );
//                    startActivity(intent);
//                    finish();
//                } else {
//                    // Check doctors node (optional, see approach 2 for alternative)
//                    Intent intent = new Intent(getApplicationContext(), doc_homescreen.class );
//                    startActivity(intent);
//                    finish();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // Handle database errors
//            }
//        });
//
////        if(user == null) {
//            setContentView(R.layout.activity_pod);
//
////        }
//    }
//
//    public void btn_patient(View view){
//
//        startActivity(new Intent(this, Plogin.class));
//    }
//
//    public void btn_doctor(View view){
//
//        startActivity(new Intent(this, doc_login.class));
//    }
//}
//
//
public class Pod extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseUser user = auth.getCurrentUser();

        // Check if user is logged in before setting content view
        if (user != null) {
            checkUserType(user.getUid()); // Move user type check here
        } else {
            setContentView(R.layout.activity_pod);

            // User not logged in, handle accordingly (e.g., show login screen)
            // You can display a different layout or redirect to a login activity
        }
    }

    private void checkUserType(String userId) {
        DatabaseReference patientsRef = database.getReference("Patients");
        patientsRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // User is a Patient
                    Intent intent = new Intent(getApplicationContext(), home_screen.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Check doctors node (optional, see approach 2 for alternative)
                    Intent intent = new Intent(getApplicationContext(), doc_homescreen.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database errors
            }
        });
    }

    public void btn_patient(View view) {
        startActivity(new Intent(this, Plogin.class));
    }

    public void btn_doctor(View view) {
        startActivity(new Intent(this, doc_login.class));
    }
}

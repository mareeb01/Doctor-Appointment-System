package com.example.doctorappointmentsystem.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorappointmentsystem.Authentication.Plogin;
import com.example.doctorappointmentsystem.Authentication.Pod;
import com.example.doctorappointmentsystem.R;
import com.example.doctorappointmentsystem.contact;
import com.example.doctorappointmentsystem.help_screen;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;


public class Profile extends AppCompatActivity {

    TextView greetingTextView;
    FirebaseAuth auth;
    Button button;
    ImageView doc_btn;




    View image;
//    TextView textView;
//    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        image = findViewById(R.id.logout);
        auth = FirebaseAuth.getInstance();
        doc_btn = (ImageView) findViewById(R.id.doc_btn);


        boolean user = false;

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(getApplicationContext(), Plogin.class);
//                startActivity(intent);
                Intent intent = new Intent(getApplicationContext(), Pod.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }


        });

//        auth = FirebaseAuth.getInstance();
//        button = findViewById(R.id.logout);
//        textView = findViewById(R.id.user_detail);
//        user = auth.getCurrentUser();
//
//        if (user == null){
//
//            Intent intent = new Intent(getApplicationContext(), Plogin.class);
//            startActivity(intent);
//            finish();
//        }
//        else {
//
//            textView.setText(user.getEmail());
//        }




//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(getApplicationContext(), Plogin.class);
//                startActivity(intent);
//                finish();
//
//            }
//        });  if (user == null){
//
//            Intent intent = new Intent(getApplicationContext(), Plogin.class);
//            startActivity(intent);
//            finish();
//        }
//        else {
//
//            textView.setText(user.getEmail());
//        }
//


//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(getApplicationContext(), Plogin.class);
//                startActivity(intent);
//                finish();
//
//            }
//        });


        greetingTextView = findViewById(R.id.greetingTextView);

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if (hour >= 6 && hour < 12) {
            // Morning (6am to 11:59am)
            greetingTextView.setText("Good Morning");
        } else if (hour >= 12 && hour < 18) {
            // Afternoon (12pm to 5:59pm)
            greetingTextView.setText("Good Afternoon");
        } else {
            // Evening (6pm to 5:59am)
            greetingTextView.setText("Good Evening");
        }

        doc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), all_doc.class);
                startActivity(intent);
            }
        });
    }


    public void contact(View view) {
        startActivity(new Intent(getApplicationContext(), contact.class));
    }

    public void help(View view) {
        startActivity(new Intent(getApplicationContext(), help_screen.class));
    }

    public void btn_home(View view){

        startActivity(new Intent(this, home_screen.class));
    }

    public void btn_appointment(View view){

        startActivity(new Intent(this, bookedappointment.class));
    }
    public void btn_profile(View view){

        startActivity(new Intent(this, Profile.class));
    }
}

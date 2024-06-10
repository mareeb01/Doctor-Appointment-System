package com.example.doctorappointmentsystem.Doctors;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorappointmentsystem.Authentication.Pod;
import com.example.doctorappointmentsystem.R;
import com.example.doctorappointmentsystem.contact;
import com.example.doctorappointmentsystem.help_screen;
import com.google.firebase.auth.FirebaseAuth;

public class doc_profile extends AppCompatActivity {

    FirebaseAuth auth;
    View image;

    TextView greeting;
    TextView name;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile);

        Intent intent = getIntent();
        greeting = (TextView) findViewById(R.id.g);
        name = (TextView)findViewById(R.id.n);
        email = (TextView)findViewById(R.id.e);
        Bundle extra = intent.getExtras();

        Log.d("value", "\n\n" + intent.getStringExtra("name"));
        Log.d("value", "\n\n" + intent.getStringExtra("email"));
        Log.d("value", "\n\n" + intent.getStringExtra("greeting"));

        name.setText(intent.getStringExtra("name"));
        email.setText(intent.getStringExtra("email"));
        greeting.setText(intent.getStringExtra("greeting"));

        if(name == null) {
            Log.i("value", "is null1");
        }
        if(greeting == null) {
            Log.i("value", "is null2");
        }
        if(email == null) {
            Log.i("value", "is null3");
        }



        auth = FirebaseAuth.getInstance();
        image = findViewById(R.id.logout);


        boolean user = false;

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(getApplicationContext(), Pod.class);
//                startActivity(intent);
                Intent intent = new Intent(getApplicationContext(), Pod.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                finish();

            }
        });


    }

    public void doc_profile(View view){

        startActivity(new Intent(this, doc_profile.class));
    }

    public void doc_dashboard(View view){

        startActivity(new Intent(this, doc_homescreen.class));
    }
    public void doc_visible_detail(View view){

        startActivity(new Intent(this, doc_visible_detail_to_patient.class));
    }
    public void btn_appointment(View view){

        startActivity(new Intent(this, doc_all_patients.class));
    }

    public void help(View view){

        startActivity(new Intent(this, help_screen.class));
    }

    public void contact_us(View view){

        startActivity(new Intent(this, contact.class));
    }


}
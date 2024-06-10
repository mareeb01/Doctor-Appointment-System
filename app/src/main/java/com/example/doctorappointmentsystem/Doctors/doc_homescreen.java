package com.example.doctorappointmentsystem.Doctors;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorappointmentsystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class doc_homescreen extends AppCompatActivity {

    TextView greetingTextView;

    private String name;
    private String email;
    private String greeting;
    private TextView dateTextView;


    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    String UID = auth.getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_homescreen);

        dateTextView = findViewById(R.id.date); // Get reference to the TextView

        // Get the current date
        LocalDate currentDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
        }

        // Format the date in the desired format (e.g., YYYY-MM-DD)
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        }
        String formattedDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formattedDate = currentDate.format(formatter);
        }

        // Set the formatted date on the TextView
        dateTextView.setText(formattedDate);


        greetingTextView = findViewById(R.id.greetingTextView);

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if (hour >= 6 && hour < 12) {
            // Morning (6am to 11:59am)
            greetingTextView.setText("Good Morning");
            greeting = "Good Morning";
        } else if (hour >= 12 && hour < 18) {
            // Afternoon (12pm to 5:59pm)
            greetingTextView.setText("Good Afternoon");
            greeting = "Good Afternoon";

        } else {
            // Evening (6pm to 5:59am)
            greetingTextView.setText("Good Evening");
            greeting = "Good Evening";

        }

        db.child("Doctors").child(UID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()) {
                    greetingTextView.setText(greetingTextView.getText() + " " + task.getResult().child("d_name").getValue());
                    name = task.getResult().child("d_name").getValue().toString();
                    email = task.getResult().child("d_email").getValue().toString();
                }
            }
        });
    }

    public void doc_dashboard(View view){

        startActivity(new Intent(this, doc_homescreen.class));
    }

    public void doc_profile(View view){
        Intent intent = new Intent(this, doc_profile.class);
        intent.putExtra("name", name);
        intent.putExtra("email", email);
        intent.putExtra("greeting", greeting);
        startActivity(intent);
    }
    public void btn_appointment(View view){

        startActivity(new Intent(this, doc_all_patients.class));
    }


}
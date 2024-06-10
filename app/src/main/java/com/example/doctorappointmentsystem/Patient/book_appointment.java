package com.example.doctorappointmentsystem.Patient;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorappointmentsystem.Model.Placeorder;
import com.example.doctorappointmentsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class book_appointment extends AppCompatActivity {

    Button book_now;
    EditText pname,page,pphone,pproblem;
    TextView pdate;
    DatabaseReference getUserDatabaseReference;
    private FirebaseAuth mAuth;
    ImageView img;
    String orderId,userId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        book_now = findViewById(R.id.book_now);
        pname = findViewById(R.id.pname);
        page = findViewById(R.id.page);
        pphone = findViewById(R.id.pphone);
        pdate = findViewById(R.id.pdate);
        pproblem = findViewById(R.id.pproblem);
        img = findViewById(R.id.pimg);

        pdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog();
            }
        });

        Intent intent = getIntent();
        String doctorid = intent.getStringExtra("doctorid");

//        Toast.makeText(this, ""+doctorid, Toast.LENGTH_SHORT).show();

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        getUserDatabaseReference = database.getReference("Appointments").child(doctorid);
        orderId = getUserDatabaseReference.push().getKey();



        book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pname.getText().toString().isEmpty()){
                    Toast.makeText(book_appointment.this, "Name is Required", Toast.LENGTH_SHORT).show();
                } else if (page.getText().toString().isEmpty()) {
                    Toast.makeText(book_appointment.this, "Name is Required", Toast.LENGTH_SHORT).show();
                }else if (pphone.getText().toString().isEmpty()) {
                    Toast.makeText(book_appointment.this, "Name is Required", Toast.LENGTH_SHORT).show();
                }else if (pdate.getText().toString().isEmpty()) {
                    Toast.makeText(book_appointment.this, "Name is Required", Toast.LENGTH_SHORT).show();
                }else if (pproblem.getText().toString().isEmpty()) {
                    Toast.makeText(book_appointment.this, "Name is Required", Toast.LENGTH_SHORT).show();
                }
                else {
                    String fullName = pname.getText().toString().trim();
                    String email = page.getText().toString().trim();
                    String phone = pphone.getText().toString().trim();
                    String date = pdate.getText().toString().trim();
                    String problem = pproblem.getText().toString().trim();
//                    String medicineimg = pname.getText().toString().trim();

//                    String id = userId;

                    Toast.makeText(book_appointment.this, ""+orderId, Toast.LENGTH_SHORT).show();

                    // Create an Order object
                    Placeorder order = new Placeorder(fullName,email,phone,date,problem,orderId, mAuth.getCurrentUser().getUid());

                    // Push the order to the user's orders node

                    getUserDatabaseReference.child(orderId).setValue(order);
                    getUserDatabaseReference.child(orderId).child("medicineimg").setValue("default_image");
                    getUserDatabaseReference.child(orderId).child("patientId").setValue(mAuth.getCurrentUser().getUid());
                    Toast.makeText(book_appointment.this, "Your Appointment is Successfull!", Toast.LENGTH_SHORT).show();
                    showpopup();
                    finish();
                }

            }

            private void showpopup() {
                Intent intent = new Intent(book_appointment.this, popup_congratulation.class);
                startActivity(intent);
            }
        });


    }


    private void dateDialog(){
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                pdate.setText(String.valueOf(year)+"/"+String.valueOf(month)+"/"+String.valueOf(dayOfMonth));
            }
        }, 2024, 1, 14);
        dialog.show();
    }


}
package com.example.doctorappointmentsystem.Patient;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorappointmentsystem.Adapter.Doclistadapter;
import com.example.doctorappointmentsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class bookedappointment extends AppCompatActivity implements Bookedoptlistadapter.OnNoteListener{

    RecyclerView bookedrecycler;
    ArrayList<Bookedoptlistmodel> Bookedopt_list;
    String pID;
    Bookedoptlistadapter adapter;
    DatabaseReference getUserDatabaseReference;
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookedappointment);

        bookedrecycler = findViewById(R.id.bookedrecycler);
        mAuth=FirebaseAuth.getInstance();



        Bookedopt_list = new ArrayList<>();
        getUserDatabaseReference = FirebaseDatabase.getInstance().getReference("Appointments");
        bookedrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        bookedrecycler.setHasFixedSize(true);
        adapter = new Bookedoptlistadapter(Bookedopt_list, bookedappointment.this);
        bookedrecycler.setAdapter(adapter);
        pID = mAuth.getUid();
        findAppointmentsForPatient(pID);
//        getMenData();



    }

    private void findAppointmentsForPatient(String patientID) {
        // Add a listener to read the data at our Appointments reference
        getUserDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot orderSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot order : orderSnapshot.getChildren()) {
                        String currentPatientID = order.child("patientId").getValue(String.class);



                        if (currentPatientID != null && currentPatientID.equals(pID)) {
                            String img = order.child("medicineimg").getValue(String.class);
                            String name = order.child("pname").getValue(String.class);
                            String age = order.child("page").getValue(String.class);
                            String disc = order.child("pproblem").getValue(String.class);
                            String atime = order.child("pdate").getValue(String.class);
                            String orderId = order.child("orderId").getValue(String.class);
                            String phone = order.child("pphone").getValue(String.class);



                            Bookedoptlistmodel model = new Bookedoptlistmodel( img,name,age, disc, atime,orderId,phone, pID);
                            Bookedopt_list.add(model);
                        }
                    }
                }
                adapter = new Bookedoptlistadapter(Bookedopt_list, bookedappointment.this);
                bookedrecycler.setAdapter(adapter);

                if(Bookedopt_list.isEmpty()) {
                    Toast.makeText(bookedappointment.this, "Empty", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors.
                System.err.println("Error: " + databaseError.getMessage());
            }
        });
    }

    private void getMenData() {
        try {
            getUserDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Bookedopt_list.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String img = ds.child("medicineimg").getValue(String.class);
                            String name = ds.child("pname").getValue(String.class);
                            String age = ds.child("page").getValue(String.class);
                            String disc = ds.child("pproblem").getValue(String.class);
                            String atime = ds.child("pdate").getValue(String.class);
                            String orderId = ds.child("orderId").getValue(String.class);
                            String phone = ds.child("pphone").getValue(String.class);
                            String ppid = ds.child("patientId").getValue(String.class);

                            Bookedoptlistmodel model = new Bookedoptlistmodel( img,name,age, disc, atime,orderId,phone, ppid);
                            Bookedopt_list.add(model);
                        }
//                        Toast.makeText(Orders.this, String.valueOf(Order_list.size()), Toast.LENGTH_SHORT).show();
                        adapter = new Bookedoptlistadapter(Bookedopt_list, bookedappointment.this);
                        bookedrecycler.setAdapter(adapter);
                    } else {
                        Toast.makeText(bookedappointment.this, "Empty", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle onCancelled event if needed
                }
            });
        } catch (Exception error) {
            Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onNoteClick(int position) {

        String pimg = Bookedopt_list.get(position).getPimg();
        Intent intent = new Intent(bookedappointment.this, MedicineView.class);
        intent.putExtra("pimg",pimg);
        startActivity(intent);

    }

    @Override
    public void onCreates(Bundle savedInstanceState) {

    }
}
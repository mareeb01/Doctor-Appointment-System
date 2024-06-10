package com.example.doctorappointmentsystem.Doctors;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorappointmentsystem.Patient.Bookedoptlistadapter;
import com.example.doctorappointmentsystem.Patient.Bookedoptlistmodel;
import com.example.doctorappointmentsystem.Patient.bookedappointment;
import com.example.doctorappointmentsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

// comment
public class doc_all_patients extends AppCompatActivity implements Bookedoptlistadapter.OnNoteListener{


    RecyclerView bookedrecycler;
    ArrayList<Bookedoptlistmodel> Bookedopt_list;
    Bookedoptlistadapter adapter;
    DatabaseReference getUserDatabaseReference;
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_all_patients);

        bookedrecycler = findViewById(R.id.bookedrecycler);
        mAuth=FirebaseAuth.getInstance();



        Bookedopt_list = new ArrayList<>();
        getUserDatabaseReference = FirebaseDatabase.getInstance().getReference("Appointments").child(mAuth.getUid());
        bookedrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        bookedrecycler.setHasFixedSize(true);
        adapter = new Bookedoptlistadapter(Bookedopt_list, doc_all_patients.this);
        bookedrecycler.setAdapter(adapter);
        getMenData();







    }



    private void getMenData() {
        try {
            getUserDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Bookedopt_list.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String img = ds.child("img").getValue(String.class);
                            String name = ds.child("pname").getValue(String.class);
                            String age = ds.child("page").getValue(String.class);
                            String disc = ds.child("pproblem").getValue(String.class);
                            String atime = ds.child("pdate").getValue(String.class);
                            String orderId = ds.child("orderId").getValue(String.class);
                            String phone = ds.child("pphone").getValue(String.class);
                            String patientId = ds.child("patientId").getValue(String.class);

                            Bookedoptlistmodel model = new Bookedoptlistmodel( img,name,age, disc, atime,orderId,phone, patientId);
                            Bookedopt_list.add(model);
                        }
//                        Toast.makeText(Orders.this, String.valueOf(Order_list.size()), Toast.LENGTH_SHORT).show();
                        adapter = new Bookedoptlistadapter(Bookedopt_list, doc_all_patients.this);
                        bookedrecycler.setAdapter(adapter);
                    } else {
                        Toast.makeText(doc_all_patients.this, "Empty", Toast.LENGTH_SHORT).show();
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

        String pname = Bookedopt_list.get(position).getPname();
        String page = Bookedopt_list.get(position).getPage();
        String pproblem = Bookedopt_list.get(position).getPdisc();
        String ptime = Bookedopt_list.get(position).getAtime();
        String orderId = Bookedopt_list.get(position).getOrderId();
        String phone = Bookedopt_list.get(position).getPphone();
        String pid = Bookedopt_list.get(position).getOrderId();
        String patientId = Bookedopt_list.get(position).getPatientId();
        Intent intent = new Intent(doc_all_patients.this,doc_patient_detail.class);
        intent.putExtra("pname",pname);
        intent.putExtra("page",page);
        intent.putExtra("pproblem",pproblem);
        intent.putExtra("ptime",ptime);
        intent.putExtra("orderId",orderId);
        intent.putExtra("pphone",phone);
        intent.putExtra("pid",pid);
        intent.putExtra("patientId", patientId);
        startActivity(intent);

    }

    @Override
    public void onCreates(Bundle savedInstanceState) {

    }
}
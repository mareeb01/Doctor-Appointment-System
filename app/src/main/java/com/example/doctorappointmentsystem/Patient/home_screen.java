package com.example.doctorappointmentsystem.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorappointmentsystem.Adapter.Doclistadapter;
import com.example.doctorappointmentsystem.Model.Doclistmmodel;
import com.example.doctorappointmentsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class home_screen extends AppCompatActivity implements Doclistadapter.OnNoteListener {

    FirebaseAuth auth;
    DatabaseReference getUserDatabaseReference;
    RecyclerView doc_recycler;
    ArrayList<Doclistmmodel> Doc_list;
    Doclistadapter adapter;

    ImageView doc_btn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        doc_recycler = findViewById(R.id.doc_recycler);
        auth = FirebaseAuth.getInstance();

        doc_btn = (ImageView) findViewById(R.id.doc_btn);

        //recycler for Doctors list view
        Doc_list = new ArrayList<>();
        getUserDatabaseReference = FirebaseDatabase.getInstance().getReference("Doctors");
        doc_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        doc_recycler.setHasFixedSize(true);
        getMenData();
        //recycler for Doctors list view

        doc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), all_doc.class);
                startActivity(intent);
            }
        });


    }

    public void sd_see(View view) {

        startActivity(new Intent(this, specialist_screen.class));

    }

    public void td_see(View view) {

        startActivity(new Intent(this, top_doctors.class));
    }


    public void btn_home(View view){

        startActivity(new Intent(this, home_screen.class));
    }

    public void btn_profile(View view){

        startActivity(new Intent(this, Profile.class));
    }
    public void allp(View view){

        startActivity(new Intent(this, all_doc.class));
    }

    public void btn_appointment(View view){

        startActivity(new Intent(this, bookedappointment.class));
    }



    public void doc_profile(View view){

        startActivity(new Intent(this, doctor_profile.class));
    }


    private void getMenData() {
        try {
            getUserDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Doc_list.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {

                            String name = ds.child("d_name").getValue(String.class);
                            String specialization = ds.child("d_specialization").getValue(String.class);
                            String email = ds.child("d_email").getValue(String.class);
                            String id = ds.child("d_id").getValue(String.class);


                            Doclistmmodel model = new Doclistmmodel( name, specialization,email,id);
                            Doc_list.add(model);
                        }
                        Collections.shuffle(Doc_list);
//                        Toast.makeText(home_screen.this, String.valueOf(Doc_list.size()), Toast.LENGTH_SHORT).show();
                        adapter = new Doclistadapter(Doc_list, home_screen.this);
                        doc_recycler.setAdapter(adapter);
                    } else {
                        Toast.makeText(home_screen.this, "Empty", Toast.LENGTH_SHORT).show();
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

        String name =  Doc_list.get(position).getName();
        String specialization = Doc_list.get(position).getSpecialization();
        String email = Doc_list.get(position).getEmail();
        String docid = Doc_list.get(position).getId();
        Intent intent = new Intent(home_screen.this, doctor_profile.class);
        intent.putExtra("name",name);
        intent.putExtra("specialization", specialization);
        intent.putExtra("email",email);
        intent.putExtra("docid",docid);
        startActivity(intent);

    }

    @Override
    public void onCreates(Bundle savedInstanceState) {

    }

}
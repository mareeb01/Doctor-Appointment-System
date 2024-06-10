package com.example.doctorappointmentsystem.Doctors;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorappointmentsystem.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

public class doc_patient_detail extends AppCompatActivity {

    TextView name, age, problem, date,phone;
    Button uploadmedicine;
    ImageView imgview;
    String pname, page, pprolem, ptime, orderId,pphone,pid, patientId;
    Uri uri;
    String user_id;
    private DatabaseReference getUserDatabaseReferences;
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_patient_detail);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        problem = findViewById(R.id.problem);
        date = findViewById(R.id.date);
        uploadmedicine = findViewById(R.id.uploadmedicine);
        imgview = findViewById(R.id.imgview);
        phone = findViewById(R.id.phone);

        Intent intent = getIntent();
        pname = intent.getStringExtra("pname");
        name.setText(pname);
        page = intent.getStringExtra("page");
        age.setText(page);
        pprolem = intent.getStringExtra("pproblem");
        problem.setText(pprolem);
        ptime = intent.getStringExtra("ptime");
        date.setText(ptime);
        orderId = intent.getStringExtra("orderId");
        pphone = intent.getStringExtra("pphone");
        phone.setText(pphone);
        pid = intent.getStringExtra("pid");
        patientId = intent.getStringExtra("patientId");



//        Toast.makeText(this, ""+orderId, Toast.LENGTH_SHORT).show();

        mAuth = FirebaseAuth.getInstance();
        user_id = mAuth.getCurrentUser().getUid();

        getUserDatabaseReferences = FirebaseDatabase.getInstance().getReference().child("Appointments").child(user_id).child(orderId);
        //String patientId = FirebaseDatabase.getInstance().getReference().child("Appointments").child(user_id).child(orderId);
        getUserDatabaseReferences.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String imagess = dataSnapshot.child("medicineimg").getValue().toString();


                if(!imagess.equals("defaultimage")){ // default image condition for new user
                    Picasso.get()
                            .load(imagess)
//                             .networkPolicy(NetworkPolicy.OFFLINE) // for offline
                            .placeholder(R.drawable.mimg)
                            .error(R.drawable.mimg)
                            .into(imgview);
                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(doc_patient_detail.this)
                        .crop() // Crop image(Optional), Check Customization for more option
                        .start();
            }
        });

        uploadmedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uri != null) {
                    FirebaseStorage firebaseStorage;
                    firebaseStorage=FirebaseStorage.getInstance();
                    FirebaseDatabase firebaseDatabase;
                    firebaseDatabase=FirebaseDatabase.getInstance();

                    final StorageReference storageReference = firebaseStorage.getReference().child("Medicine").child(orderId);

                    storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    UploadMedicineModel UploadMedicineModel = new UploadMedicineModel();
                                    UploadMedicineModel.setMedicineimg(uri.toString());
                                    UploadMedicineModel.setPname(pname);
                                    UploadMedicineModel.setPage(page);
                                    UploadMedicineModel.setPdate(ptime);
                                    UploadMedicineModel.setPphone(pphone);
                                    UploadMedicineModel.setPproblem(pprolem);
                                    UploadMedicineModel.setOrderId(pid);
                                    UploadMedicineModel.setPatientId(patientId);
                                    Log.i("patientId", "onSuccess: " + patientId);

                                    firebaseDatabase.getReference().child("Appointments").child(user_id).child(orderId).setValue(UploadMedicineModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(doc_patient_detail.this, "Update successful", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        }
                    });
                } else {
                    Toast.makeText(doc_patient_detail.this, "Please select an image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && data != null) {
            uri = data.getData(); // Use Uri object instead of File to avoid storage permissions
            imgview.setImageURI(uri);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}

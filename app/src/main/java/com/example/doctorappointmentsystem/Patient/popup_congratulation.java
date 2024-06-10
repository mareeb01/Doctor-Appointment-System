package com.example.doctorappointmentsystem.Patient;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorappointmentsystem.R;

public class popup_congratulation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_congratulation);
    }

    private void showPopupDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_book_appointment);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // Make background transparent
        dialog.show();
    }

}
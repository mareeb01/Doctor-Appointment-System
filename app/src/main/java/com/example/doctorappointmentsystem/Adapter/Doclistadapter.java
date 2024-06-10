package com.example.doctorappointmentsystem.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorappointmentsystem.Model.Doclistmmodel;
import com.example.doctorappointmentsystem.R;

import java.util.ArrayList;

public class Doclistadapter extends RecyclerView.Adapter<Doclistadapter.personsViewfinder> {
    private final ArrayList<Doclistmmodel> list;
    private final OnNoteListener mOnNoteListener;


    public Doclistadapter(ArrayList<Doclistmmodel> list,OnNoteListener onNoteListener) {
        this.list = list;
        this.mOnNoteListener = onNoteListener;
    }

    @androidx.annotation.NonNull
    @Override
    public Doclistadapter.personsViewfinder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_doc_list_design,parent,false);
        return new Doclistadapter.personsViewfinder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull Doclistadapter.personsViewfinder holder, int position) {

        holder.name.setText(list.get(position).getName());
        holder.specialization.setText(list.get(position).getSpecialization());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class personsViewfinder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        TextView name,specialization;
        OnNoteListener onNoteListener2;
        public personsViewfinder(@NonNull View itemView, OnNoteListener onNoteListener){
            super(itemView);

            name = itemView.findViewById(R.id.name);
            specialization = itemView.findViewById(R.id.specialization);

            this.onNoteListener2=onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            onNoteListener2.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);

        void onCreates(Bundle savedInstanceState);
    }
}
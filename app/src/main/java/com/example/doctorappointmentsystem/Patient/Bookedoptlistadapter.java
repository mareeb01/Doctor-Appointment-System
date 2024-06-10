package com.example.doctorappointmentsystem.Patient;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorappointmentsystem.R;

import java.util.ArrayList;

public class Bookedoptlistadapter extends RecyclerView.Adapter<Bookedoptlistadapter.personsViewfinder> {
    private final ArrayList<Bookedoptlistmodel> list;
    private final OnNoteListener mOnNoteListener;


    public Bookedoptlistadapter(ArrayList<Bookedoptlistmodel> list, OnNoteListener onNoteListener) {
        this.list = list;
        this.mOnNoteListener = onNoteListener;
    }

    @androidx.annotation.NonNull
    @Override
    public Bookedoptlistadapter.personsViewfinder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booked_appointment_design,parent,false);
        return new Bookedoptlistadapter.personsViewfinder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull Bookedoptlistadapter.personsViewfinder holder, int position) {

        holder.pname.setText(list.get(position).getPname());
        holder.page.setText(list.get(position).getPage());
        holder.pdisc.setText(list.get(position).getPdisc());
        holder.atime.setText(list.get(position).getAtime());
//        Picasso.get().load(list.get(position).getPimg()).into(holder.pimg);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class personsViewfinder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        ImageView pimg;
        TextView pname,page,pdisc,atime;
        OnNoteListener onNoteListener2;
        public personsViewfinder(@NonNull View itemView, OnNoteListener onNoteListener){
            super(itemView);

            pimg = itemView.findViewById(R.id.pimg);
            pname = itemView.findViewById(R.id.pname);
            page = itemView.findViewById(R.id.page);
            pdisc = itemView.findViewById(R.id.pdisc);
            atime = itemView.findViewById(R.id.atime);

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








//public class Bookedoptlistadapter extends RecyclerView.Adapter<Bookedoptlistadapter.ViewHolder> {
//
//    private final ArrayList<Bookedoptlistmodel> list;
//    private final Doclistadapter.OnNoteListener mOnNoteListener;
//    private Context context;
//
//
//    public Bookedoptlistadapter(ArrayList<Bookedoptlistmodel> list, Context context, Doclistadapter.OnNoteListener onNoteListener) {
//        this.list = list;
//        this.mOnNoteListener = onNoteListener;
//        this.context = context;
//    }
//
//    @androidx.annotation.NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booked_appointment_design,parent,false);
//        return new Bookedoptlistadapter.personsViewfinder(view, mOnNoteListener);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//    }
//
//
//    @Override
//    public void onBindViewHolder(@androidx.annotation.NonNull Bookedoptlistadapter.personsViewfinder holder, int position) {
//
//        holder.pname.setText(list.get(position).getPname());
//        holder.page.setText(list.get(position).getPage());
//        holder.pdisc.setText(list.get(position).getPdisc());
//        holder.atime.setText(list.get(position).getAtime());
////        Picasso.get().load(list.get(position).getPimg()).into(holder.pimg);
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//        private final Doclistadapter.OnNoteListener onNoteListener2;
//        ImageView pimg;
//        TextView pname,page,pdisc,atime;
//
//        public personsViewfinder(@NonNull View itemView, Doclistadapter.OnNoteListener onNoteListener){
//            super(itemView);
//
//            pimg = itemView.findViewById(R.id.pimg);
//            pname = itemView.findViewById(R.id.pname);
//            page = itemView.findViewById(R.id.page);
//            pdisc = itemView.findViewById(R.id.pdisc);
//            atime = itemView.findViewById(R.id.atime);
//
//            this.onNoteListener2=onNoteListener;
//            itemView.setOnClickListener(this);
//        }
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//
//        @Override
//        public void onClick(View v)
//        {
//            onNoteListener2.onNoteClick(getAdapterPosition());
//        }
//    }
//
//    public interface OnNoteListener{
//        void onNoteClick(int position);
//
//        void onCreates(Bundle savedInstanceState);
//    }
//}
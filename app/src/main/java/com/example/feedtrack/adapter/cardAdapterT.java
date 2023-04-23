package com.example.feedtrack.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedtrack.R;
import com.example.feedtrack.feedback_options_t;
import com.example.feedtrack.model.cardT;
import com.example.feedtrack.prn_list;

import java.util.ArrayList;
import java.util.List;



public class cardAdapterT extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<cardT> cards;
    String sem;
    Context ctx;
    public cardAdapterT(ArrayList<cardT> cards,Context ctx,String sem) {
        this.ctx=ctx;
        this.cards = cards;
        this.sem=sem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardt, parent, false);
        return new RecyclerView.ViewHolder(view) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        cardT c=cards.get(position);
        TextView titleTextView = holder.itemView.findViewById(R.id.titleTextView);
        Button TeacherFeedbackBtn = holder.itemView.findViewById(R.id.TeacherFeedbackBtn);
        Button attendance = holder.itemView.findViewById(R.id.TeacherAttendenceBtn);
        String sub=c.getTitle();
        titleTextView.setText(sub);
//
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle feedback button click
                Intent intent=new Intent(ctx, prn_list.class);
                intent.putExtra("sub",sub);
                intent.putExtra("sem",sem);
                ctx.startActivity(intent);


            }
        });

        TeacherFeedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle feedback button click
                Intent intent=new Intent(ctx, feedback_options_t.class);
                intent.putExtra("sub",sub);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return  cards == null ? 0 : cards.size();

    }
}
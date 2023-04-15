package com.example.feedtrack.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedtrack.R;
import com.example.feedtrack.model.cardT;
import com.example.feedtrack.prn_list;

import java.util.List;



public class cardAdapterT extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<cardT> cards;
    Context ctx;
    public cardAdapterT(List<cardT> cards,Context ctx) {
        this.ctx=ctx;
        this.cards = cards;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardt, parent, false);
        return new RecyclerView.ViewHolder(view) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        cardT card = cards.get(position);
        TextView titleTextView = holder.itemView.findViewById(R.id.titleTextView);
        Button TeacherFeedbackBtn = holder.itemView.findViewById(R.id.TeacherFeedbackBtn);
        Button attendance = holder.itemView.findViewById(R.id.TeacherAttendenceBtn);

        titleTextView.setText(card.getTitle());
//
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle feedback button click
                    ctx.startActivity(new Intent(ctx, prn_list.class));

            }
        });

        TeacherFeedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle feedback button click
            }
        });
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}


package com.example.feedtrack.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedtrack.MainActivity;
import com.example.feedtrack.R;
import com.example.feedtrack.feedback_options;
import com.example.feedtrack.model.Card;
import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Card> cards;
    Context ctx;
    public CardAdapter(List<Card> cards, Context ctx) {
        this.cards = cards;
        this.ctx=ctx;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new RecyclerView.ViewHolder(view) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Card card = cards.get(position);
        TextView titleTextView = holder.itemView.findViewById(R.id.titleTextView);
        TextView descriptionTextView = holder.itemView.findViewById(R.id.descriptionTextView);
        TextView lecturesPresentTextView = holder.itemView.findViewById(R.id.lecturesPresentTextView);
        TextView lecturesConductedTextView = holder.itemView.findViewById(R.id.lecturesConductedTextView);
        TextView Percentages=holder.itemView.findViewById(R.id.Percentages);
        Button feedbackButton = holder.itemView.findViewById(R.id.feedbackButton);

        titleTextView.setText(card.getTitle());
        descriptionTextView.setText(card.getDescription());
        lecturesPresentTextView.setText("Lectures Present: " + card.getLecturesPresent());
        lecturesConductedTextView.setText("Lectures Conducted: " + card.getConducted());
        Percentages.setText("Percentages:"+card.getPercentages()+"%");

        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle feedback button click
                Intent intent=new Intent(ctx, feedback_options.class);
                intent.putExtra("sub",card.getTitle());
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}


package com.example.feedtrack.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.feedtrack.R;
import com.example.feedtrack.adapter.cardAdapterT;
import com.example.feedtrack.model.cardT;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Teachersem5 extends Fragment {
    private RecyclerView recyclerView;
    private cardAdapterT adapter;
    DatabaseReference db;

    public ArrayList<cardT> cards;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sem5, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        db=FirebaseDatabase.getInstance().getReference("Teachers").child(this.getArguments().getString("uname"));
        cards = new ArrayList<>();
//        cards.add(new cardT("Card sem5 "));
//        cards.add(new cardT("Card 2 sem 5"));
//        cards.add(new cardT("Card 3 sem 5"));
//        cards.add(new cardT("Card 4"));
//        cards.add(new cardT("Card 5"));
//        cards.add(new cardT("Card 6"));
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    cardT c=snapshot.getValue(cardT.class);
                    c.setTitle(snapshot.child("sub").child("fifth").getValue().toString());
                    cards.add(c);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        adapter = new cardAdapterT(cards,getContext(),"fifth");
        recyclerView.setAdapter(adapter);

        return view;
    }
}
package com.example.feedtrack.fragments;

import android.os.Bundle;
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

import java.util.ArrayList;

public class Teachersem5 extends Fragment {
    private RecyclerView recyclerView;
    private cardAdapterT adapter;
    private ArrayList<cardT> cards;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sem5, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cards = new ArrayList<>();
        cards.add(new cardT("Card sem5 "));
        cards.add(new cardT("Card 2 sem 5"));
        cards.add(new cardT("Card 3 sem 5"));
        cards.add(new cardT("Card 4"));
        cards.add(new cardT("Card 5"));
        cards.add(new cardT("Card 6"));

        adapter = new cardAdapterT(cards,getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }
}

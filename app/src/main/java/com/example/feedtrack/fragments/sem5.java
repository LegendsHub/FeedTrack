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
import com.example.feedtrack.adapter.CardAdapter;
import com.example.feedtrack.databinding.FragmentSem4Binding;
import com.example.feedtrack.databinding.FragmentSem5Binding;
import com.example.feedtrack.model.Card;

import java.util.ArrayList;

public class sem5 extends Fragment {
    private CardAdapter adapter;
    private ArrayList<Card> cards;
    FragmentSem5Binding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= FragmentSem5Binding.inflate(inflater,container,false);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cards = new ArrayList<>();
        cards.add(new Card("Card sem5", "Description 1", 5, 10));
        cards.add(new Card("Card 2", "Description 2", 7, 12));
        cards.add(new Card("Card 3", "Description 3", 2, 8));
        cards.add(new Card("Card 4", "Description 4", 9, 15));
        cards.add(new Card("Card 5", "Description 5", 3, 6));
        cards.add(new Card("Card 6", "Description 6", 4, 9));

        adapter = new CardAdapter(cards);
        binding.recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }
}

package com.example.feedtrack.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedtrack.R;
import com.example.feedtrack.adapter.CardAdapter;
import com.example.feedtrack.databinding.FragmentSem4Binding;
import com.example.feedtrack.model.Card;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class sem4 extends Fragment {
    ArrayList<Card> cards;
    CardAdapter adapter;
    FragmentSem4Binding binding;
    FirebaseDatabase db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragmentSem4Binding.inflate(inflater,container,false);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String prn=this.getArguments().getString("prn");
        cards = new ArrayList<>();
        db=FirebaseDatabase.getInstance();
        db.getReference("Students").child(prn).child("fourth").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()) {
                    Card c=snapshot1.getValue(Card.class);
                    String sub=String.valueOf(snapshot1.getKey());
                    c.setTitle(sub);
                    int present=snapshot1.child("attended").getValue(Integer.class);
                    c.setLecturesPresent(present);
                    DatabaseReference ref=FirebaseDatabase.getInstance().getReference("fourth").child("sub").child(sub).child("conducted");
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int conduct=snapshot.getValue(Integer.class);
                            c.setConducted(conduct);
                            try {
                                c.setPercentages((int)((float)present/(float)conduct)*100);
                                adapter.notifyDataSetChanged();
                            } catch (Exception e) {
                                c.setPercentages(0);
                            }
                            adapter.notifyDataSetChanged(); // move this line inside onDataChange
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // handle error
                        }
                    });
                    // move these lines inside onDataChange
                    cards.add(c);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        adapter = new CardAdapter(cards,getContext());
        binding.recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }

}
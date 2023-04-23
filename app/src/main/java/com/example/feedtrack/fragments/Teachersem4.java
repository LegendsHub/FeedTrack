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
import com.example.feedtrack.databinding.FragmentStudentBinding;
import com.example.feedtrack.databinding.FragmentTeachersem4Binding;
import com.example.feedtrack.model.ContactModel;
import com.example.feedtrack.model.cardT;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Teachersem4 extends Fragment {
    FragmentTeachersem4Binding binding;
    private RecyclerView recyclerView;
    private cardAdapterT adapter;
    DatabaseReference db;
    ArrayList<cardT> cards;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTeachersem4Binding.inflate(inflater, container, false);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        db=FirebaseDatabase.getInstance().getReference("Teachers").child(this.getArguments().getString("uname"));
        cards = new ArrayList<>();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 cardT c=snapshot.getValue(cardT.class);
                String subject=snapshot.child("sub").child("fourth").getValue(String.class);
                    c.setTitle(subject);
                    cards.add(c);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new cardAdapterT(cards,getContext(),"fourth");
        binding.recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }
}
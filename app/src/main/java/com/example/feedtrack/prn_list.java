package com.example.feedtrack;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.feedtrack.adapter.RecyclerContactAdapter;
import com.example.feedtrack.databinding.ActivityPrnListBinding;
import com.example.feedtrack.model.ContactModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class prn_list extends AppCompatActivity {
    ArrayList<ContactModel> arrcontacts=new ArrayList<>();
    FirebaseDatabase db;
    String sub,sem;
ActivityPrnListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPrnListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db=FirebaseDatabase.getInstance();
        sem=getIntent().getStringExtra("sem");
        sub=getIntent().getStringExtra("sub");
        RecyclerContactAdapter adapter=new RecyclerContactAdapter(prn_list.this,arrcontacts,sub,sem);
        db.getReference("Students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    ContactModel c=snapshot1.getValue(ContactModel.class);
                    c.setPrn(String.valueOf(snapshot1.child("user_name").getValue()));
                    arrcontacts.add(c);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.recyclerContact.setLayoutManager(new LinearLayoutManager(prn_list.this));
        binding.recyclerContact.setAdapter(adapter);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.attendance,menu);
        MenuBuilder m = (MenuBuilder) menu;
        //noinspection RestrictedApi
        m.setOptionalIconsVisible(true);
        return super.onCreateOptionsMenu(menu);
    }
//
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       DatabaseReference d;
        if(R.id.save_attendance==item.getItemId()){
            d = FirebaseDatabase.getInstance().getReference(sem).child("sub").child(sub);
            d.child("conducted").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    add(snapshot, d);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle error
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
    public void add(DataSnapshot snapshot, DatabaseReference d) {
        Integer a = snapshot.getValue(Integer.class);
        int conducted = a + 1;
        d.child("conducted").setValue(conducted)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Update UI or do something else on success
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle error
                    }
                });
    }
}
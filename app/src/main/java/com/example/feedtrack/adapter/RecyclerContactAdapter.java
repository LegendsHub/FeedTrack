package com.example.feedtrack.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedtrack.R;
import com.example.feedtrack.TeacherActivity;
import com.example.feedtrack.loginT;
import com.example.feedtrack.model.ContactModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder>
{
    Context context;
    ArrayList<ContactModel>arrConcants;
    DatabaseReference db;
    String sub,sem;
    public RecyclerContactAdapter(Context context, ArrayList<ContactModel> arrcon,String sub,String sem)
    {
        this.context=context;
        this.arrConcants=arrcon;
        this.sub=sub;
        this.sem=sem;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactModel cm = arrConcants.get(position);
        String prn = cm.getPrn();
        holder.txtName.setText(prn);
//        int attended;
        holder.p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = FirebaseDatabase.getInstance().getReference("Students").child(prn).child(sem).child(sub);
                db.child("attended").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        add(snapshot, db);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle error
                    }
                });
            }
        });
    }

    public void add(DataSnapshot snapshot, DatabaseReference db) {
        Integer a = snapshot.getValue(Integer.class);
        int attended = a + 1;
        db.child("attended").setValue(attended)
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

    @Override
    public int getItemCount() {
        return arrConcants.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtName;
        Button p;
        public ViewHolder(View itemView) {
            super(itemView);

            txtName=itemView.findViewById(R.id.txtName);
            p=itemView.findViewById(R.id.p);
        }
    }
}

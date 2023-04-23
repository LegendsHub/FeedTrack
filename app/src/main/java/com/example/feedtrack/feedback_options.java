package com.example.feedtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.feedtrack.databinding.ActivityFeedbackOptionsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class feedback_options extends AppCompatActivity {
ActivityFeedbackOptionsBinding binding;
FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFeedbackOptionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        db=FirebaseDatabase.getInstance();
        Intent i=getIntent();
        String sub=i.getStringExtra("sub");
        String prn=i.getStringExtra("prn");
        String sem=i.getStringExtra("sem");
        binding.weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(feedback_options.this,weekly_feedback.class);
                i.putExtra("sub",sub);
                i.putExtra("prn",prn);
                i.putExtra("sem",sem);
                startActivity(i);
            }
        });
        db.getReference("feedback").child("sub").child(sub).child("eseenabled").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    binding.ese.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int va=snapshot.getValue(Integer.class);
                            if(va==0){
                                Toast.makeText(feedback_options.this, "ESE Feedback is not enabled", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent i=new Intent(feedback_options.this, exam_feedback.class);
                                i.putExtra("sub",sub);
                                i.putExtra("prn",prn);
                                i.putExtra("sem",sem);
                                i.putExtra("type","ese");
                                startActivity(i);
                            }
                        }
                    });
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db.getReference("feedback").child("sub").child(sub).child("mseenabled").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    binding.mse.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int va=snapshot.getValue(Integer.class);
                            if(va==0){
                                Toast.makeText(feedback_options.this, "MSE Feedback is not enabled", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent i=new Intent(feedback_options.this, exam_feedback.class);
                                i.putExtra("sub",sub);
                                i.putExtra("prn",prn);
                                i.putExtra("sem",sem);
                                i.putExtra("type","mse");
                                startActivity(i);
                            }
                        }
                    });
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
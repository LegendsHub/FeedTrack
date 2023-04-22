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
        db=FirebaseDatabase.getInstance();
        Intent i=getIntent();
        String sub=i.getStringExtra("sub");
        binding.weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(feedback_options.this,weekly_feedback.class));
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
                                startActivity(new Intent(feedback_options.this, exam_feedback.class));
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
                                startActivity(new Intent(feedback_options.this, exam_feedback.class));
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
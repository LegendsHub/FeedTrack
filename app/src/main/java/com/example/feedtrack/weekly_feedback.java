package com.example.feedtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.feedtrack.databinding.ActivityWeeklyFeedbackBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class weekly_feedback extends AppCompatActivity {
ActivityWeeklyFeedbackBinding binding;
DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityWeeklyFeedbackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        Intent i=getIntent();
        String prn=i.getStringExtra("prn");
        String sem=i.getStringExtra("sem");
        String sub=i.getStringExtra("sub");

        binding.submitWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.interactive.getCheckedRadioButtonId()==-1||binding.understanding.getCheckedRadioButtonId()==-1||binding.relevance.getCheckedRadioButtonId()==-1){
                    Toast.makeText(weekly_feedback.this, "First 3 questions are mandatory", Toast.LENGTH_SHORT).show();
                }
                else {
                    RadioButton rb = findViewById(binding.understanding.getCheckedRadioButtonId());
                    RadioButton rb1 = findViewById(binding.relevance.getCheckedRadioButtonId());
                    RadioButton rb2 = findViewById(binding.interactive.getCheckedRadioButtonId());
                    String ans1 = rb.getText().toString();
                    String ans2 = rb1.getText().toString();
                    String ans3 = rb2.getText().toString();
                    String ans4 = binding.detail.getText().toString().trim();
                    String ans5 = binding.suggetion.getText().toString().trim();
                    db = FirebaseDatabase.getInstance().getReference("Students").child(prn).child(sem).child(sub).child("weekly");
                    db.child("How much did you understand in the last week?").setValue(ans1);
                    db.child("Were this week's lectures relevant to the course content?").setValue(ans2);
                    db.child("Did you feel engaged and interactive throughout the lecture?").setValue(ans3);
                    db.child("Were there any topics you would like to see covered in more detail?").setValue(ans4);
                    db.child("What suggestions would you like to give?").setValue(ans5);
                    Toast.makeText(weekly_feedback.this, "Response Submitted...", Toast.LENGTH_SHORT).show();
                    binding.detail.setText("");
                    binding.suggetion.setText("");
                    rb2.setSelected(false);
                    rb.setSelected(false);
                    rb2.setSelected(false);
                }
            }});

    }
}
package com.example.feedtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.feedtrack.databinding.ActivityExamFeedbackBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class exam_feedback extends AppCompatActivity {
ActivityExamFeedbackBinding binding;
DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityExamFeedbackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent i=getIntent();
        String prn=i.getStringExtra("prn");
        String sem=i.getStringExtra("sem");
        String sub=i.getStringExtra("sub");
        String type=i.getStringExtra("type");

        binding.examSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.difficultyRadioGroup.getCheckedRadioButtonId()==-1||binding.understanding.getCheckedRadioButtonId()==-1||binding.knowledge.getCheckedRadioButtonId()==-1||binding.relevant.getCheckedRadioButtonId()==-1){
                    Toast.makeText(exam_feedback.this, "First 4 questions are mandatory", Toast.LENGTH_SHORT).show();
                }
                else {
                    RadioButton rb = findViewById(binding.difficultyRadioGroup.getCheckedRadioButtonId());
                    RadioButton rb1 = findViewById(binding.understanding.getCheckedRadioButtonId());
                    RadioButton rb2 = findViewById(binding.knowledge.getCheckedRadioButtonId());
                    RadioButton rb3 = findViewById(binding.relevant.getCheckedRadioButtonId());
                    String ans1 = rb.getText().toString();
                    String ans2 = rb1.getText().toString();
                    String ans3 = rb2.getText().toString();
                    String ans4 = rb3.getText().toString();
                    String ans5 = binding.suggestion.getText().toString().trim();
                    db = FirebaseDatabase.getInstance().getReference("Students").child(prn).child(sem).child(sub).child(type);
                    db.child("Do you feel the exam accurately assessed your knowledge and understanding?").setValue(ans3);
                    db.child("How much syllabus did the question paper cover?").setValue(ans2);
                    db.child("Is the course relevant to industry needs?").setValue(ans4);
                    db.child("What suggestions would you like to give?").setValue(ans5);
                    db.child("What was the difficulty level of the exam?").setValue(ans1);
                }
            }});

    }
}
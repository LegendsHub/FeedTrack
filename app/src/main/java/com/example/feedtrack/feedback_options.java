package com.example.feedtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.feedtrack.databinding.ActivityFeedbackOptionsBinding;

public class feedback_options extends AppCompatActivity {
ActivityFeedbackOptionsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFeedbackOptionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(feedback_options.this,weekly_feedback.class));
            }
        });
        binding.ese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(feedback_options.this,exam_feedback.class));
            }
        });
        binding.mse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(feedback_options.this,exam_feedback.class));
            }
        });
    }
}
package com.example.feedtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.feedtrack.databinding.ActivityExamFeedbackBinding;

public class exam_feedback extends AppCompatActivity {
ActivityExamFeedbackBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityExamFeedbackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}
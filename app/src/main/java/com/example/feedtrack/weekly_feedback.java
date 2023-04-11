package com.example.feedtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.feedtrack.databinding.ActivityWeeklyFeedbackBinding;

public class weekly_feedback extends AppCompatActivity {
ActivityWeeklyFeedbackBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityWeeklyFeedbackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}
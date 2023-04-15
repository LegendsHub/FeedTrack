package com.example.feedtrack;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.feedtrack.databinding.ActivityTeacherBinding;
import com.example.feedtrack.fragments.Teachersem4;
import com.example.feedtrack.fragments.Teachersem5;


public class TeacherActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ActivityTeacherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTeacherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.semesterSpinner.setOnItemSelectedListener(this);

        // Make API call to retrieve user's name and PRN number
        getUserInfo();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                // Redirect to Semester 4 Fragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container1, new Teachersem4())
                        .addToBackStack(null)
                        .commit();
                break;
            case 1:
                // Redirect to Semester 5 Fragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container1, new Teachersem5())
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }

    private void getUserInfo() {
        // Make API call to retrieve user's name and PRN number
        // Replace the following with your backend API call implementation
        String name = "John Doe";
        String mail = "abc@walchandsangli.ac.in";

        // Set the retrieved name and PRN number in the corresponding TextViews
        binding.name.setText("Name: "+name);
        binding.mailid.setText("Email: "+mail);
    }
}



package com.example.feedtrack.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.feedtrack.R;
import com.example.feedtrack.databinding.FragmentStudentBinding;
import com.example.feedtrack.databinding.FragmentTeacherBinding;
import com.example.feedtrack.login;
import com.example.feedtrack.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class teacher extends Fragment {

    FragmentTeacherBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTeacherBinding.inflate(inflater, container, false);
        binding.alreadyhave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), login.class));
            }
        });
        pd = new ProgressDialog(getContext());
        pd.setTitle("Creating Account");
        pd.setMessage("Please Wait...");
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        binding.signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                mAuth.createUserWithEmailAndPassword
                        (binding.signupEmail.getText().toString(), binding.signupPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            pd.dismiss();
                            Users user = new Users(binding.uname.getText().toString(), binding.signupPassword.getText().toString(), binding.signupEmail.getText().toString());
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Teachers").child(binding.uname.getText().toString()).setValue(user);
                            startActivity(new Intent(getContext(), login.class));
                            Toast.makeText(getContext(), "Account Created Successfully...", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return binding.getRoot();
    }
}

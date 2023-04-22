package com.example.feedtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.feedtrack.databinding.ActivityLoginBinding;
import com.example.feedtrack.databinding.ActivityLoginTBinding;
import com.example.feedtrack.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class loginT extends AppCompatActivity {
    ActivityLoginTBinding binding;
    ProgressDialog pd;
    FirebaseAuth auth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginTBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        pd=new ProgressDialog(loginT.this);
        pd.setTitle("Authenticating");
        pd.setMessage("please wait...");
        binding.clickhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginT.this,signup.class));
            }


        });

        binding.Studentlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginT.this,login.class));
            }
        });


        binding.forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=binding.email.getText().toString();
                if(TextUtils.isEmpty(str))
                {
                    Toast.makeText(loginT.this,"Email field required to reset password", Toast.LENGTH_SHORT).show();
                    binding.email.setError("Email is required");
                    binding.email.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(str).matches())
                {
                    binding.email.setError("enter valid email");
                    binding.email.requestFocus();
                }
                else {
                    auth.sendPasswordResetEmail(binding.email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(loginT.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=binding.email.getText().toString();
                String pass=binding.password.getText().toString();
                Intent intent=new Intent(loginT.this, TeacherActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("email",binding.email.getText().toString().trim());
                intent.putExtra("password",binding.password.getText().toString());
                if(TextUtils.isEmpty(str))
                {
                    Toast.makeText(loginT.this,"Email field should not be empty", Toast.LENGTH_SHORT).show();
                    binding.email.setError("Email is required");
                    binding.email.requestFocus();
                }
                else if(TextUtils.isEmpty(pass)){
                    Toast.makeText(loginT.this,"password field should not be empty", Toast.LENGTH_SHORT).show();
                    binding.password.setError("Password is required");
                    binding.password.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(str).matches())
                {
                    binding.email.setError("enter valid email");
                    binding.email.requestFocus();
                }
                else if(str.equals("mayur.rathi@walchandsangli.ac.in")||str.equals("prashant.kharat@walchandsangli.ac.in")||str.equals("shefali.sonavane@walchandsangli.ac.in")||str.equals("manisha.dabde@walchandsangli.ac.in")) {
                    pd.show();
                    auth.signInWithEmailAndPassword(binding.email.getText().toString(), binding.password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            pd.dismiss();
                            if (task.isSuccessful()) {
                                startActivity(intent);
                            } else {
                                Toast.makeText(loginT.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(loginT.this, "User is not authorized", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
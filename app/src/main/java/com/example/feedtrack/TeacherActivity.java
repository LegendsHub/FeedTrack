package com.example.feedtrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.FragmentTransaction;

import com.example.feedtrack.databinding.ActivityTeacherBinding;
import com.example.feedtrack.fragments.Teachersem4;
import com.example.feedtrack.fragments.Teachersem5;
import com.google.firebase.auth.FirebaseAuth;


public class TeacherActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ActivityTeacherBinding binding;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTeacherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.semesterSpinner.setOnItemSelectedListener(this);
        auth=FirebaseAuth.getInstance();
        Intent intent=getIntent();
        // Make API call to retrieve user's name and PRN number
        getUserInfo(intent.getStringExtra("email"),intent.getStringExtra("password"));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                // Redirect to Semester 4 Fragment
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                Bundle bundle=new Bundle();
                bundle.putString("uname",getIntent().getStringExtra("password"));
                Teachersem4 teachersem4=new Teachersem4();
                teachersem4.setArguments(bundle);
                transaction.replace(R.id.fragment_container1, teachersem4).commit();
                break;
            case 1:
                // Redirect to Semester 5 Fragment
                FragmentTransaction trans=getSupportFragmentManager().beginTransaction();
                Bundle bun=new Bundle();
                bun.putString("uname",getIntent().getStringExtra("password"));
                Teachersem5 teachersem5=new Teachersem5();
                teachersem5.setArguments(bun);
                trans.replace(R.id.fragment_container1, teachersem5).commit();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }

    private void getUserInfo(String mail,String name) {
        // Make API call to retrieve user's name and PRN number
        // Replace the following with your backend API call implementation
//        String name = "John Doe";
//        String mail = "abc@walchandsangli.ac.in";

        // Set the retrieved name and PRN number in the corresponding TextViews
        binding.txtName.setText("Name: "+name);
        binding.mailid.setText("Email: "+mail);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuBuilder m = (MenuBuilder) menu;
        //noinspection RestrictedApi
        m.setOptionalIconsVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                auth.signOut();
                startActivity(new Intent(TeacherActivity.this,loginT.class));

        }
        return super.onOptionsItemSelected(item);
    }
}
package com.example.feedtrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import com.example.feedtrack.databinding.ActivityMainBinding;
import com.example.feedtrack.fragments.sem4;
import com.example.feedtrack.fragments.sem5;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

   ActivityMainBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
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
                        .replace(R.id.fragment_container, new sem4())
                        .addToBackStack(null)
                        .commit();
                break;
            case 1:
                // Redirect to Semester 5 Fragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new sem5())
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
        String name = "Saurabh Salunke";
        String prn = "22620002";

        // Set the retrieved name and PRN number in the corresponding TextViews
        binding.name.setText("Name: "+name);
        binding.welcometxt.setText("Welcome, "+prn);
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
                startActivity(new Intent(MainActivity.this,login.class));

        }
        return super.onOptionsItemSelected(item);
    }
}
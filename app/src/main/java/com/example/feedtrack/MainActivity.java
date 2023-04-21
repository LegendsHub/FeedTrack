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
import androidx.fragment.app.FragmentTransaction;

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
    String prn,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        binding.semesterSpinner.setOnItemSelectedListener(this);
        // Make API call to retrieve user's name and PRN number
        Intent intent=getIntent();
        email=intent.getStringExtra("email");
        prn=intent.getStringExtra("prn");
        getUserInfo(email,prn);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                Bundle bundle=new Bundle();
                bundle.putString("prn",prn);
                sem4 sem4=new sem4();
                sem4.setArguments(bundle);
                transaction.replace(R.id.fragment_container, sem4).commit();
                break;
            case 1:
                FragmentTransaction trans=getSupportFragmentManager().beginTransaction();
                Bundle bun=new Bundle();
                bun.putString("prn",prn);
                sem5 sem5=new sem5();
                sem5.setArguments(bun);
                trans.replace(R.id.fragment_container, sem5).commit();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }

    private void getUserInfo(String email,String prn) {
        // Make API call to retrieve user's name and PRN number
        // Replace the following with your backend API call implementation

        // Set the retrieved name and PRN number in the corresponding TextViews
        binding.name.setText(email);
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
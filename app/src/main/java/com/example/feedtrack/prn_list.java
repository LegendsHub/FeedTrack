package com.example.feedtrack;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.feedtrack.adapter.RecyclerContactAdapter;
import com.example.feedtrack.databinding.ActivityPrnListBinding;
import com.example.feedtrack.model.ContactModel;

import java.util.ArrayList;

public class prn_list extends AppCompatActivity {
    ArrayList<ContactModel> arrcontacts=new ArrayList<>();
ActivityPrnListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPrnListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        arrcontacts.add(new ContactModel("21610003"));
        arrcontacts.add(new ContactModel("21610005"));
        arrcontacts.add(new ContactModel("21610006"));
        arrcontacts.add(new ContactModel("21610008"));
        arrcontacts.add(new ContactModel("21610009"));
        arrcontacts.add(new ContactModel("21610010"));

        arrcontacts.add(new ContactModel("21610011"));
        arrcontacts.add(new ContactModel("21610013"));
        arrcontacts.add(new ContactModel("21610014"));
        arrcontacts.add(new ContactModel("21610017"));
        arrcontacts.add(new ContactModel("21610018"));
        arrcontacts.add(new ContactModel("21610019"));

        arrcontacts.add(new ContactModel("21610020"));
        arrcontacts.add(new ContactModel("21610021"));
        arrcontacts.add(new ContactModel("21610022"));
        arrcontacts.add(new ContactModel("21610023"));
        arrcontacts.add(new ContactModel("21610024"));
        arrcontacts.add(new ContactModel("21610025"));

        arrcontacts.add(new ContactModel("21610026"));
        arrcontacts.add(new ContactModel("21610027"));
        arrcontacts.add(new ContactModel("21610028"));
        arrcontacts.add(new ContactModel("21610029"));
        arrcontacts.add(new ContactModel("21610031"));
        arrcontacts.add(new ContactModel("21610035"));

        arrcontacts.add(new ContactModel("21610036"));
        arrcontacts.add(new ContactModel("21610038"));
        arrcontacts.add(new ContactModel("21610040"));
        arrcontacts.add(new ContactModel("21610041"));
        arrcontacts.add(new ContactModel("21610042"));
        arrcontacts.add(new ContactModel("21610043"));

        arrcontacts.add(new ContactModel("21610045"));
        arrcontacts.add(new ContactModel("21610046"));
        arrcontacts.add(new ContactModel("21610047"));
        arrcontacts.add(new ContactModel("21610048"));
        arrcontacts.add(new ContactModel("21610049"));
        arrcontacts.add(new ContactModel("21610050"));

        arrcontacts.add(new ContactModel("21610051"));
        arrcontacts.add(new ContactModel("21610052"));
        arrcontacts.add(new ContactModel("21610053"));
        arrcontacts.add(new ContactModel("21610054"));
        arrcontacts.add(new ContactModel("21610055"));
        arrcontacts.add(new ContactModel("21610056"));

        arrcontacts.add(new ContactModel("21610057"));
        arrcontacts.add(new ContactModel("21610058"));
        arrcontacts.add(new ContactModel("21610059"));
        arrcontacts.add(new ContactModel("21610060"));
        arrcontacts.add(new ContactModel("21610061"));
        arrcontacts.add(new ContactModel("21610062"));


        arrcontacts.add(new ContactModel("21610063"));
        arrcontacts.add(new ContactModel("21610064"));
        arrcontacts.add(new ContactModel("21610065"));
        arrcontacts.add(new ContactModel("21610066"));
        arrcontacts.add(new ContactModel("21610067"));
        arrcontacts.add(new ContactModel("21610068"));

        arrcontacts.add(new ContactModel("21610069"));
        arrcontacts.add(new ContactModel("21610070"));
        arrcontacts.add(new ContactModel("21610071"));
        arrcontacts.add(new ContactModel("21610072"));
        arrcontacts.add(new ContactModel("21610073"));
        arrcontacts.add(new ContactModel("21610074"));

        arrcontacts.add(new ContactModel("21610075"));
        arrcontacts.add(new ContactModel("21610076"));
        arrcontacts.add(new ContactModel("21610077"));
        arrcontacts.add(new ContactModel("21610078"));
        arrcontacts.add(new ContactModel("21610079"));
        arrcontacts.add(new ContactModel("21610080"));


        arrcontacts.add(new ContactModel("22620001"));
        arrcontacts.add(new ContactModel("22620002"));
        arrcontacts.add(new ContactModel("22620003"));
        arrcontacts.add(new ContactModel("22620004"));
        arrcontacts.add(new ContactModel("22620005"));
        arrcontacts.add(new ContactModel("22620006"));
        arrcontacts.add(new ContactModel("22620007"));
        arrcontacts.add(new ContactModel("22620008"));
        arrcontacts.add(new ContactModel("22620009"));
        arrcontacts.add(new ContactModel("22620010"));
        arrcontacts.add(new ContactModel("22620012"));


        RecyclerContactAdapter adapter=new RecyclerContactAdapter(prn_list.this,arrcontacts);
        binding.recyclerContact.setLayoutManager(new LinearLayoutManager(prn_list.this));
        binding.recyclerContact.setAdapter(adapter);
    }
}
package com.example.feedtrack;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.feedtrack.databinding.ActivityTeacherBinding;
import com.example.feedtrack.fragments.Teachersem4;
import com.example.feedtrack.fragments.Teachersem5;
import com.example.feedtrack.model.Card;
import com.example.feedtrack.model.attendance;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class TeacherActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ActivityTeacherBinding binding;
    FirebaseAuth auth;
    ArrayList<attendance> c=new ArrayList<>();
    FirebaseDatabase db;
    String sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTeacherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.semesterSpinner.setOnItemSelectedListener(this);
        auth=FirebaseAuth.getInstance();
        Intent intent=getIntent();
        db=FirebaseDatabase.getInstance();
        // Make API call to retrieve user's name and PRN number
        String email=intent.getStringExtra("email");
        getUserInfo(email,intent.getStringExtra("password"));
        if(email.equals("mayur.rathi@walchandsangli.ac.in")){
            sub="Software Engineering";
        } else if (email.equals("shefali.sonavane@walchandsangli.ac.in")) {
            sub="Theory of computation";
        } else if (email.equals("prashant.kharat@walchandsangli.ac.in")) {
            sub="Computer Networks";
        } else if (email.equals("manisha.dabde@walchandsangli.ac.in")) {
            sub="Computer Architecure";
        }
        else{}
//
//        data reading from firebase for attendance pdf

        FirebaseDatabase.getInstance().getReference("Students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                c.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    attendance at=snapshot1.getValue(attendance.class);
                    String p=snapshot1.getKey();
                    at.setPrn(p);
                    int attend= snapshot1.child("fourth").child(sub).child("attended").getValue(Integer.class);
                    at.setAttended(attend);
                    FirebaseDatabase.getInstance().getReference("fourth").child("sub").child(sub).child("conducted").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int con=snapshot.getValue(Integer.class);
                            at.setConducted(con);
//                            try {
//                                at.setPercentage(at.getPercentage());
//                            } catch (Exception e) {
//                                at.setPercentage(0);
//                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    Log.d("Attendance", "Prn: " + at.getPrn() + ", Attended: " + at.getAttended() + ", Conducted: " + at.getConducted() + ", Percentage: " + at.getPercentage());
                    c.add(at);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//
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
        // Set the retrieved name and PRN number in the corresponding TextViews
        binding.txtName.setText("Name: "+name);
        binding.mailid.setText(mail);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.teacher_menu,menu);
        MenuBuilder m = (MenuBuilder) menu;
        //noinspection RestrictedApi
        m.setOptionalIconsVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logoutt:
                auth.signOut();
                startActivity(new Intent(TeacherActivity.this,loginT.class));
                finish();
                break;
            case R.id.attendance_pdf:
//               nkfneofweroow
                try {
                    // create a new document
                    Document document = new Document();

                    // create a file path for the PDF file
                    File filePath = new File(Environment.getExternalStorageDirectory().getPath()+"/Download/"+sub+"_"+System.currentTimeMillis()+".pdf");

                    // create a PDF writer
                    PdfWriter.getInstance(document, new FileOutputStream(filePath));

                    // open the document
                    document.open();

                    // create a table to display the attendance data
                    PdfPTable table = new PdfPTable(4);
                    table.addCell("PRN");
                    table.addCell("Lectures Present");
                    table.addCell("Lectures Conducted");
                    table.addCell("Percentage");

                    // add data to the table
                    for (attendance data : c) {
                        table.addCell(data.getPrn());
                        table.addCell(String.valueOf(data.getAttended()));
                        table.addCell(String.valueOf(data.getConducted()));
                        table.addCell(String.valueOf((int)(data.getAttended()/data.getConducted())*100));
                    }

                    // add the table to the document
                    document.add(table);

                    // close the document
                    document.close();

                    // show a toast message to indicate the PDF generation is complete
                    Toast.makeText(this, "PDF saved in Download folder", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.enmse:
                db.getReference("feedback").child("sub").child(sub).child("mseenabled").setValue(1);
                break;
            case R.id.enese:
                db.getReference("feedback").child("sub").child(sub).child("eseenabled").setValue(1);



        }
        return super.onOptionsItemSelected(item);
    }
}
package com.example.feedtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.feedtrack.databinding.ActivityFeedbackOptionsTBinding;
import com.example.feedtrack.model.attendance;
import com.example.feedtrack.model.exam;
import com.example.feedtrack.model.exam_ese;
import com.example.feedtrack.model.weekly;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class feedback_options_t extends AppCompatActivity {
ActivityFeedbackOptionsTBinding binding;
ArrayList<weekly> c=new ArrayList<>();
ArrayList<exam> c1=new ArrayList<>();
ArrayList<exam_ese> c2=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFeedbackOptionsTBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        String sub=getIntent().getStringExtra("sub");
        binding.weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Students").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        c.clear();
                        for(DataSnapshot snapshot1:snapshot.getChildren()){
                            weekly at=snapshot1.getValue(weekly.class);
                            String p=snapshot1.getKey();
                            at.setPrn(p);
                            String ans1=snapshot1.child("fourth").child(sub).child("weekly").child("How much did you understand in the last week?").getValue(String.class);
                            String ans2=snapshot1.child("fourth").child(sub).child("weekly").child("Were this week's lectures relevant to the course content?").getValue(String.class);
                            String ans3=snapshot1.child("fourth").child(sub).child("weekly").child("Did you feel engaged and interactive throughout the lecture?").getValue(String.class);
                            String ans4=snapshot1.child("fourth").child(sub).child("weekly").child("Were there any topics you would like to see covered in more detail?").getValue(String.class);
                            String ans5=snapshot1.child("fourth").child(sub).child("weekly").child("What suggestions would you like to give?").getValue(String.class);
                            at.setUnderstand(ans1);
                            at.setRelevant(ans2);
                            at.setInteractive(ans3);
                            at.setDetail(ans4);
                            at.setSuggestions(ans5);
                            Log.d("feed", "Prn: " + at.getPrn() + ", Understand: " + at.getUnderstand() + ", Relevant: " + at.getRelevant() + ", Interactive: " + at.getInteractive());
                            c.add(at);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                // pdf creation code

                try {
                    // create a new document
                    Document document = new Document();

                    // create a file path for the PDF file
                    File filePath = new File(Environment.getExternalStorageDirectory().getPath()+"/Download/weekly_"+sub+"_"+System.currentTimeMillis()+"_feedback.pdf");

                    // create a PDF writer
                    PdfWriter.getInstance(document, new FileOutputStream(filePath));

                    // open the document
                    document.open();

                    // create a table to display the attendance data
                    PdfPTable table = new PdfPTable(6);
                    table.addCell("PRN");
                    table.addCell("How much did you understand in the last week?");
                    table.addCell("Were this week's lectures relevant to the course content?");
                    table.addCell("Did you feel engaged and interactive throughout the lecture?");
                    table.addCell("Were there any topics you would like to see covered in more detail?");
                    table.addCell("What suggestions would you like to give?");

                    // add data to the table
                    for (weekly data : c) {
                        table.addCell(data.getPrn());
                        table.addCell(data.getUnderstand());
                        table.addCell(data.getRelevant());
                        table.addCell(data.getInteractive());
                        table.addCell(data.getDetail());
                        table.addCell(data.getSuggestions());
                    }

                    // add the table to the document
                    document.add(table);

                    // close the document
                    document.close();

                    // show a toast message to indicate the PDF generation is complete
                    Toast.makeText(getApplicationContext(), "PDF saved in Download folder", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        binding.mse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Students").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        c1.clear();
                        for(DataSnapshot snapshot1:snapshot.getChildren()){
                            exam at=snapshot1.getValue(exam.class);
                            String p=snapshot1.getKey();
                            at.setPrn(p);
                            String ans1=snapshot1.child("fourth").child(sub).child("mse").child("What was the difficulty level of the exam?").getValue(String.class);
                            String ans2=snapshot1.child("fourth").child(sub).child("mse").child("How much syllabus did the question paper cover?").getValue(String.class);
                            String ans3=snapshot1.child("fourth").child(sub).child("mse").child("Do you feel the exam accurately assessed your knowledge and understanding?").getValue(String.class);
                            String ans4=snapshot1.child("fourth").child(sub).child("mse").child("Is the course relevant to industry needs?").getValue(String.class);
                            String ans5=snapshot1.child("fourth").child(sub).child("mse").child("What suggestions would you like to give?").getValue(String.class);
                            at.setDifficulty(ans1);
                            at.setCover(ans2);
                            at.setUnderstanding(ans3);
                            at.setNeeds(ans4);
                            at.setSuggestions(ans5);
                            Log.d("feed", "Prn: " + at.getPrn() + ", Understand: " + at.getUnderstanding() + ", Difficulty: " + at.getDifficulty() + ", Industry Needs: " + at.getNeeds()+", Cover: "+at.getCover());
                            c1.add(at);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                // pdf creation code

                try {
                    // create a new document
                    Document document = new Document();

                    // create a file path for the PDF file
                    File filePath = new File(Environment.getExternalStorageDirectory().getPath()+"/Download/MSE_"+sub+"_"+System.currentTimeMillis()+"_feedback.pdf");

                    // create a PDF writer
                    PdfWriter.getInstance(document, new FileOutputStream(filePath));

                    // open the document
                    document.open();

                    // create a table to display the attendance data
                    PdfPTable table = new PdfPTable(6);
                    table.addCell("PRN");
                    table.addCell("What was the difficulty level of the exam?");
                    table.addCell("How much syllabus did the question paper cover?");
                    table.addCell("Do you feel the exam accurately assessed your knowledge and understanding?");
                    table.addCell("Is the course relevant to industry needs?");
                    table.addCell("What suggestions would you like to give?");

                    // add data to the table
                    for (exam data : c1) {
                        table.addCell(data.getPrn());
                        table.addCell(data.getDifficulty());
                        table.addCell(data.getCover());
                        table.addCell(data.getUnderstanding());
                        table.addCell(data.getNeeds());
                        table.addCell(data.getSuggestions());
                    }

                    // add the table to the document
                    document.add(table);

                    // close the document
                    document.close();

                    // show a toast message to indicate the PDF generation is complete
                    Toast.makeText(getApplicationContext(), "PDF saved in Download folder", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        binding.ese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Students").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        c1.clear();
                        for(DataSnapshot snapshot1:snapshot.getChildren()){
                            exam_ese at=snapshot1.getValue(exam_ese.class);
                            String p=snapshot1.getKey();
                            at.setPrn(p);
                            String ans1=snapshot1.child("fourth").child(sub).child("ese").child("What was the difficulty level of the exam?").getValue(String.class);
                            String ans2=snapshot1.child("fourth").child(sub).child("ese").child("How much syllabus did the question paper cover?").getValue(String.class);
                            String ans3=snapshot1.child("fourth").child(sub).child("ese").child("Do you feel the exam accurately assessed your knowledge and understanding?").getValue(String.class);
                            String ans4=snapshot1.child("fourth").child(sub).child("ese").child("Is the course relevant to industry needs?").getValue(String.class);
                            String ans5=snapshot1.child("fourth").child(sub).child("ese").child("What suggestions would you like to give?").getValue(String.class);
                            at.setDifficulty(ans1);
                            at.setCover(ans2);
                            at.setUnderstanding(ans3);
                            at.setNeeds(ans4);
                            at.setSuggestions(ans5);
                            Log.d("feed", "Prn: " + at.getPrn() + ", Understand: " + at.getUnderstanding() + ", Difficulty: " + at.getDifficulty() + ", Industry Needs: " + at.getNeeds()+", Cover: "+at.getCover());
                            c2.add(at);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                // pdf creation code

                try {
                    // create a new document
                    Document document = new Document();

                    // create a file path for the PDF file
                    File filePath = new File(Environment.getExternalStorageDirectory().getPath()+"/Download/ESE_"+sub+"_"+System.currentTimeMillis()+"_feedback.pdf");

                    // create a PDF writer
                    PdfWriter.getInstance(document, new FileOutputStream(filePath));

                    // open the document
                    document.open();

                    // create a table to display the attendance data
                    PdfPTable table = new PdfPTable(6);
                    table.addCell("PRN");
                    table.addCell("What was the difficulty level of the exam?");
                    table.addCell("How much syllabus did the question paper cover?");
                    table.addCell("Do you feel the exam accurately assessed your knowledge and understanding?");
                    table.addCell("Is the course relevant to industry needs?");
                    table.addCell("What suggestions would you like to give?");

                    // add data to the table
                    for (exam_ese data : c2) {
                        table.addCell(data.getPrn());
                        table.addCell(data.getDifficulty());
                        table.addCell(data.getCover());
                        table.addCell(data.getUnderstanding());
                        table.addCell(data.getNeeds());
                        table.addCell(data.getSuggestions());
                    }

                    // add the table to the document
                    document.add(table);

                    // close the document
                    document.close();

                    // show a toast message to indicate the PDF generation is complete
                    Toast.makeText(getApplicationContext(), "PDF saved in Download folder", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
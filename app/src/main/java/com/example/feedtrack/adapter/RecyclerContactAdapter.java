package com.example.feedtrack.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedtrack.R;
import com.example.feedtrack.model.ContactModel;

import java.util.ArrayList;


public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder>
{
    Context context;
    ArrayList<ContactModel>arrConcants;
    public RecyclerContactAdapter(Context context, ArrayList<ContactModel> arrcon)
    {
        this.context=context;
        this.arrConcants=arrcon;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(arrConcants.get(position).prn);


    }

    @Override
    public int getItemCount() {
        return arrConcants.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtName;
        public ViewHolder(View itemView) {
            super(itemView);

            txtName=itemView.findViewById(R.id.txtName);

        }
    }
}

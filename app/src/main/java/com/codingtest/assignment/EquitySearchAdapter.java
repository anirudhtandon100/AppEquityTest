package com.codingtest.assignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

public class EquitySearchAdapter extends RecyclerView.Adapter<EquitySearchAdapter.ViewHolder>{

    private Context context;
    private List<EquityInitialDetails> equityDetails;
    private ItemClickListener itemClickListener;

    EquitySearchAdapter(Context context, List<EquityInitialDetails> equityDetails){
        this.equityDetails = equityDetails;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View v = LayoutInflater.from(context).inflate(R.layout.recyclerviewitem, parent, false);
        return new ViewHolder(v);


    }

    public void onBindViewHolder(ViewHolder holder, int position){
        EquityInitialDetails eq = equityDetails.get(position);
        holder.name.setText(eq.name);


    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name;
        public ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);

        }

        public void onClick(View view){
            itemClickListener.onItemClick(view,getAdapterPosition());
        }

    }

    public int getItemCount(){
        return equityDetails.size();
    }

    public void update(List<EquityInitialDetails> eqDet){

        equityDetails.clear();
        equityDetails.addAll(eqDet);
        notifyDataSetChanged();

    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public interface ItemClickListener{
        public void onItemClick(View view, int position);
    }

}


package com.codingtest.assignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

public class EquityAdapter extends RecyclerView.Adapter<EquityAdapter.ViewHolder>{

    private Context context;
    private List<EquityDetails> equityDetails;
    private ItemClickListener itemClickListener;

    EquityAdapter(Context context, List<EquityDetails> equityDetails){
        this.equityDetails = equityDetails;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View v = LayoutInflater.from(context).inflate(R.layout.rviewitem, parent, false);
        return new ViewHolder(v);


    }

    public void onBindViewHolder(ViewHolder holder, int position){
        EquityDetails eq = equityDetails.get(position);
        if(eq.contractName==null){
            holder.name.setText("No contract name");
        }else{
            holder.name.setText(eq.contractName);
        }
        holder.symbol.setText(eq.symbol);
        holder.currency.setText(eq.currency);
        holder.pricingDate.setText(eq.pricingDate);


    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name;
        public TextView symbol;
        public TextView currency;
        public TextView pricingDate;
        public ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.name);
            symbol = itemView.findViewById(R.id.symbol);
            currency = itemView.findViewById(R.id.currency);
            pricingDate = itemView.findViewById(R.id.pricingDate);
            itemView.setOnClickListener(this);

        }

        public void onClick(View view){
            itemClickListener.onItemClick(view,getAdapterPosition());
        }

    }

    public int getItemCount(){
        return equityDetails.size();
    }

    public void update(List<EquityDetails> eqDet){

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

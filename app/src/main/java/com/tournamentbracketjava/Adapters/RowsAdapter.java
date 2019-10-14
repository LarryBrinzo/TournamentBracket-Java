package com.tournamentbracketjava.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tournamentbracketjava.Models.Annotations;
import com.tournamentbracketjava.Models.Rows;
import com.tournamentbracketjava.R;

import java.util.List;

public class RowsAdapter extends RecyclerView.Adapter<RowsAdapter.MyHolder>{

    private List<Rows> rows;
    private List<Annotations> annotations;
    private int cardWidth,Width;
    private Context context;

    public RowsAdapter(List<Rows> rows, List<Annotations> annotations, int cardWidth, int Width, Context context) {
        this.rows = rows;
        this.annotations = annotations;
        this.cardWidth = cardWidth;
        this.Width = Width;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.single_row_card,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.rowElemetsRecycle.setNestedScrollingEnabled(false);

        RowElemetsAdapter rowsElementsAdapter = new RowElemetsAdapter(rows.get(position).getItems().get(0),
                annotations, cardWidth, Width, context);
        RecyclerView.LayoutManager recycerows = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.rowElemetsRecycle.setLayoutManager(recycerows);
        holder.rowElemetsRecycle.setAdapter(rowsElementsAdapter);
    }

    @Override
    public int getItemCount() {
        int arr = 0;
        try{
            if(rows.size()!=0){
                arr=rows.size(); }
        }
        catch (Exception ignored){ }
        return arr;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        RecyclerView rowElemetsRecycle;

        MyHolder(View itemView) {
            super(itemView);
            rowElemetsRecycle = itemView.findViewById(R.id.rowelementsrecycle);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}


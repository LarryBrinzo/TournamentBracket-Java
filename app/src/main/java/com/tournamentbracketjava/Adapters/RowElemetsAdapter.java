package com.tournamentbracketjava.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tournamentbracketjava.Models.Annotations;
import com.tournamentbracketjava.Models.Items;
import com.bumptech.glide.Glide;
import com.tournamentbracketjava.R;
import java.util.List;

public class RowElemetsAdapter extends RecyclerView.Adapter<RowElemetsAdapter.MyHolder>{

    private List<Items> items;
    private List<Annotations> annotations;
    private int cardWidth,Width;
    private Context context;

    RowElemetsAdapter(List<Items> items, List<Annotations> annotations, int cardWidth, int Width, Context context) {
        this.items = items;
        this.annotations = annotations;
        this.cardWidth = cardWidth;
        this.Width = Width;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_element_card,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, @SuppressLint("RecyclerView") final int position) {

        int totalWidth= Width / getItemCount();
        holder.maincard.setMinWidth(totalWidth);

        if(items.isEmpty()) {
            holder.emptyCard.setVisibility(View.VISIBLE);
        }

        else if(items.get(position).getStyle().equals("round")){
            holder.roundCard.setVisibility(View.VISIBLE);

            holder.roundCard.getLayoutParams().width=cardWidth;

            holder.team1Img.getLayoutParams().width=cardWidth/5;
            holder.team2Img.getLayoutParams().width=cardWidth/5;

            holder.team1.setText(items.get(position).getLeftPrimaryScore());
            holder.team2.setText(items.get(position).getRightPrimaryScore());

            if(items.get(position).getLeftTeamID() == items.get(position).getWinnerTeamID()){
                holder.leftTeam.setBackground(ContextCompat.getDrawable(context, R.drawable.left_rounded_corner) );

                int teamPrimaryColor= Color.parseColor("#4f4e4e");

                LayerDrawable bgDrawable = (LayerDrawable) holder.leftTeam.getBackground();
                GradientDrawable lrCorner = (GradientDrawable) bgDrawable.findDrawableByLayerId(R.id.lr_corner);
                lrCorner.setColor(teamPrimaryColor);
                lrCorner.setStroke(Math.round(pxFromDp(context, 1f)),manipulateColor(teamPrimaryColor,0.8f));

                holder.team1.setTextColor(Color.parseColor("#ffffff"));
            }

            else{
                holder.rightTeam.setBackground(ContextCompat.getDrawable(context, R.drawable.right_rounded_corner) );

                int teamPrimaryColor= Color.parseColor("#4f4e4e");

                LayerDrawable bgDrawable = (LayerDrawable) holder.rightTeam.getBackground();
                GradientDrawable rr_corner = (GradientDrawable) bgDrawable.findDrawableByLayerId(R.id.rr_corner);
                rr_corner.setColor(teamPrimaryColor);
                rr_corner.setStroke(Math.round(pxFromDp(context, 1f)),manipulateColor(teamPrimaryColor,0.8f));

                holder.team2.setTextColor(Color.parseColor("#ffffff"));
            }

            for(int a=0;a<annotations.size();a++){

                if(annotations.get(a).getElementID().equals(items.get(position).getElementID())){

                    if(annotations.get(a).getEdge().equals("top")){

                        holder.annotationtop.setVisibility(View.VISIBLE);

                        if(annotations.get(a).getAlignment().equals("left"))
                            holder.annotationtop1.setText(annotations.get(a).getText());
                        else
                            holder.annotationtop2.setText(annotations.get(a).getText());
                    }

                    else{

                        holder.annotationbottom.setVisibility(View.VISIBLE);

                        if(annotations.get(a).getAlignment().equals("left"))
                            holder.annotationbottom1.setText(annotations.get(a).getText());
                        else
                            holder.annotationbottom2.setText(annotations.get(a).getText());
                    }

                }

            }
        }



        else if(items.get(position).getStyle().equals("stage")){
            holder.stageCard.setVisibility(View.VISIBLE);

            holder.stageCard.getLayoutParams().width=cardWidth;

            holder.matchName.setText(items.get(position).getName());
        }

        else if(items.get(position).getStyle().equals("final")){
            holder.finalCard.setVisibility(View.VISIBLE);

            holder.finalCard.getLayoutParams().width=cardWidth;

            if (context != null) {
                Glide.with(context).load(items.get(position).getTrophyRemoteImageURL()).into(holder.finalimg);
            }

        }

    }

    @Override
    public int getItemCount() {
        int arr = 0;
        try{
            if(items.size()==0){
                arr = 1; }
            else{ arr=items.size(); }
        }
        catch (Exception ignored){ }
        return arr;
    }

    class MyHolder extends RecyclerView.ViewHolder{

        LinearLayout roundCard, stageCard, finalCard, emptyCard, leftTeam, rightTeam, annotationtop,
                annotationbottom;
        TextView matchName, team1, team2, annotationtop1, annotationtop2, annotationbottom1,
                annotationbottom2;
        ImageView team1Img, team2Img, finalimg;
        ConstraintLayout maincard;


        MyHolder(View itemView) {
            super(itemView);
            roundCard = itemView.findViewById(R.id.roundcard);
            stageCard = itemView.findViewById(R.id.stagecard);
            emptyCard = itemView.findViewById(R.id.emptycard);
            maincard = itemView.findViewById(R.id.maincard);
            leftTeam = itemView.findViewById(R.id.leftteam);
            rightTeam = itemView.findViewById(R.id.rightteam);
            finalCard = itemView.findViewById(R.id.finalcard);
            matchName = itemView.findViewById(R.id.matchname);
            team1Img = itemView.findViewById(R.id.team1img);
            team2Img = itemView.findViewById(R.id.team2img);
            team1 = itemView.findViewById(R.id.team1);
            team2 = itemView.findViewById(R.id.team2);
            finalimg = itemView.findViewById(R.id.finalimg);
            annotationtop = itemView.findViewById(R.id.annotationtop);
            annotationbottom = itemView.findViewById(R.id.annotationbottom);
            annotationtop1 = itemView.findViewById(R.id.annotationtop1);
            annotationtop2 = itemView.findViewById(R.id.annotationtop2);
            annotationbottom1 = itemView.findViewById(R.id.annotationbottom1);
            annotationbottom2 = itemView.findViewById(R.id.annotationbottom2);
        }
    }

    private int manipulateColor(int color, Float factor){

        int a = Color.alpha(color),r = Math.round(Color.red(color) * factor),
                g = Math.round(Color.green(color) * factor), b = Math.round(Color.blue(color) * factor);

        return Color.argb(
                a,
                Math.min(r, 255),
                Math.min(g, 255),
                Math.min(b, 255)
        );
    }

    private Float pxFromDp(Context context,Float dp){
        return dp * context.getResources().getDisplayMetrics().density;
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



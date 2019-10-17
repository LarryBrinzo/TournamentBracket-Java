package com.tournamentbracketjava.Views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.View;
import com.tournamentbracketjava.Models.Rows;
import java.util.List;

import static java.lang.Math.abs;

@SuppressLint("ViewConstructor")
public class ConnectionsView extends View {

    Paint mPaint = new Paint();
    Path mPath;
    Context context;
    int screenHeight, screenWidth, starty;
    Pair<Integer,Integer> fromRowAndItemNumber;
    Pair<Integer,Integer> toRowAndItemNumber;
    List<Rows> rows;
    int cardWidth, inc;

    public ConnectionsView(Context context,String fromID, String toID, List<Rows> rows, int annotationcount,
                           int cardWidth, int inc, int winningTeamTextHeight, int height){
        super(context);
        this.context=context;
        this.rows = rows;
        this.cardWidth = cardWidth;
        this.inc = inc;

        fromRowAndItemNumber = returnRowAndItemNumber(fromID);
        toRowAndItemNumber = returnRowAndItemNumber(toID);

        mPaint.setAntiAlias(true);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#CECCCC"));
        mPaint.setStrokeWidth(4f);
        Float radius = 35.0f;
        CornerPathEffect corEffect = new CornerPathEffect(radius);
        mPaint.setPathEffect(corEffect);

        if(rows.get(fromRowAndItemNumber.first - 1).getItems().get(0).get(fromRowAndItemNumber.second - 1).getWinnerTeamID() ==
                rows.get(toRowAndItemNumber.first - 1).getItems().get(0).get(toRowAndItemNumber.second - 1).getLeftTeamID() ||
                rows.get(fromRowAndItemNumber.first - 1).getItems().get(0).get(fromRowAndItemNumber.second - 1).getWinnerTeamID() ==
                        rows.get(toRowAndItemNumber.first - 1).getItems().get(0).get(toRowAndItemNumber.second - 1).getRightTeamID()){

            int teamPrimaryColor= Color.parseColor("#4f4e4e");

            mPaint.setColor(manipulateColor(teamPrimaryColor,0.8f));

        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);

        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        starty = (int) ((screenHeight - (pxFromDp(context, 75f) * rows.size()) -
                        (pxFromDp(context, 20f) * annotationcount)) / 2);

        if(height > screenHeight)
            starty = (int) (pxFromDp(context, 10f) + winningTeamTextHeight);

        mPath = new Path();

        Pair<Integer,Integer> fromReach = findReach(fromID);
        Pair<Integer,Integer> toReach = findReach(toID);

        if( abs ((toReach.second - toReach.first) - (fromReach.second - fromReach.first) ) >= cardWidth-10 ){
            if(rows.get(fromRowAndItemNumber.first-1).getItems().get(0).size()==
                    rows.get(toRowAndItemNumber.first-1).getItems().get(0).size())
                straightConnection();
            else
                normalConnectionDraw();
        }

        else
            slantConnection();
    }

    private void straightConnection(){

        int fromy = (int) (starty + pxFromDp(context, 75f) * fromRowAndItemNumber.first +
                        pxFromDp(context, 20f));

        int toy = (int) (fromy + (int) (pxFromDp(context, 75f) * (abs(toRowAndItemNumber.first-fromRowAndItemNumber.first)-1)) +
                (pxFromDp(context, 15f)));

        int fromElementSize = 60;

        if(!rows.get(fromRowAndItemNumber.first - 1).getItems().get(0).isEmpty())
            fromElementSize = screenWidth / rows.get(fromRowAndItemNumber.first - 1).getItems().get(0).size();

        int fromx = (fromElementSize*fromRowAndItemNumber.second) - (fromElementSize/2);

        Point p1 = new Point(fromx,fromy);
        Point p2 = new Point(fromx,toy);

        mPath.moveTo(p1.x, p1.y);
        mPath.lineTo(p2.x, p2.y);
        mPath.lineTo(p2.x, p2.y);
    }

    private void normalConnectionDraw(){

        if(rows.get(toRowAndItemNumber.first - 1).getItems().get(0).size() >
                rows.get(fromRowAndItemNumber.first - 1).getItems().get(0).size()){
            fromLowerToHigherRowItems();
        }

        else {
            fromHigherToLowerRowItems();
        }

    }

    private void fromLowerToHigherRowItems(){

        int fromElementSize = 60, toElementSize = 60;

        if(!rows.get(fromRowAndItemNumber.first - 1).getItems().get(0).isEmpty())
            fromElementSize = screenWidth / rows.get(fromRowAndItemNumber.first - 1).getItems().get(0).size();

        if(!rows.get(toRowAndItemNumber.first - 1).getItems().get(0).isEmpty())
            toElementSize = screenWidth / rows.get(toRowAndItemNumber.first - 1).getItems().get(0).size();

        int fromx = (fromElementSize*(fromRowAndItemNumber.second-1)) + ((fromElementSize - cardWidth)/2);

        if(toRowAndItemNumber.second%2==0)
            fromx+=cardWidth;

        int tox;

        if(toRowAndItemNumber.second%2==0)
            tox = (toElementSize*toRowAndItemNumber.second) - (toElementSize/2);
        else
            tox = (toElementSize*(toRowAndItemNumber.second-1)) + (toElementSize/2);

        if(rows.get(toRowAndItemNumber.first - 1).getItems().get(0).size() > 2){

            if(toRowAndItemNumber.second%2==0)
                tox +=cardWidth/14;
            else
                tox -=cardWidth/14;
        }

        if(toRowAndItemNumber.second%2==0)
            tox += inc;
        else tox -= inc;

        int toy = (int) (starty + pxFromDp(context, 75f) * (toRowAndItemNumber.first - 1) +
                pxFromDp(context, 35f));

        int diffy = (int) (
                pxFromDp(context, 75f) * ((toRowAndItemNumber.first-fromRowAndItemNumber.first)-1) +
                        pxFromDp(context, 45f));

        Point p1 = new Point(fromx,toy - diffy);
        Point p2 = new Point(tox,toy - diffy);
        Point p3 = new Point(tox,toy);

        mPath.moveTo(p1.x, p1.y);
        mPath.lineTo(p2.x, p2.y);
        mPath.lineTo(p3.x, p3.y);
        mPath.lineTo(p3.x, p3.y);
    }

    private void fromHigherToLowerRowItems(){

        int fromElementSize = 60, toElementSize = 60;

        if(!rows.get(fromRowAndItemNumber.first - 1).getItems().get(0).isEmpty())
            fromElementSize = screenWidth / rows.get(fromRowAndItemNumber.first - 1).getItems().get(0).size();

        if(!rows.get(toRowAndItemNumber.first - 1).getItems().get(0).isEmpty())
            toElementSize = screenWidth / rows.get(toRowAndItemNumber.first - 1).getItems().get(0).size();

        int fromy = (int) (starty + pxFromDp(context, 75f) * fromRowAndItemNumber.first +
                pxFromDp(context, 20f));

        int fromx;

        if(fromRowAndItemNumber.second%2==0)
            fromx = (fromElementSize*fromRowAndItemNumber.second) - (fromElementSize/2);
        else
            fromx = (fromElementSize*(fromRowAndItemNumber.second-1)) + (fromElementSize/2);

        if(rows.get(fromRowAndItemNumber.first - 1).getItems().get(0).size() > 2){

            if(fromRowAndItemNumber.second%2==0)
                fromx +=cardWidth/14;
            else
                fromx -=cardWidth/14;
        }

        int tox = (toElementSize*(toRowAndItemNumber.second-1)) + ((toElementSize - cardWidth)/2);

        if(fromRowAndItemNumber.second%2==0)
            tox+=cardWidth;

        if(fromRowAndItemNumber.second%2==0)
            fromx += inc;
        else fromx -= inc;

        int diffy = (int) (pxFromDp(context, 15f) *
                (toRowAndItemNumber.first-fromRowAndItemNumber.first)+
                pxFromDp(context, 60f) * ((toRowAndItemNumber.first-fromRowAndItemNumber.first)-1) +
                pxFromDp(context, 30f));

        Point p1 = new Point(fromx,fromy);
        Point p2 = new Point(fromx,fromy + diffy);
        Point p3 = new Point(tox,fromy + diffy);

        mPath.moveTo(p1.x, p1.y);
        mPath.lineTo(p2.x, p2.y);
        mPath.lineTo(p3.x, p3.y);
        mPath.lineTo(p3.x, p3.y);
    }

    private void slantConnection(){

        int fromy = (int) (starty + pxFromDp(context, 75f) * fromRowAndItemNumber.first +
                        pxFromDp(context, 20f));

        int toy = (int) (starty + pxFromDp(context, 75f) * (toRowAndItemNumber.first-1) +
                        pxFromDp(context, 36f));


        int fromElementSize = 60, toElementSize = 60;

        if(!rows.get(fromRowAndItemNumber.first - 1).getItems().get(0).isEmpty())
            fromElementSize = screenWidth / rows.get(fromRowAndItemNumber.first - 1).getItems().get(0).size();

        if(!rows.get(toRowAndItemNumber.first - 1).getItems().get(0).isEmpty())
            toElementSize = screenWidth / rows.get(toRowAndItemNumber.first - 1).getItems().get(0).size();

        int fromx = (fromElementSize*fromRowAndItemNumber.second) - (fromElementSize/2);
        int tox = (toElementSize*toRowAndItemNumber.second) - (toElementSize/2);

        Point p1 = new Point(fromx,fromy);
        Point p2 = new Point(fromx,fromy + 40);
        Point p3 = new Point(tox,toy - 40);
        Point p4 = new Point(tox,toy);

        mPath.moveTo(p1.x, p1.y);
        mPath.lineTo(p2.x, p2.y);
        mPath.lineTo(p3.x, p3.y);
        mPath.lineTo(p4.x, p4.y);
        mPath.lineTo(p4.x, p4.y);
    }

    private Pair<Integer,Integer> findReach(String elementID){

        Pair<Integer,Integer> rowAndItemNumber = returnRowAndItemNumber(elementID);
        int elementSize = 60;

        if(!rows.get(rowAndItemNumber.first - 1).getItems().get(0).isEmpty())
            elementSize = screenWidth / rows.get(rowAndItemNumber.first - 1).getItems().get(0).size();

        int startx = elementSize*(rowAndItemNumber.second-1);
        int endx = startx+elementSize;

        return new Pair(startx,endx);
    }

    private Pair<Integer,Integer> returnRowAndItemNumber(String elementID){

        Pair<Integer,Integer> rowAndItem = null;

        for(int r=0;r<rows.size();r++){
            for(int i=0;i<rows.get(r).getItems().get(0).size();i++){

                if(rows.get(r).getItems().get(0).get(i).getElementID().equals(elementID)){
                    rowAndItem = new Pair(r+1,i+1);
                    return rowAndItem;
                }
            }
        }

        return rowAndItem;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        getParent().requestDisallowInterceptTouchEvent(true);
        canvas.drawPath(mPath, mPaint);
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

}


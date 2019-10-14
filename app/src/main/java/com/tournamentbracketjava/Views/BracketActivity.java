package com.tournamentbracketjava.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.tournamentbracketjava.Adapters.RowsAdapter;
import com.tournamentbracketjava.Contracts.BracketInterface;
import com.tournamentbracketjava.Models.Annotations;
import com.tournamentbracketjava.Models.BracketActivityModel;
import com.tournamentbracketjava.Models.Connections;
import com.tournamentbracketjava.Models.Rows;
import com.tournamentbracketjava.R;

import java.util.List;

import static java.lang.StrictMath.max;

public class BracketActivity extends AppCompatActivity implements BracketInterface.View{

    private BracketActivityModel bracketModel;
    private RecyclerView bracketRecycle;
    private List<Rows> rows;
    private TextView winningTeam;
    private List<Annotations> annotations;
    private ConstraintLayout layout;
    private int cardWidth = 0;
    private int width = 0;
    private int height = 0;
    private int annotationcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_barcket);

        bracketModel = new BracketActivityModel(getApplicationContext());
        bracketRecycle = findViewById(R.id.bracketrecycle);
        winningTeam = findViewById(R.id.winningteam);
        layout = findViewById(R.id.layout);

        bracketRecycle.setNestedScrollingEnabled(false);

        initView();
    }

    private void initView(){
        setWinningTeam();
        setRows();
    }


    @Override
    public void setRows() {

        rows = bracketModel.getRows();

        int maxItem=0;

        for(int r=0;r<rows.size();r++)
            maxItem = max(maxItem,rows.get(r).getItems().get(0).size());

        for(int r=0;r<rows.size();r++){
            if(rows.get(r).getItems().get(0).size() == maxItem)
                annotationcount++;
        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = (int) (displayMetrics.widthPixels - pxFromDp(getApplicationContext(), 4f));
        height = displayMetrics.heightPixels;

        cardWidth = width / maxItem;
        cardWidth -= pxFromDp(getApplicationContext(), 6f);

        setAnnotations();
    }

    @Override
    public void setAnnotations() {
        annotations = bracketModel.getAnnotations();

        adapterSetup();
        setConnections();
    }

    @Override
    public void setConnections() {

        List<Connections> connections = bracketModel.getConnections();

        winningTeam.measure(0, 0);
        int winningTeamTextHeight= winningTeam.getMeasuredHeight();

        height= (int) max(height,(pxFromDp(getApplicationContext(), 75f) * rows.size()) +
                (pxFromDp(getApplicationContext(), 20f) * annotationcount) +
                pxFromDp(getApplicationContext(), 50f) + winningTeamTextHeight);

        for(int c=0;c<connections.size();c++){

            Connections connection = connections.get(c);

            for(int t=0;t<connection.getToElementIDs().size();t++){

                ConnectionsView connectionsView = new ConnectionsView(this,
                        connection.getFromElementID(),connection.getToElementIDs().get(t),
                        rows,annotationcount,cardWidth,(cardWidth/6)*t,winningTeamTextHeight,height);

                layout.addView(connectionsView,width,height);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setWinningTeam() {
        int winningTeamName = bracketModel.getWinningTeam();

        if(winningTeamName != 0)
            winningTeam.setText(Integer.toString(winningTeamName));
    }

    private void adapterSetup(){
        RowsAdapter rowsAdapter = new RowsAdapter(rows, annotations, cardWidth, width, getApplicationContext());
        RecyclerView.LayoutManager recycleRow = new GridLayoutManager(getApplicationContext(), 1);
        bracketRecycle.setLayoutManager(recycleRow);
        bracketRecycle.setItemAnimator( new DefaultItemAnimator());
        bracketRecycle.setAdapter(rowsAdapter);
    }

    private Float pxFromDp(Context context,Float dp){
        return dp * context.getResources().getDisplayMetrics().density;
    }
}

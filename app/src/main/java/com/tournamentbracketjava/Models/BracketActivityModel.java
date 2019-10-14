package com.tournamentbracketjava.Models;

import android.content.Context;
import com.tournamentbracketjava.Contracts.BracketInterface;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.io.InputStream;

public class BracketActivityModel implements BracketInterface.Model{

    private Context context;
    private GridDataClass finalGridsValues;

    public BracketActivityModel(Context context){
        this.context=context;
        String f = readJSONFromAsset();
        Gson gson = new Gson();
        finalGridsValues = gson.fromJson(f, GridDataClass.class);
    }

    @Override
    public int getWinningTeam() {
        int rowSize = finalGridsValues.getC().getFinalsGrids().get(0).getRows().size();
        return finalGridsValues.getC().getFinalsGrids().get(0).getRows().get(rowSize - 1).getItems().get(0).get(0).getWinnerTeamID();
    }

    @Override
    public List<Rows> getRows() {
        return finalGridsValues.getC().getFinalsGrids().get(0).getRows();
    }

    @Override
    public List<Annotations> getAnnotations() {
        return finalGridsValues.getC().getFinalsGrids().get(0).getAnnotations();
    }

    @Override
    public List<Connections> getConnections() {
        return finalGridsValues.getC().getFinalsGrids().get(0).getConnections();
    }

    private String readJSONFromAsset() {
        String json;
        try {
            assert context != null;
            InputStream is = context.getAssets().open("bracket.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}

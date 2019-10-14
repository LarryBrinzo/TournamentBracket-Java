package com.tournamentbracketjava.Models;

import java.util.List;

public class C {

    private List<FinalsGrids> finalsGrids;
    private String defaultCompetitionID="";

    public List<FinalsGrids> getFinalsGrids() {return finalsGrids;}
    public void setFinalsGrids(List<FinalsGrids> finalsGrids) {this.finalsGrids=finalsGrids;}

    public String getDefaultCompetitionID() {return defaultCompetitionID;}
    public void setDefaultCompetitionID(String defaultCompetitionID) {
        this.defaultCompetitionID=defaultCompetitionID;}
}

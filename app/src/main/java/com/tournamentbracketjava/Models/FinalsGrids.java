package com.tournamentbracketjava.Models;

import java.util.List;

public class FinalsGrids {

    private List<Rows> rows;
    private List<Annotations> annotations;
    private List<Connections> connections;
    private String competitionID="";
    private String competitionName="";

    public List<Rows> getRows() {return rows;}
    public void setRows(List<Rows> rows) {this.rows=rows;}

    public List<Annotations> getAnnotations() {return annotations;}
    public void setAnnotations(List<Annotations> annotations) {this.annotations=annotations;}

    public List<Connections> getConnections() {return connections;}
    public void setConnections(List<Connections> connections) {this.connections=connections;}

    public String getCompetitionID() {return competitionID;}
    public void setCompetitionID(String competitionID) {this.competitionID=competitionID;}

    public String getCompetitionName() {return competitionName;}
    public void setCompetitionName(String competitionName) {this.competitionName=competitionName;}
}

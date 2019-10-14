package com.tournamentbracketjava.Models;

import java.util.List;

public class Connections {

    private String fromElementID="";
    private List<String> toElementIDs;

    public String getFromElementID() {return fromElementID;}
    public void setFromElementID(String fromElementID) {this.fromElementID=fromElementID;}

    public List<String> getToElementIDs() {return toElementIDs;}
    public void setToElementIDs(List<String> toElementIDs) {this.toElementIDs=toElementIDs;}
}

package com.tournamentbracketjava.Models;

import java.util.List;

public class Rows {

    private String elementID="";
    private List<List<Items>> items;

    public String getElementID() {return elementID;}
    public void setElementID(String elementID) {this.elementID=elementID;}

    public List<List<Items>> getItems() {return items;}
    public void setItems(List<List<Items>> items) {this.items=items;}
}

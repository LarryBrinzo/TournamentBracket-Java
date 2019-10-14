package com.tournamentbracketjava.Models;

public class Items {

    private String elementID="", style="", name="", trophyRemoteImageURL="", leftPrimaryScore="",
            rightPrimaryScore="";
    private int leftTeamID=0, rightTeamID=0, winnerTeamID=0;

    public String getElementID() {return elementID;}
    public void setElementID(String elementID) {this.elementID=elementID;}

    public String getStyle() {return style;}
    public void setStyle(String style) {this.style=style;}

    public String getName() {return name;}
    public void setName(String name) {this.name=name;}

    public String getTrophyRemoteImageURL() {return trophyRemoteImageURL;}
    public void setTrophyRemoteImageURL(String trophyRemoteImageURL) {
        this.trophyRemoteImageURL=trophyRemoteImageURL;}

    public String getLeftPrimaryScore() {return leftPrimaryScore;}
    public void setLeftPrimaryScore(String leftPrimaryScore) {this.leftPrimaryScore=leftPrimaryScore;}

    public String getRightPrimaryScore() {return rightPrimaryScore;}
    public void setRightPrimaryScore(String rightPrimaryScore) {
        this.rightPrimaryScore=rightPrimaryScore;}

    public int getLeftTeamID() {return leftTeamID;}
    public void setLeftTeamID(int leftTeamID) {this.leftTeamID=leftTeamID;}

    public int getRightTeamID() {return rightTeamID;}
    public void setRightTeamID(int rightTeamID) {this.rightTeamID=rightTeamID;}

    public int getWinnerTeamID() {return winnerTeamID;}
    public void setWinnerTeamID(int winnerTeamID) {this.winnerTeamID=winnerTeamID;}

}

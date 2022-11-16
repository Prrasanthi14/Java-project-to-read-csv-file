package com.iplproject;

public class Delivery {

    private String matchID;
    private String bowlingTeam;
    private String extraRuns;
    private String bowler;
    private String totalruns;

//    public Delivery(String id, String bowlingTeam, String extraRuns, String bowler, String total_runs) {
//        this.id = id;
//        this.bowlingTeam = bowlingTeam;
//        this.extraRuns = extraRuns;
//        this.bowler=bowler;
//        this.totalruns=total_runs;
//    }

    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
        this.matchID = matchID;
    }

    public String getBowlingTeam() {
        return bowlingTeam;
    }

    public void setBowlingTeam(String bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
    }

    public String getExtraRuns() {
        return extraRuns;
    }

    public void setExtraRuns(String extraRuns) {
        this.extraRuns = extraRuns;
    }

    public String getBowler() {
        return bowler;
    }

    public void setBowler(String bowler) {
        this.bowler = bowler;
    }

    public String getTotalruns() {
        return totalruns;
    }

    public void setTotalRuns(String totalruns) {
        this.totalruns = totalruns;
    }
}


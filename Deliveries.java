public class Deliveries {

    String id;
    String bowling_team;
    String extra_runs;
    String bowler;
    String totalruns;
    public Deliveries(String id, String bowling_team, String extra_runs, String bowler, String total_runs) {
        this.id = id;
        this.bowling_team = bowling_team;
        this.extra_runs = extra_runs;
        this.bowler=bowler;
        this.totalruns=total_runs;
    }



    //getters
    public String getId() {
        return id;
    }

    public String getBowling_team() {
        return bowling_team;
    }

    public String getExtra_runs() {
        return extra_runs;
    }
    public String getBowler() {
        return bowler;
    }
    public String getTotalruns() {
        return totalruns;
    }


//setters

    public void setId(String id) {
        this.id = id;
    }

    public void setBowling_team(String bowling_team) {
        this.bowling_team = bowling_team;
    }
    public void setBowler(String bowler) {
        this.bowler = bowler;
    }

    public void setTotalruns(String totalruns) {
        this.totalruns = totalruns;
    }

    public void setExtra_runs(String extra_runs) {
        this.extra_runs = extra_runs;
    }
}

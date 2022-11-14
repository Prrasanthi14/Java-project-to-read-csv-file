public  class Matches {
    String playerofmatch;
    String id;
    String season;
    String city;
    String date;
    String winner;

 public  Matches(String id, String season, String city, String date, String winner, String playerofmatch) {
        this.id = id;
        this.season = season;
        this.city = city;
        this.date = date;
        this.winner = winner;
        this.playerofmatch=playerofmatch;

    }


    //setters
    public void setSeason(String season) {
        this.season = season;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setPlayerofmatch(String playerofmatch) {
        this.playerofmatch = playerofmatch;
    }

//getters

    public String getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getSeason() {
        return season;
    }


    public String getPlayerofmatch() {
        return playerofmatch;
    }

    public String getWinner() {
        return winner;
    }





    public String getDate() {
        return date;
    }
}

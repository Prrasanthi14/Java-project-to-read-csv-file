package com.iplproject;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {
    private static final int MATCH_ID = 0;
    private static final int MATCH_SEASON = 1;
    private static final int MATCH_WINNER = 10;
    private static final int MATCH_PLAYEROFMATCH = 13;
    private static final int DELIVERY_MATCHID = 0;
    private static final int DELIVERY_BOWLINGTEAM = 3;
    private static final int DELIVERY_EXTRARUNS = 16;
    private static final int DELIVERY_BOWLER = 8;
    private static final int DELIVERY_TOTALRUNS = 17;


    public static void main(String[] args) {

        List<Match> matchesPlayed = readMatchData();
        List<Delivery> deliveryData = readDeliveryData();
        boolean loop = true;
        do {
            System.out.println("\nEnter the question number (1-5) or 6 to quit");
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    getSeasons(matchesPlayed);
                    break;
                case 2:
                    getTeams(matchesPlayed);
                    break;
                case 3:
                    getExtraRuns(matchesPlayed, deliveryData);
                    break;
                case 4:
                    getEconomicPlayer(matchesPlayed, deliveryData);
                    break;
                case 5:
                    getManOfMatch(matchesPlayed);
                    break;
                case 6:
                    loop = false;
                default:
                    break;
            }
        } while (loop);
    }


    public static List<Match> readMatchData() {
        List<Match> matchesByRows = new ArrayList<>();
        try {
            File matchesFile = new File("./matches.csv");
            FileReader frMatch = new FileReader(matchesFile);
            BufferedReader brMatch = new BufferedReader(frMatch);
            String str = "";
            int i = 0;
            while ((str = brMatch.readLine()) != null) {
                if (i != 0) {
                    List<String> fields = new ArrayList<String>(Arrays.asList(str.split(",")));

                    String id = fields.get(MATCH_ID);
                    String season = fields.get(MATCH_SEASON);
                    String winner = fields.get(MATCH_WINNER);
                    String playerOfMatch = fields.get(MATCH_PLAYEROFMATCH);

                    Match match = new Match();
                    match.setId(id);
                    match.setSeason(season);
                    match.setWinner(winner);
                    match.setPlayerOfMatch(playerOfMatch);
                    
                    matchesByRows.add(match);
                }
                i++;

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return matchesByRows;
    }


    public static List<Delivery> readDeliveryData() {
        List<Delivery> deliveriesByRows = new ArrayList<>();
        try {
            File deliveryFile = new File("./deliveries.csv");
            FileReader frDelivery = new FileReader(deliveryFile);
            BufferedReader brDelivery = new BufferedReader(frDelivery);

            String str = "";
            int i = 0;
            while ((str = brDelivery.readLine()) != null) {
                if (i != 0) {
                    List<String> fields = new ArrayList<String>(Arrays.asList(str.split(",")));


                    String matchID = fields.get(DELIVERY_MATCHID);
                    String bowlingTeam = fields.get(DELIVERY_BOWLINGTEAM);
                    String extraRuns = fields.get(DELIVERY_EXTRARUNS);
                    String bowler = fields.get(DELIVERY_BOWLER);
                    String totalRuns = fields.get(DELIVERY_TOTALRUNS);


                    Delivery delivery = new Delivery();
                    delivery.setMatchID(matchID);
                    delivery.setBowlingTeam(bowlingTeam);
                    delivery.setExtraRuns(extraRuns);
                    delivery.setBowler(bowler);
                    delivery.setTotalRuns(totalRuns);

                    deliveriesByRows.add(delivery);
                }
                i++;

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return deliveriesByRows;
    }


    static void getSeasons(List<Match> matchesPlayed) {
        Map<String, Integer> matchCounts = new HashMap<String, Integer>();
        for (Match item : matchesPlayed) {
            if (item.getSeason() != null) {
                String year = item.getSeason();
                Integer j = matchCounts.get(year);
                matchCounts.put(item.getSeason(), (j == null) ? 1 : j + 1);
            }
        }
        System.out.println("\n\tNumber of matches played per year of all the years in IPL.\n");
        for (Map.Entry yearcount : matchCounts.entrySet()) {
            System.out.println("Number of matches played in the year " + yearcount.getKey() + " is: " + yearcount.getValue());
        }
    }


    static void getTeams(List<Match> matchesplayed) {
        Map<String, Integer> teamwincount = new HashMap<String, Integer>();
        for (Match item1 : matchesplayed) {
            if (item1.getWinner().length() > 0) {
                String team = item1.getWinner();
                Integer j = teamwincount.get(team);
                teamwincount.put(item1.getWinner(), (j == null) ? 1 : j + 1);
            } else {
                break;
            }
        }
        System.out.println("\n\tNumber of matches won of all teams over all the years of IPL\n");
        for (Map.Entry wincount : teamwincount.entrySet()) {
            System.out.println("Number of matches won by " + wincount.getKey() + " over all the years of IPL is: " + wincount.getValue());
        }
    }


    static void getExtraRuns(List<Match> matchesPlayed, List<Delivery> deliveryData) {

        Map<String, Integer> extraRuns = new HashMap<String, Integer>();
        List<String> ids = new ArrayList<>();

        matchesPlayed.remove(0);
        for (Match item : matchesPlayed) {
            if (item.getSeason().equals("2016")) {
                ids.add(item.getId());
            }
        }

        for (Delivery d : deliveryData) {
            if (ids.contains(d.getMatchID())) {
                if (extraRuns.containsKey(d.getBowlingTeam())) {
                    int containruns = extraRuns.get(d.getBowlingTeam());
                    int runs = Integer.parseInt(d.getExtraRuns());
                    extraRuns.put(d.getBowlingTeam(), containruns + runs);
                }
            } else {
                extraRuns.put(d.getBowlingTeam(), Integer.parseInt(d.getExtraRuns()));
            }
        }
        System.out.println("\n\tFor the year 2016 get the extra runs conceded per team\n");
        for (Map.Entry extra : extraRuns.entrySet()) {
            System.out.println("The extra runs conceded by " + extra.getKey() + " is: " + extra.getValue());
        }
    }


    static void getEconomicPlayer(List<Match> matchesPlayed, List<Delivery> deliveryData) {

        List<String> ids = new ArrayList<>();

        matchesPlayed.remove(0);
        for (Match item : matchesPlayed) {
            if (item.getSeason().equals("2015")) {
                ids.add(item.getId());
            }
        }

        Map<String, Integer> countofBowlers = new HashMap<String, Integer>();
        Map<String, Integer> totalRunsPerBowler = new HashMap<>();
        Map<String, Double> oversCount = new HashMap<>();

        Map<String, Double> economicPlayers = new HashMap<>();

        int i = 0;
        for (Delivery bc : deliveryData) {
            if (ids.contains(bc.getMatchID())) {
                String bowlerName = bc.getBowler();
                if (countofBowlers.containsKey(bowlerName)) {
                    int j = countofBowlers.get(bowlerName);
                    countofBowlers.put(bc.getBowler(), j + 1);
                } else {
                    countofBowlers.put(bc.getBowler(), 1);
                }
            }
            i++;
        }

        for (Delivery trb : deliveryData) {
            if (ids.contains(trb.getMatchID())) {
                String bowlerName = trb.getBowler();
                if (totalRunsPerBowler.containsKey(bowlerName)) {
                    int j = totalRunsPerBowler.get(bowlerName);
                    totalRunsPerBowler.put(trb.getBowler(), j + Integer.parseInt(trb.getTotalruns()));

                } else {
                    totalRunsPerBowler.put(trb.getBowler(), Integer.parseInt(trb.getTotalruns()));
                }
                if (totalRunsPerBowler.containsKey(bowlerName)) {
                    int j = totalRunsPerBowler.get(bowlerName);
                    double overs = j;
                    int q = (int) overs / 6;
                    double r = (overs % 6) / 3;
                    double ans = BigDecimal.valueOf(r + q).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    oversCount.put(trb.getBowler(), ans);
                } else {
                    oversCount.put(trb.getBowler(), Double.parseDouble(trb.getTotalruns()));
                }

            }
        }

        for (Delivery ec : deliveryData) {
            if (ec.getBowler() != null) {
                String bowlerName = ec.getBowler();
                if (oversCount.containsKey(bowlerName)) {
                    double runs = totalRunsPerBowler.get(bowlerName);
                    double bowlerOver = oversCount.get(bowlerName);
                    double playerEconomyCalc = runs / bowlerOver;
                    double ans = Double.parseDouble(String.format("%.2f", playerEconomyCalc));
                    economicPlayers.put(bowlerName, ans);

                }
            }
        }
        System.out.println("\n\tThe top Economical Bowlers of 2015 ");

        List<Map.Entry<String,Double>> sortedEconomicPlayers=new ArrayList<Map.Entry<String,Double>>(economicPlayers.entrySet());
        Collections.sort(sortedEconomicPlayers, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> player1, Map.Entry<String, Double> player2) {
                return player1.getValue().compareTo(player2.getValue());
            }
        });

        System.out.println("\n\tDisplaying top 10 economical players\n");
        int j=1;
        for (Map.Entry<String, Double> players:sortedEconomicPlayers) {
            if (j<=10) {
                System.out.println("PLayer name:"+players.getKey()+" "+players.getValue());
            }
            else{break;}
            j++;
        }

    }


    static void getManOfMatch(List<Match> matchesplayed) {
        Map<String, Integer> manOfMatches = new HashMap<String, Integer>();
        for (Match item1 : matchesplayed) {
            if (item1.getPlayerOfMatch().length() > 0) {
                String names = item1.getPlayerOfMatch();
                Integer j = manOfMatches.get(names);
                manOfMatches.put(item1.getPlayerOfMatch(), (j == null) ? 1 : j + 1);
            } else {
                break;
            }
        }
        System.out.println("\n\tNumber of times the players were the man of the match in IPL.\n");
        for (Map.Entry count : manOfMatches.entrySet()) {
            System.out.println("Number of times " + count.getKey() + " had been the man of the match is: " + count.getValue());
        }
    }


}
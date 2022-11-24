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
        List<Match> matches = getMatchesData();
        List<Delivery> deliveries = getDeliveriesData();

        findNoOfMatchesPlayedInAYear(matches);

        findNoOfMatchesWonByTeams(matches);

        findExtraRunsConcededPerTeam2016(matches, deliveries);

        findEconomicPlayer(matches, deliveries);

        findManOfMatch(matches);
    }


    public static List<Match> getMatchesData() {
        List<Match> matches = new ArrayList<>();
        try {
            File macthFile = new File("./matches.csv");
            FileReader fileReader = new FileReader(macthFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str = "";
            int i = 0;
            while ((str = bufferedReader.readLine()) != null) {
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

                    matches.add(match);
                }
                i++;
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return matches;
    }

    public static List<Delivery> getDeliveriesData() {
        List<Delivery> deliveriesByRows = new ArrayList<>();
        try {
            File deliveryFile = new File("./deliveries.csv");
            FileReader deliveryReader = new FileReader(deliveryFile);
            BufferedReader deliveryBufferedReader = new BufferedReader(deliveryReader);

            String str = "";
            int i = 0;
            while ((str = deliveryBufferedReader.readLine()) != null) {
                if (i != 0) {
                    List<String> field = new ArrayList<String>(Arrays.asList(str.split(",")));

                    String matchID = field.get(DELIVERY_MATCHID);
                    String bowlingTeam = field.get(DELIVERY_BOWLINGTEAM);
                    String extraRuns = field.get(DELIVERY_EXTRARUNS);
                    String bowler = field.get(DELIVERY_BOWLER);
                    String totalRuns = field.get(DELIVERY_TOTALRUNS);

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
            deliveryBufferedReader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return deliveriesByRows;
    }

    static void findNoOfMatchesPlayedInAYear(List<Match> matchesPlayed) {
        Map<String, Integer> matchesCounts = new HashMap<String, Integer>();
        for (Match item : matchesPlayed) {
            if (item.getSeason() != null) {
                String year = item.getSeason();
                Integer j = matchesCounts.get(year);
                matchesCounts.put(item.getSeason(), (j == null) ? 1 : j + 1);
            }
        }
        for (Map.Entry yearCount : matchesCounts.entrySet()) {
            System.out.println("Number of matches played in the year " + yearCount.getKey() + " is: " + yearCount.getValue());
        }
    }

    static void findNoOfMatchesWonByTeams(List<Match> matchesplayed) {
        Map<String, Integer> teamsWinCount = new HashMap<String, Integer>();
        for (Match item1 : matchesplayed) {
            if (item1.getWinner().length() > 0) {
                String team = item1.getWinner();
                Integer j = teamsWinCount.get(team);
                teamsWinCount.put(item1.getWinner(), (j == null) ? 1 : j + 1);
            } else {
                break;
            }
        }
        for (Map.Entry winCount : teamsWinCount.entrySet()) {
            System.out.println("Number of matches won by " + winCount.getKey() + " over all the years of IPL is: " + winCount.getValue());
        }
    }

    static void findExtraRunsConcededPerTeam2016(List<Match> matches, List<Delivery> deliveryData) {

        Map<String, Integer> extraRuns = new HashMap<String, Integer>();
        List<String> ids = new ArrayList<>();

        matches.remove(0);
        for (Match item : matches) {
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

        for (Map.Entry extra : extraRuns.entrySet()) {
            System.out.println("The extra runs conceded by " + extra.getKey() + " is: " + extra.getValue());
        }
    }


    static void findEconomicPlayer(List<Match> matchesPlayed, List<Delivery> deliveryData) {

        List<String> ids = new ArrayList<>();

        matchesPlayed.remove(0);
        for (Match item : matchesPlayed) {
            if (item.getSeason().equals("2015")) {
                ids.add(item.getId());
            }
        }

        Map<String, Integer> countOfBowlers = new HashMap<String, Integer>();
        Map<String, Integer> totalRunsPerBowler = new HashMap<>();
        Map<String, Double> oversCount = new HashMap<>();

        Map<String, Double> economicPlayers = new HashMap<>();

        int i = 0;
        for (Delivery bc : deliveryData) {
            if (ids.contains(bc.getMatchID())) {
                String bowlerName = bc.getBowler();
                if (countOfBowlers.containsKey(bowlerName)) {
                    int j = countOfBowlers.get(bowlerName);
                    countOfBowlers.put(bc.getBowler(), j + 1);
                } else {
                    countOfBowlers.put(bc.getBowler(), 1);
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
                    int quotient = (int) overs / 6;
                    double remainder = (overs % 6) / 3;
                    double totalOvers = BigDecimal.valueOf(remainder + quotient).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    oversCount.put(trb.getBowler(), totalOvers);
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
                    double economyOfPlayer = Double.parseDouble(String.format("%.2f", playerEconomyCalc));
                    economicPlayers.put(bowlerName, economyOfPlayer);
                }
            }
        }

        List<Map.Entry<String, Double>> sortedEconomicPlayers = new ArrayList<Map.Entry<String, Double>>(economicPlayers.entrySet());
        Collections.sort(sortedEconomicPlayers, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> player1, Map.Entry<String, Double> player2) {
                return player1.getValue().compareTo(player2.getValue());
            }
        });

        System.out.println("\n\tDisplaying top 10 economical players\n");
        int j = 1;
        for (Map.Entry<String, Double> players : sortedEconomicPlayers) {
            if (j <= 10) {
                System.out.println("PLayer name:" + players.getKey() + " Economy:" + players.getValue());
            } else {
                break;
            }
            j++;
        }
    }


    static void findManOfMatch(List<Match> matchesplayed) {
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

        for (Map.Entry count : manOfMatches.entrySet()) {
            System.out.println("Number of times " + count.getKey() + " had been the man of the match is: " + count.getValue());
        }
    }
}
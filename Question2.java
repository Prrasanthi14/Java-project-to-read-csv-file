import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Question2 {

    static Map<String, Integer> teamwincount = new HashMap<String, Integer>();

    static void getteams(List<Matches> matchesplayed) {

        for (Matches item1 : matchesplayed) {
            if(item1.winner.length()>0) {
                String team = item1.winner;
                Integer j = teamwincount.get(team);
                teamwincount.put(item1.winner, (j == null) ? 1 : j + 1);
            }
            else{break;}
        }
        System.out.println("\n\tNumber of matches won of all teams over all the years of IPL\n");
        for (Map.Entry wincount : teamwincount.entrySet()) {
            System.out.println("Number of matches won by " + wincount.getKey() + " over all the years of IPL is: " + wincount.getValue());
        }
        //  System.out.println(count.toString());

    }
}
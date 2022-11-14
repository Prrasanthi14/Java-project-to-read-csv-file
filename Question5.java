import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question5 {
    static Map<String, Integer> manofmatch = new HashMap<String, Integer>();

    static void getmanofmatch(List<Matches> matchesplayed) {

        for (Matches item1 : matchesplayed) {
            if (item1.playerofmatch.length() > 0) {
                String names = item1.getPlayerofmatch();
                Integer j = manofmatch.get(names);
                manofmatch.put(item1.getPlayerofmatch(), (j == null) ? 1 : j + 1);
            } else {
                break;
            }
        }
        System.out.println("\n\tNumber of times the players were the man of the match in IPL.\n");
        for (Map.Entry count : manofmatch.entrySet()) {
            System.out.println("Number of times the player -  " + count.getKey() + " had been the man of the match is: " + count.getValue());
        }
        //System.out.println("NoMatch Played in the "count.toString());

    }
}
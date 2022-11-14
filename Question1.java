import java.util.*;

public class Question1 {

    static Map<String, Integer> count = new HashMap<String, Integer>();

    static void getseasons(List<Matches>matchesplayed)
        {

            for(Matches item:matchesplayed){
                if(item.getSeason()!=null) {
                    String year = item.getSeason();
                    Integer j = count.get(year);
                    count.put(item.getSeason(), (j == null) ? 1 : j + 1);
                }
            }
            System.out.println("\n\tNumber of matches played per year of all the years in IPL.\n");
            for(Map.Entry yearcount : count.entrySet()){
                System.out.println("Number of matches played in the year "+yearcount.getKey()+" is: "+yearcount.getValue());
            }
            //System.out.println("NoMatch Played in the "count.toString());
        }




}

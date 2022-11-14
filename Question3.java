import java.util.*;

public class Question3 {

static void getextraruns(List<Matches> matchesplayed, List<Deliveries>deliverydata){

    Map<String, Integer> extraruns = new HashMap<String, Integer>();
    List<String>ids=new ArrayList<>();

    matchesplayed.remove(0);
    for(Matches item:matchesplayed){
        if(item.getSeason().equals("2016")){
            ids.add(item.id);
            //System.out.println(item.season);
        }
    }
   // System.out.println(ids.toString());

    for(Deliveries d:deliverydata){
        if(ids.contains(d.getId())) {
            if(extraruns.containsKey(d.getBowling_team())){
                int containruns= extraruns.get(d.getBowling_team());
                int runs=Integer.parseInt(d.getExtra_runs());
                    extraruns.put(d.getBowling_team(),containruns+runs);
                }

            }
        else{
            extraruns.put(d.getBowling_team(),Integer.parseInt(d.getExtra_runs()));
        }

        }

    System.out.println("\n\tFor the year 2016 get the extra runs conceded per team\n");
    for(Map.Entry extra : extraruns.entrySet()){
        System.out.println("the extra runs conceded by "+extra.getKey()+" is: "+extra.getValue());
    }

}

}

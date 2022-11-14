import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Question4 {

    static void getextraruns(List<Matches> matchesplayed, List<Deliveries>deliverydata){

        Map<String, Integer> extraruns = new HashMap<String, Integer>();
        List<String>ids=new ArrayList<>();


        

        matchesplayed.remove(0);
        for(Matches item:matchesplayed){
            if(item.getSeason().equals("2015")){
                ids.add(item.id);
            }
        }

      //  System.out.println(ids.size());

        Map<String,Integer> countbowler=new HashMap<String,Integer>();
        Map<String,Integer> totalrunsperbowler=new HashMap<>();
        Map<String,Double> overscount= new HashMap<>();
        
        
        Map<String,Double> economyplayer=new HashMap<>();
        int i=0;
        for(Deliveries bc:deliverydata){
            if(ids.contains(bc.getId())) {
                String bowlername = bc.getBowler();
                if(countbowler.containsKey(bowlername)){
                    int j = countbowler.get(bowlername);
                    countbowler.put(bc.getBowler(),j+1 );
                }
                else{
                    countbowler.put(bc.getBowler(),1);
                }
            }i++;
        }
      // System.out.println(countbowler.size());
        for(Deliveries trb:deliverydata){
            if(ids.contains(trb.getId())) {
                String bowlername = trb.getBowler();
                if(totalrunsperbowler.containsKey(bowlername)){
                    int j = totalrunsperbowler.get(bowlername);
                    totalrunsperbowler.put(trb.getBowler(),j+Integer.parseInt(trb.getTotalruns()));

                }
                else{
                    totalrunsperbowler.put(trb.getBowler(),Integer.parseInt(trb.getTotalruns()));
                }
                if(totalrunsperbowler.containsKey(bowlername)){
                    int j = totalrunsperbowler.get(bowlername);
                    double overs=j;
                    int q=(int)overs/6;
                    double r=(overs%6)/3;
                    double ans= BigDecimal.valueOf(r+q).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    overscount.put(trb.getBowler(), ans );
                }
                else{
                    overscount.put(trb.getBowler(), Double.parseDouble(trb.getTotalruns()));
                }

            }
        }
  //      System.out.println("totalrunsperbowler"+totalrunsperbowler.size());
        
        for(Deliveries ec:deliverydata){
            if(ec.getBowler()!=null){
                String bowlername=ec.getBowler();
                if(overscount.containsKey(bowlername)){
                    double runs=totalrunsperbowler.get(bowlername);
                    double bowlerover=overscount.get(bowlername);
                    double playereconomycalc=runs/bowlerover;
                    double ans= Double.parseDouble(String.format("%.2f",playereconomycalc));
                    economyplayer.put(bowlername,ans);

                }
            }
            }
        double minimum = Collections.min(economyplayer.values());
       // System.out.println(economyplayer.size());
        for(Map.Entry mp : economyplayer.entrySet()){
            if(mp.getValue().equals(minimum))
            {System.out.println(mp.getKey()+" "+ mp.getValue());}
        }
        }





     //   System.out.println(totalrunsperbowler.toString());
        // System.out.println(countbowler.toString());

//        for(Deliveries totovers:deliverydata){
//        //    if(totovers.getTotalruns()!=null) {
//                String bowlername = totovers.getBowler();
//                if(overscount.containsKey(bowlername)){
//                    double j = overscount.get(bowlername);
//                    double overs=j;
//                    int q=(int)overs/6;
//                    double r=(overs%6)/3;
//                    double ans= BigDecimal.valueOf(r+q).setScale(2, RoundingMode.HALF_UP).doubleValue();
//                    overscount.put(totovers.getBowler(),ans );
//                }
//                else{
//                    overscount.put(totovers.getBowler(), Double.parseDouble(totovers.getTotalruns()));
//                }
//     //       }
//        }

}

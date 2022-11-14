import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try
        {
            File file = new File("/home/prasanthi/Desktop/IPL_Project/matches.csv");
            File file2=new File("/home/prasanthi/Desktop/IPL_Project/deliveries.csv");
            FileReader fr = new FileReader(file);
            FileReader fr2 = new FileReader(file2);

            BufferedReader br = new BufferedReader(fr);
            BufferedReader br2 = new BufferedReader(fr2);

            List<Matches> matchesplayed = new ArrayList<>();//each line input
            List<Deliveries> deliverydata=new ArrayList<>();//deliverydata input

            String str = "";
            int i=0;
            while ((str=br.readLine()) != null) {
                if(i!=0) {
                    List<String> fields = new ArrayList<String>(Arrays.asList(str.split(",")));

                    String id = fields.get(0);
                    String season = fields.get(1);
                    String city = fields.get(2);
                    String date = fields.get(3);
                    String winner= fields.get(10);
                    String playerofmatch= fields.get(13);

                    Matches match = new Matches(id, season, city, date,winner,playerofmatch);
                    matchesplayed.add(match);
                }
                i++;

            }

       //  Question1.getseasons(matchesplayed);
            
         // Question2.getteams(matchesplayed);


          //q3
        i=0;
            while ((str=br2.readLine()) != null) {
                if(i!=0) {
                    List<String> fields = new ArrayList<String>(Arrays.asList(str.split(",")));


                    String match_id = fields.get(0);
                    String bowling_team = fields.get(3);
                    String extra_runs = fields.get(16);
                    String bowler=fields.get(8);
                    String total_runs=fields.get(17);


                    Deliveries delivery = new Deliveries(match_id, bowling_team, extra_runs,bowler,total_runs);
                    deliverydata.add(delivery);
                }
                i++;

            }
//         Question3.getextraruns(matchesplayed,deliverydata);
//            Question4.getextraruns(matchesplayed,deliverydata);
//3          Question5.getmanofmatch(matchesplayed);

            System.out.println("Enter the question number (1-5) ");
            Scanner sc =new Scanner(System.in);
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    Question1.getseasons(matchesplayed);
                    break;
                case 2:
                    Question2.getteams(matchesplayed);
                    break;
                case 3:
                    Question3.getextraruns(matchesplayed,deliverydata);
                    break;
                case 4:
                    Question4.getextraruns(matchesplayed,deliverydata);
                    break;
                case 5:
                    Question5.getmanofmatch(matchesplayed);
                    break;
                default:
                    break;

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }}
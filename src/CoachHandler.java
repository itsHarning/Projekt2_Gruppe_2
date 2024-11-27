import java.io.*;
import java.util.ArrayList;


public class CoachHandler {
    public static void main(String[] args) {
        createCoaches();


    }

    public static void createCoaches(){
        ArrayList<Coach>coachlist = new ArrayList<>(); //opretter ny Arrayliste med Coaches

        Coach c1 = new Coach("Torben Enemand",1,Team.competitiveO18);
        Coach c2 = new Coach("Sofie Laudrup",2,Team.competitiveU18);
        Coach c3 = new Coach("Mads Jensen",3,Team.exerciseteam);
        Coach c4 = new Coach("Ricky Topsky",4,Team.competitiveO18);
        Coach c5 = new Coach("Amalie Langstrand",5,Team.competitiveU18);
        Coach c6 = new Coach("Oline Wagner",6,Team.exerciseteam);
        Coach c7 = new Coach("Carla Frankovic",7,Team.competitiveO18);
        Coach c8 = new Coach("Pat Riley",8,Team.competitiveU18);

        coachlist.add(c1); //tilføjer de oprettede Coaches til Arraylisten
        coachlist.add(c2);
        coachlist.add(c3);
        coachlist.add(c4);
        coachlist.add(c5);
        coachlist.add(c6);
        coachlist.add(c7);
        coachlist.add(c8);

        updateTextFile(coachlist); // kalder update Tekstfil-metoden.
    }


    public static void updateTextFile(ArrayList<Coach> tempList) {
        try {
            FileWriter file = new FileWriter("src//CoachList.txt", false);
            PrintWriter out = new PrintWriter(file);
            for (Coach coach: tempList){
                int id = coach.id;
                String name = coach.name;



                out.println(name+","+id);
            }
            out.close(); // Closes so all data gets written to the Textfile
        } catch (IOException e) {
            System.out.println("could not write to file");
        }
    }
    public static ArrayList loadMembersFromTextFile(){
        ArrayList<Coach> tempList= new ArrayList<>();
        try {
            FileReader fil = new FileReader("src//CoachList.txt");
            BufferedReader ind = new BufferedReader(fil);
            String line = ind.readLine();
            while (line !=null){
                String[]bites = line.split(",");

                String id = bites [0];
                String name = bites[1];

                try{
                    int parseId = Integer.parseInt(id);

                    tempList.add(new Coach(name, parseId,));

                }catch (NumberFormatException e){
                    System.out.println("Not a number");
                }
                line = ind.readLine();
            }

        }catch (IOException e){
            System.out.println("Could not find file");;
        }
        return tempList;
    }
}

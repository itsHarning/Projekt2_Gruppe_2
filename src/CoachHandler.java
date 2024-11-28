import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CoachHandler {
    static ArrayList<Coach>coachlist = new ArrayList<>(); //opretter ny Arrayliste med Coaches
    public static void main(String[] args) {
        createCoaches();
        for (Coach c: coachlist) {
            if(c.team == Team.competitiveO18) {
                System.out.print("competitiveO18: ");
                System.out.println(c);
            }
            if(c.team == Team.exerciseteam){
                System.out.print("exerciseteam: ");
                System.out.println(c);}
            if(c.team == Team.competitiveU18){
                System.out.print("competitiveU18: ");
                System.out.println(c);}
        }
    }

    public static void createCoaches(){


        Coach c1 = new Coach(1,"Torben Enemand","competitiveO18",Team.competitiveO18);
        Coach c2 = new Coach(2,"Sofie Laudrup","competitiveU18",Team.competitiveU18);
        Coach c3 = new Coach(3,"Mads Jensen","exerciseteam",Team.exerciseteam);
        Coach c4 = new Coach(4,"Ricky Topsky","competitiveO18",Team.competitiveO18);
        Coach c5 = new Coach(5,"Amalie Langstrand","competitiveU18",Team.competitiveU18);
        Coach c6 = new Coach(6,"Oline Wagner","exerciseteam",Team.exerciseteam);
        Coach c7 = new Coach(7,"Carla Frankovic","competitiveO18",Team.competitiveO18);
        Coach c8 = new Coach(8,"Pat Riley","competitiveU18",Team.competitiveU18);



        coachlist.add(c1); //tilføjer de oprettede Coaches til Arraylisten
        coachlist.add(c2);
        coachlist.add(c3);
        coachlist.add(c4);
        coachlist.add(c5);
        coachlist.add(c6);
        coachlist.add(c7);
        coachlist.add(c8);


        updateTextFile(coachlist); // kalder update Tekstfil-metoden.
        loadMembersFromTextFile();
    }


    public static void updateTextFile(ArrayList<Coach> tempList) {
        try {
            FileWriter file = new FileWriter("src//CoachList.txt", false);
            PrintWriter out = new PrintWriter(file);
            for (Coach coach: tempList){
                int id = coach.id;
                String name = coach.name;
                String arrayName = coach.arrayName;

                out.println(id+","+name+","+arrayName);
            }
            out.close(); // Closes so all data gets written to the Textfile
        } catch (IOException e) {
            System.out.println("could not write to file");
        }
    }

    public static ArrayList loadMembersFromTextFile() {
        ArrayList<Coach> tempList = new ArrayList<>();
        try {
            FileReader fil = new FileReader("src//CoachList.txt");
            BufferedReader ind = new BufferedReader(fil);
            String line = ind.readLine();
            while (line != null) {
                String[] bites = line.split(",");

                String id = bites[0];
                String name = bites[1];
                String arrayname = bites[2];
                ArrayList team = Team.competitiveO18;
                if(arrayname.equals("competitiveO18")){
                    team =Team.competitiveO18;
                }
                if(arrayname.equals("competitiveU18")){
                    team = Team.competitiveU18;
                }
                if(arrayname.equals("exerciseteam")){
                    team = Team.exerciseteam;
                }


                try {
                    int parseId = Integer.parseInt(id);

                    coachlist.add(new Coach(parseId, name, arrayname,team));

                } catch (NumberFormatException e) {
                    System.out.println("Not a number");
                }
                line = ind.readLine();
            }

        } catch (IOException e) {
            System.out.println("Could not find file");

        }
        return tempList;
    }
}


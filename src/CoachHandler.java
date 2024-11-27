import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class CoachHandler {
    public static void main(String[] args) {
        createCoaches();


    }

    public static void createCoaches(){
        ArrayList<Coach>coachlist = new ArrayList<>();

        Coach c1 = new Coach("Torben Enemand",1);
        Coach c2 = new Coach("Sofie Laudrup",2);
        Coach c3 = new Coach("Mads Jensen",3);
        Coach c4 = new Coach("Ricky Topsky",4);
        Coach c5 = new Coach("Amalie Langstrand",5);
        Coach c6 = new Coach("Oline Wagner",6);
        Coach c7 = new Coach("Carla Frankovic",7);
        Coach c8 = new Coach("Pat Riley",8);

        coachlist.add(c1);
        coachlist.add(c2);
        coachlist.add(c3);
        coachlist.add(c4);
        coachlist.add(c5);
        coachlist.add(c6);
        coachlist.add(c7);
        coachlist.add(c8);

        updateTextFile(coachlist);
    }
    public static void updateTextFile(ArrayList<Coach> tempList) {
        try {
            FileWriter file = new FileWriter("src//CoachList.txt", false);
            PrintWriter out = new PrintWriter(file);
            for (Coach coach: tempList){
                int id = coach.id;
                String name = coach.name;


                out.println(id+","+name);
            }
            out.close(); // Closes so all data gets written to the Textfile
        } catch (IOException e) {
            System.out.println("could not write to file");
        }
    }
}

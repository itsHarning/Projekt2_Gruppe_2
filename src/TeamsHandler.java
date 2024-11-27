/*import java.io.*;
import java.util.*;

class TeamsHandler {
    String name;
    String type; //Motionist, KonkurrenceU18, KonkurrenceO18
    String passiv; // PassivMember

    //Konstruktør
    public TeamsHandler(String name, String type, String passiv) {
        this.name = name;
        this.type = type; //Motionist, KonkurrenceU18, KonkurrenceO18,
        this.passiv = passiv;
    }
    //To string til at udskrive oplysninger om medlemmet

    public String toString() {
        return name + " (" + type +")";
    }
}
public class TeamsHandlerList {
    public static void main(String[] args) throws IOException {

        //Skal læse filen og sortere medlemmerne

        File file = new File("MemberList.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        // Lister til at opdele medlemmerne efter type

        List<TeamsHandler> exerciseteam = new ArrayList<>();
        List<TeamsHandler> competitiveU18 = new ArrayList<>();
        List<TeamsHandler> competitiveO18 = new ArrayList<>();
        List<TeamsHandler> passivMembers = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) !=null) {

            //Filen skal være i formatet: Navn, kategori, eller passiv

            String[] parts = line.split(",");
            String navn = parts[0].trim();
            String type = parts[1].trim();
            String passiv = parts[2].trim();

            //Opret et Teamsliste objekt
            TeamsHandler TeamsHandler = new TeamsHandler(navn, type, passiv);

            // Tilføj til den rette liste baseret på type
            if (type.equalsIgnoreCase("exerciseteam")) {
                exerciseteam.add(TeamsHandler);
            } else if (type.equalsIgnoreCase("competitiveO18")) {
                competitiveO18.add(TeamsHandler);
            } else if (type.equalsIgnoreCase("KonkurrenceO18")) {
                competitiveU18.add(TeamsHandler);
            } else if (passiv.equalsIgnoreCase("competitiveU18")) {
                passiv.add(TeamsHandler);
            }
        }
        // Udskriv medlemmerne i hver kategori
        System.out.println("Motionister:");
        for (TeamsHandler memberlist : exerciseteam) {
            System.out.println(memberlist);
        }

        System.out.println("\nKonkurrence U18:");
        for (TeamsHandler memberlist : competitiveU18) {
            System.out.println(memberlist);
        }

        System.out.println("\nKonkurrence O18:");
        for (TeamsHandler memberlist : competitiveO18) {
            System.out.println(memberlist);
        }

        System.out.println("\nPassiv medlemmer:");
        for (TeamsHandler memberlist : passivMedlemmmer) {
            System.out.println(memberlist);
        }
        reader.close();

    }

}




 */
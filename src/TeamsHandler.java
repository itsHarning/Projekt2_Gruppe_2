import java.io.*;
import java.util.*;

class TeamsHandler {
    String navn;
    String type; //Motionist, KonkurrenceU18, KonkurrenceO18
    String passiv; // PassivMember

    //Konstruktør
    public TeamsHandler(String navn, String type, String passiv) {
        this.navn = navn;
        this.type = type; //Motionist, KonkurrenceU18, KonkurrenceO18,
        this.passiv = passiv;
    }
    //To string til at udskrive oplysninger om medlemmet
    public String toString() {
        return navn + " (" + type +")";
    }
}
public class TeamslistSorted {
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
            //Filen skal være i formatet: Navn, kategori, eller passiv.
            String[] parts = line.split(",");
            String navn = parts[0].trim();
            String type = parts[1].trim();
            String passiv = parts[2].trim();

            //Opret et Teamsliste objekt
            Teamslist teamslist = new Teamslist(navn, type, passiv);

            // Tilføj til den rette liste baseret på type
            if (type.equalsIgnoreCase("Motionist")) {
                motionister.add(teamslist);
            } else if (type.equalsIgnoreCase("KonkurrenceU18")) {
                konkurrenceU18.add(teamslist);
            } else if (type.equalsIgnoreCase("KonkurrenceO18")) {
                konkurrenceO18.add(teamslist);
            } else if (passiv.equalsIgnoreCase("PassivMember")) {
                passivMedlemmer.add(teamslist);
            }
        }
        // Udskriv medlemmerne i hver kategori
        System.out.println("Motionister:");
        for (Teamslist memberlist : motionister) {
            System.out.println(memberlist);
        }

        System.out.println("\nKonkurrence U18:");
        for (Teamslist memberlist : konkurrenceU18) {
            System.out.println(memberlist);
        }

        System.out.println("\nKonkurrence O18:");
        for (Teamslist memberlist : konkurrenceO18) {
            System.out.println(memberlist);
        }

        System.out.println("\nPassiv medlemmer:");
        for (Teamslist memberlist : passivMedlemmer) {
            System.out.println(memberlist);
        }
        reader.close();

    }

}



import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class CoachHandler {

        public static void createCoaches(ArrayList<Coach>templist) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Hvad er navnet på den nye træner? (skriv fornavn og efternavn)");
        String newCoachName = keyboard.nextLine();

        System.out.println("Hvilket hold skal den nye træner være på?");
            while (true){
            System.out.println("Tast 1: Konkurrenceholdet under 18");
            System.out.println("Tast 2: Konkurrenceholdet over 18");

            int valg = Main.checkIntFromUser(keyboard);
            String arrayName = "";
            if (valg==0) break;
            switch (valg) {
                case 1:
                    arrayName = "competitiveU18";
                    System.out.println(newCoachName + " er nu oprettet som træner på Konkurrenceholdet under 18 i svømmeklubben Delfinen");
                    templist.add(new Coach(newCoachName,arrayName));
                    updateTextFile(templist);
                     break;
                case 2:
                    arrayName =  "competitiveO18";
                    System.out.println(newCoachName + " er nu oprettet som træner på Konkurrenceholdet over 18 i svømmeklubben Delfinen");
                    templist.add(new Coach(newCoachName,arrayName));
                    updateTextFile(templist);
                    break;
                default:
                    System.out.println("Ugyldigt svar.");
            }

         }
    }


    public static void updateTextFile(ArrayList<Coach> tempList) {
        try {
            FileWriter file = new FileWriter("src/main/resources/CoachList.txt", false);
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
            FileReader fil = new FileReader("src/main/resources/CoachList.txt");
            BufferedReader ind = new BufferedReader(fil);
            String line = ind.readLine();
            while (line != null) {
                String[] bites = line.split(",");

                String name = bites[1];
                String arrayname = bites[2];

                try {
                    tempList.add(new Coach (name, arrayname));

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
    public static void printTeam (ArrayList<Coach>templist){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Indtast ID på den pågældende træner");
        int coachId = Main.checkIntFromUser(keyboard);
        for (Coach c : templist){
            if (c.id == coachId){
                System.out.println("Træner ID: "+c.id+"\t Navn: "+ c.name+"\t Hold: "+c.arrayName);
                if(!c.team.isEmpty()) {
                    System.out.println("Elever:");
                    for (Member m : c.team) {
                        System.out.println(m.getProfile());
                    }
                    System.out.println("");
                }

            }
        }
    }
    public static void printCoachTeam(ArrayList<Coach> coachList){
        Collections.sort(coachList, new Comparator<Coach>() {
            @Override
            public int compare(Coach c1, Coach c2) {
                return Integer.compare(c1.getTeam().size(), c2.getTeam().size());
            }
        });

        for(Coach c: coachList){
            System.out.println("Træner ID :"+c.id+"\t Navn: "+c.name+"\t Hold: "+ c.arrayName);
            if(!c.team.isEmpty()) {
                System.out.println("Antal elever "+c.team.size()+":");
                for (Member m : c.team) {
                    System.out.println(m.getProfile());
                }

                System.out.println("");
            }
        }
    }

    public void assignmembersContainer(ArrayList<Coach> coachList) {
        ArrayList<Coach> TeamCoach = new ArrayList<>();
        for (Coach c: coachList)
        {
            if(c.arrayName.contains("competitiveO18")){
                TeamCoach.add(c);
            }
        }
        assignmembers(Team.competitiveO18, TeamCoach);
        TeamCoach.clear();
        for (Coach c: coachList)
        {
            if(c.arrayName.contains("competitiveU18")){
                TeamCoach.add(c);
            }
        }
        assignmembers(Team.competitiveU18, TeamCoach);
    }

    public static void assignmembers(ArrayList<Member> memberList, ArrayList<Coach> coachList){


        int numCoaches = coachList.size();  // Number of coaches available
        int totalMembers = memberList.size();     // Total members in the team
        int membersPerCoach = totalMembers / numCoaches;  // Basic number of members per coach
        int remainder = totalMembers % numCoaches;  // Remainder members to be distributed


        // Distribute members equally among the coaches
        int currentCoachIndex = 0;
        int assignedMembersCount = 0;

            // Iterate over the team and assign members to coaches
            for (int i = 0; i < totalMembers; i++) {
                // Get the current coach
                Coach currentCoach = coachList.get(currentCoachIndex);

                // Add the current member to the coach's assignedMembers list
                Member currentMember = memberList.get(i);

                // Add member to the coach's assignedMembers
                currentCoach.getTeam().add(currentMember);


                assignedMembersCount++;

                // If the current coach has enough members, move to the next coach
                if (assignedMembersCount >= membersPerCoach + (currentCoachIndex < remainder ? 1 : 0)) {
                    assignedMembersCount = 0;
                    currentCoachIndex++;
                }
            }

    }
}


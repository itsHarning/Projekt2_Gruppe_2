import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class CoachHandler {

    public static void createCoaches(ArrayList<Coach>coachList) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Hvad er navnet på den nye træner? (skriv fornavn og efternavn)");
        String newCoachName = keyboard.nextLine();

        System.out.println("Hvilket hold skal den nye træner være på?");
            while (true){
            System.out.println("Tast 1: Konkurrenceholdet under 18");
            System.out.println("Tast 2: Konkurrenceholdet over 18");
            System.out.println("Tast 0: Tilbage");

            int valg = Main.checkIntFromUser(keyboard);
            String arrayName = "";
            if (valg==0) break;
            switch (valg) {
                case 1:
                    arrayName = "competitiveU18";
                    System.out.println(newCoachName + " er nu oprettet som træner på Konkurrenceholdet under 18 i svømmeklubben Delfinen");
                    System.out.println();
                    coachList.add(new Coach(newCoachName,arrayName));
                    updateTextFile(coachList);
                    return;
                case 2:
                    arrayName =  "competitiveO18";
                    System.out.println(newCoachName + " er nu oprettet som træner på Konkurrenceholdet over 18 i svømmeklubben Delfinen");
                    System.out.println();
                    coachList.add(new Coach(newCoachName,arrayName));
                    updateTextFile(coachList);
                    return;
                default:
                    System.out.println("Ugyldigt svar.");
                    System.out.println("Hvilket hold skal den nye træner være på?");
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
                String arrayName = coach.teamName;

                out.println(id+","+name+","+arrayName);
            }
            out.close(); // Closes so all data gets written to the Textfile
        } catch (IOException e) {
            System.out.println("could not write to file");
        }
    }

    public static ArrayList<Coach> loadMembersFromTextFile() {
        ArrayList<Coach> tempList = new ArrayList<>();
        try {
            FileReader fil = new FileReader("src/main/resources/CoachList.txt");
            BufferedReader ind = new BufferedReader(fil);
            String line = ind.readLine();
            while (line != null) {
                String[] bites = line.split(",");

                String name = bites[1];
                String teamName = bites[2];

                try {
                    tempList.add(new Coach (name, teamName));

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

    public static void printCoachesTeam(ArrayList<Coach>coachList){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Indtast ID på den pågældende træner");
        boolean coachFound = false;
        while (!coachFound) {
            int coachId = Main.checkIntFromUser(keyboard);
            String hold;
            for (Coach c : coachList) {
                if (c.id == coachId) {
                    if (c.teamName.equals("exerciseteam")) {
                        System.out.println("Træner ID :" + c.id + "\t Navn: " + c.name + "\t Hold: Motion");
                            System.out.println();
                        coachFound = true;
                    } else if (c.teamName.equals("competitiveU18") || c.teamName.equals("competitiveO18")) {
                        if (c.teamName.equals("competitiveU18")) {
                            hold = "Under 18";
                        } else {
                            hold = "Over 18";
                        }
                        System.out.println("Træner ID: " + c.id + "\t Navn: " + c.name + "\t Konkurrencehold: " + hold);
                        if (!c.assignedMembers.isEmpty()) {
                            System.out.println("Elever:");
                            for (Member m : c.assignedMembers) {
                                System.out.println(m.getProfile());
                            }
                            System.out.println();
                        }
                        coachFound = true;
                        break;
                    }
                }

            }
            if (!coachFound)
                System.out.println("IDet matchede ikke en træner, prøv igen");

        }
    }

    public static void printCoach(ArrayList<Coach> coachList){
        Collections.sort(coachList, new Comparator<Coach>() {
            @Override
            public int compare(Coach c1, Coach c2) {
                return Integer.compare(c1.getAssignedMembers().size(), c2.getAssignedMembers().size());
            }
        });

        String hold ="";
        for (Coach c : coachList){
            if(c.teamName.equals("exerciseteam"))
            {
                System.out.println("Træner ID: "+c.id+"\t Navn: "+c.name+"\t Hold: Motion");
                if(!c.assignedMembers.isEmpty()) {
                    System.out.println("Antal elever "+c.assignedMembers.size()+":");
                    for (Member m : c.assignedMembers) {
                        System.out.println(m.getProfile());
                    }

                    System.out.println("");
                }
            }
            if(c.teamName.equals("competitiveU18") || c.teamName.equals("competitiveO18")) {

                if (c.teamName.equals("competitiveU18")) {
                    hold = "Under 18";
                } else {
                    hold = "Over 18";
                }
                System.out.println("Træner ID: " + c.id + "\t Navn: " + c.name + "\t Hold: " + hold + "\t Antal elever: " + c.assignedMembers.size());
                if (!c.assignedMembers.isEmpty()) {
                    System.out.println("Elever:");
                    for (Member m : c.assignedMembers) {
                        System.out.println(m.getProfile());
                    }

                    System.out.println("");
                }
            }
        }
    }

    public void assignMembersContainer(ArrayList<Coach> coachList) {
        ArrayList<Coach> TeamCoach = new ArrayList<>();
        for (Coach c: coachList)
        {
            if(c.teamName.contains("competitiveO18")){
                TeamCoach.add(c);
            }
        }
        assignMembersToCoach(Team.competitiveO18, TeamCoach);
        TeamCoach.clear();
        for (Coach c: coachList)
        {
            if(c.teamName.contains("competitiveU18")){
                TeamCoach.add(c);
            }
        }
        assignMembersToCoach(Team.competitiveU18, TeamCoach);
    }

    public static void assignMembersToCoach(ArrayList<Member> memberList, ArrayList<Coach> coachList){
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
                currentCoach.getAssignedMembers().add(currentMember);


                assignedMembersCount++;

                // If the current coach has enough members, move to the next coach
                if (assignedMembersCount >= membersPerCoach + (currentCoachIndex < remainder ? 1 : 0)) {
                    assignedMembersCount = 0;
                    currentCoachIndex++;
                }
            }

    }
}


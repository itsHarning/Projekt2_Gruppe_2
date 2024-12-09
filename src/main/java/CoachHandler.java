import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class CoachHandler {
    public static void main(String[] args) {
        //opretter ny Arrayliste med Coaches
        ArrayList<Coach>coachlist = loadMembersFromTextFile();
        assignmembers(coachlist);




        for(Coach c: coachlist){
            System.out.println(c.name);
            for (Member m: c.team){
                System.out.println(m);
            }

        }


        // createCoaches(coachlist);





        for (Coach c: coachlist) {
        if(c.team == Team.exerciseteam){
            System.out.print("exerciseteam: ");
            System.out.println(c);
        }
        if(c.team == Team.competitiveO18){
                System.out.print("competitive018: ");
                System.out.println(c);}
        if(c.team == Team.competitiveU18){
                System.out.print("competitiveU18: ");
                System.out.println(c);}
        }
    }

    public static void createCoaches(ArrayList<Coach>templist) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Hvad er navnet på den nye træner? (skriv fornavn og efternavn)");
        String newCoachName = keyboard.nextLine();

        System.out.println("Hvilket hold skal den nye træner være på?");
            while (true){
            System.out.println("Tast 1: Motionsholdet");
            System.out.println("Tast 2: Konkurrenceholdet under 18");
            System.out.println("Tast 3: Konkurrenceholdet over 18");

            int valg = Main.checkIntFromUser(keyboard);
            ArrayList team = Team.competitiveO18;
            String arrayName = "";
            switch (valg) {

                case 1:
                    team = Team.exerciseteam;
                    arrayName = "exerciseteam";
                    System.out.println(newCoachName + " er nu oprettet som træner på Motionsholdet i svømmeklubben Delfinen");
                    break;
                case 2:
                    team = Team.competitiveU18;
                    arrayName = "competitiveU18";
                    System.out.println(newCoachName + " er nu oprettet som træner på Konkurrenceholdet under 18 i svømmeklubben Delfinen");
                     break;
                case 3:
                    team = Team.competitiveO18;
                    arrayName =  " competitiveO18";
                    System.out.println(newCoachName + " er nu oprettet som træner på Konkurrenceholdet over 18 i svømmeklubben Delfinen");
                    break;
                default:
                    System.out.println("Ugyldigt svar.");
            }
            templist.add(new Coach(newCoachName,arrayName,team));
            updateTextFile(templist);
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

                    tempList.add(new Coach (name, arrayname,team));

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
    public static void printTeam (Coach coach){
        System.out.println(coach.team);
    }

    public static void assignmembers(ArrayList<Coach> coachList){
        ArrayList<Member> team = new ArrayList<>(FileHandler.getListFromJson());

        int numCoaches = coachList.size();  // Number of coaches available
        int totalMembers = team.size();     // Total members in the team
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
                Member currentMember = team.get(i);

                // Add member to the coach's assignedMembers
                currentCoach.team.add(currentMember);


                assignedMembersCount++;

                // If the current coach has enough members, move to the next coach
                if (assignedMembersCount >= membersPerCoach + (currentCoachIndex < remainder ? 1 : 0)) {
                    assignedMembersCount = 0;
                    currentCoachIndex++;
                }
            }

    }
}


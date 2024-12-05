import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // creates the main list of members
    static ArrayList<Member> memberList = new ArrayList<>(FileHandler.getListFromJson());
    static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {

        CoachHandler coachHandler = new CoachHandler();

        // Sort members in teams
        Team.assignTeams(memberList);

        // Make ComptetiveObject for each member
        CreateCompObject.createCompObject(memberList);

        // Makes dolphin from ascii text file
        try {
            FileReader fil = new FileReader("ascii.txt");
            BufferedReader ind = new BufferedReader(fil);
            String line = ind.readLine(); // converts the read lines to string
            while (line != null) {
                System.out.println(line);
                line = ind.readLine();
            }
        } catch (IOException e) {
            System.out.println("fejl");
        }


        System.out.println("\t\t\t\t\t\t\t--velkommen til Svømmeklubben Delfinen--");
        System.out.println("Hvem vil du logge ind som?");
        while (true) {
        System.out.println("Tast 1: Klubbens formand");
        System.out.println("Tast 2: Klubbens kasserer");
        System.out.println("Tast 3: Træner");
        System.out.println("Tast 0: For at luk programmet ned");
        int choice = checkIntFromUser(keyboard);
        if (choice == 0) break;
        switch (choice) {
                case 1:
                    chairmanMenu();
                    break;
                case 2:
                    cashierMenu();
                    break;
                case 3:
                    coachMenu();
                    break;
                default:
                    System.out.println("Du valgte ikke en af mulighederne præsenteret, prøv igen");
                    System.out.println();
            }
        }
        FileHandler.writeListToJson(memberList);
    }

    static void chairmanMenu(){
        System.out.println("--Formand--");
        while (true) {
            System.out.println("Tast 1: Se alle medlemmer");
            System.out.println("Tast 2: Lav nyt medlem");
            System.out.println("Tast 3: Ændre om de er aktive eller passive");
            System.out.println("Tast 4: Ændre om medlemmet stiller op til stævner");
            System.out.println("Tast 5: Opdater hold");
            System.out.println("Tast 0: For at gå tilbage");
            int choice = checkIntFromUser(keyboard);
            if (choice == 0) break;
            switch (choice) {
                case 1:
                    MemberHandler.printList(memberList);
                    break;
                case 2:
                    CreateNewMember.createNewMember(memberList);
                    memberList.addAll(FileHandler.getListFromJson());
                    break;
                case 3:
                     ChangeActivityStatus.changeActivityStatus(memberList);
                    break;
                case 4:
                    ChangeActivityStatus.changeCompetitiveStatus(memberList);
                    break;
                case 5:
                    Team.updateTeams(memberList);
                    break;
                default:
                    System.out.println("Du valgte ikke en af mulighederne præsenteret, prøv igen");
                    System.out.println();
            }
        }
    }

    static  void cashierMenu(){
        System.out.println("--Kasser--");
        while (true) {
            System.out.println("Tast 1: Se kontingent");
            System.out.println("Tast 2: Indskriv et medlems betaling");
            System.out.println("Tast 0: For at gå tilbage");
            int choice = checkIntFromUser(keyboard);
            if (choice == 0) break;
            switch (choice) {
                case 1:
                    PaymentHandler.getPaymentStatus(memberList);
                    break;
                case 2:
                    PaymentHandler.payMembership(memberList);
                    break;
                default:
                    System.out.println("Du valgte ikke en af mulighederne præsenteret, prøv igen");
                    System.out.println();
            }
        }
    }

    static void coachMenu() {
        System.out.println("--Træner--");
        while (true) {
            System.out.println("Tast 1: Se hvem du er træner for");
            System.out.println("Tast 2: Se top 5 tid inden for hver svømmedisciplin");
            System.out.println("Tast 3: Få information for et givent medlem");
            System.out.println("Tast 4: Opret ny rekord tid for et medlem");
            System.out.println("Tast 0: For at gå tilbage");
            int choice = checkIntFromUser(keyboard);
            if (choice == 0) break;
            switch (choice) {
                case 1:
                    /*  Metode is not done
                    CoachHandler.createCoaches();
                    boolean aktivate=false;
                    for (Coach c: CoachHandler.coachlist) {
                        if(c.team == Team.competitiveO18) {
                            System.out.print("competitiveO18: ");
                            for(Member t: Team.competitiveO18){
                                System.out.println(t.memberName);
                            }
                        }}

                        //CoachHandler.printTeam(CoachHandler.coachlist.get(0));
                     */
                    // TODO: open a method to see who they are Coaching for.
                    break;
                case 2:
                    // TODO: open a method to se top 5 for each of the swimming discipline.
                    break;

                case 3:
                    CompetitiveMember.printMemberTimes(memberList);
                    break;
                case 4: CompetitiveMember.createNewTime(memberList);
                    break;
                default:
                    System.out.println("Du valgte ikke en af mulighederne præsenteret, prøv igen");
                    System.out.println();
            }
        }
    }

    public static int checkIntFromUser(Scanner keyboard) {
        int result = 0;
        boolean validInput = false;

        // Continue until you get a valid input
        while (!validInput) {
            try {
                result = Integer.parseInt(keyboard.nextLine()); // Read input as an int
                validInput = true; // If input is invalid, it ends the loop
            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt input! Indtast venligst et helt tal.");
            }
        }

        return result;
    }
}
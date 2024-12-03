import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Member> membersList;
    static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        // creates the main list of members
        membersList= MemberHandler.loadMembersFromTextFile();

        CoachHandler coachHandler = new CoachHandler();

        // Sort members in teams
        Team.assignTeams(membersList);

        // Makes delpine from ascii textfile
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
        int valg = tjekIntFromUser(keyboard);
        if (valg == 0) break;
        switch (valg) {
                case 1:
                    formandmenu();
                    break;
                case 2:
                    kasserermenu();
                    break;
                case 3:
                    coachmenu();
                    break;
                default:
                    System.out.println("Du valgte ikke en af mulighederne præsenteret, prøv igen");
                    System.out.println("");
            }
        }

    }

    static void formandmenu(){
        System.out.println("--Formand--");
        while (true) {
            System.out.println("Tast 1: Se alle medlemmer");
            System.out.println("Tast 2: Lav nyt medlem");
            System.out.println("Tast 3: Ændre om de er aktive eller passive");
            System.out.println("Tast 0: For at gå tilbage");
            int valg = tjekIntFromUser(keyboard);
            if (valg == 0) break;
            switch (valg) {
                case 1:
                    MemberHandler.printList(membersList);
                    break;
                case 2:
                    CreateNewMember.createNewMember(membersList);
                    membersList= MemberHandler.loadMembersFromTextFile();
                    break;
                case 3:
                     ChangeActivityStatus.changeActivityStatus(membersList);
                    break;
                case 4:
                    Team.updateTeams(membersList);
                    break;
                default:
                    System.out.println("Du valgte ikke en af mulighederne præsenteret, prøv igen");
                    System.out.println("");
            }
        }
    }

    static  void kasserermenu(){
        System.out.println("--Kasser--");
        while (true) {
            System.out.println("Tast 1: Se kontingent");
            System.out.println("Tast 2: Indskriv et medlems betaling");
            System.out.println("Tast 0: For at gå tilbage");
            int valg = tjekIntFromUser(keyboard);
            if (valg == 0) break;
            switch (valg) {
                case 1:
                    PaymentHandler.getPaymentStatus(membersList);
                    break;
                case 2:
                    PaymentHandler.payMembership(membersList);
                    break;
                default:
                    System.out.println("Du valgte ikke en af mulighederne præsenteret, prøv igen");
                    System.out.println("");
            }
        }
    }

    static void coachmenu() {
        System.out.println("--Træner--");
        while (true) {
            System.out.println("Tast 1: Se hvem du er træner for");
            System.out.println("Tast 2: Se top 5 tid inden for hver svømmedisciplin");
            System.out.println("Tast 0: For at gå tilbage");
            int valg = tjekIntFromUser(keyboard);
            if (valg == 0) break;
            switch (valg) {
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
                default:
                    System.out.println("Du valgte ikke en af mulighederne præsenteret, prøv igen");
                    System.out.println("");
            }
        }
    }

    public static int tjekIntFromUser(Scanner keyboard) {
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
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // creates the main list of members
    static ArrayList<Member> memberList = new ArrayList<>(FileHandler.getListFromJson());
    static ArrayList<Coach> coachList = CoachHandler.loadMembersFromTextFile();
    static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {

        CoachHandler coachHandler = new CoachHandler();

        // Sort members in teams
        Team.assignMembersToTeams(memberList);
        coachHandler.assignMembersContainer(coachList);

        AsciiPrinter.asciiPrint();

        System.out.println("\t\t\t\t\t\t\t--velkommen til Svømmeklubben Delfinen--");
        System.out.println("Hvem vil du logge ind som?");
        while (true) {
        System.out.println(AsciiPrinter.YELLOW+"Tast 1:"+AsciiPrinter.RESET+" Klubbens formand");
        System.out.println(AsciiPrinter.YELLOW+"Tast 2:"+AsciiPrinter.RESET+" Klubbens kasserer");
        System.out.println(AsciiPrinter.YELLOW+"Tast 3:"+AsciiPrinter.RESET+" Træner");
        System.out.println(AsciiPrinter.YELLOW+"Tast 0:"+AsciiPrinter.RESET+" Luk programmet");
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
            System.out.println(AsciiPrinter.YELLOW+"Tast 1:"+AsciiPrinter.RESET+" Se alle medlemmer");
            System.out.println(AsciiPrinter.YELLOW+"Tast 2:"+AsciiPrinter.RESET+" Opret nyt medlem");
            System.out.println(AsciiPrinter.YELLOW+"Tast 3:"+AsciiPrinter.RESET+" Se alle trænere");
            System.out.println(AsciiPrinter.YELLOW+"Tast 4:"+AsciiPrinter.RESET+" Opret ny træner");
            System.out.println(AsciiPrinter.YELLOW+"Tast 5:"+AsciiPrinter.RESET+" Ændre om medlemmet er aktive eller passive");
            System.out.println(AsciiPrinter.YELLOW+"Tast 6:"+AsciiPrinter.RESET+" Ændre om medlemmet stiller op til stævner");
            System.out.println(AsciiPrinter.YELLOW+"Tast 7:"+AsciiPrinter.RESET+" For at se alle hold");
            System.out.println(AsciiPrinter.YELLOW+"Tast 0:"+AsciiPrinter.RESET+" Tilbage til start");
            int choice = checkIntFromUser(keyboard);
            if (choice == 0) break;
            switch (choice) {
                case 1:
                    MemberHandler.printList(memberList);
                    break;
                case 2:
                    MemberHandler.createNewMember(memberList);
                    break;
                case 3:
                    CoachHandler.printCoach(coachList);
                    break;
                case 4:
                    CoachHandler.createCoaches(coachList);
                    break;
                case 5:
                     MemberHandler.changeActivityStatus(memberList);
                    break;
                case 6:
                    MemberHandler.changeCompetitiveStatus(memberList);
                    break;
                case 7:
                    Team.printMembersInTeams();
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
            System.out.println(AsciiPrinter.YELLOW+"Tast 1:"+AsciiPrinter.RESET+" Se kontingent");
            System.out.println(AsciiPrinter.YELLOW+"Tast 2:"+AsciiPrinter.RESET+" Indskriv et medlems betaling");
            System.out.println(AsciiPrinter.YELLOW+"Tast 3:"+AsciiPrinter.RESET+" Ændre abonent status på et medlem");
            System.out.println(AsciiPrinter.YELLOW+"Test 4:"+AsciiPrinter.RESET+" Alle med abonnement betaler");
            System.out.println(AsciiPrinter.YELLOW+"Tast 0:"+AsciiPrinter.RESET+" For at gå tilbage");
            int choice = checkIntFromUser(keyboard);
            if (choice == 0) break;
            switch (choice) {
                case 1:
                    PaymentHandler.getPaymentStatus(memberList);
                    break;
                case 2:
                    PaymentHandler.payMembership(memberList);
                    break;
                case 3:
                    PaymentHandler.changeSubscription(memberList);
                    break;
                case 4:
                    PaymentHandler.subscriberPayment(memberList);
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
            System.out.println(AsciiPrinter.YELLOW+"Tast 1:"+AsciiPrinter.RESET+" Se hvem du er træner for");
            System.out.println(AsciiPrinter.YELLOW+"Tast 2:"+AsciiPrinter.RESET+" Se top 5 tid inden for hver svømmedisciplin");
            System.out.println(AsciiPrinter.YELLOW+"Tast 3:"+AsciiPrinter.RESET+" Få alle tider for et givent medlem");
            System.out.println(AsciiPrinter.YELLOW+"Tast 4:"+AsciiPrinter.RESET+" Opret ny rekord tid for et medlem");
            System.out.println(AsciiPrinter.YELLOW+"Tast 0:"+AsciiPrinter.RESET+" For at gå tilbage");
            int choice = checkIntFromUser(keyboard);
            if (choice == 0) break;
            switch (choice) {
                case 1:
                    CoachHandler.printCoachesTeam(coachList);
                    break;
                case 2:
                    CompMemberHandler.getTopFiveSwimmers(memberList);
                    break;
                case 3:
                    CompMemberHandler.printMemberTimes(memberList);
                    break;
                case 4:
                    CompMemberHandler.createNewTime(memberList);
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
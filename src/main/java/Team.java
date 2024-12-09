import java.util.ArrayList;
import java.util.Scanner;

public class Team {
    static ArrayList<Member> membersList2;

    static ArrayList<Member> competitiveO18 = new ArrayList<>();
    static ArrayList<Member> competitiveU18 = new ArrayList<>();
    static ArrayList<Member> exerciseteam = new ArrayList<>();

    static void assignTeams(ArrayList<Member> SortedList) {

        for (Member m : SortedList) {
            if (m.isCompeting == false) {
                exerciseteam.add(m);
            }
            if (m.isCompeting == true && m.memberAge < 18) {
                competitiveU18.add(m);
            }
            if (m.isCompeting == true && m.memberAge >= 18) {
                competitiveO18.add(m);
            }
        }
    }

    public static void updateTeams(ArrayList<Member> UpdateList) {
        Scanner keyboard = new Scanner(System.in);
        boolean memberfound = false;
        int memberId;
        String answer;
        // This loop will run as long as a member is not found.
        while (!memberfound) {
            System.out.println("Skriv ID på medlemmet.");

            if (!keyboard.hasNextInt()) {
                System.out.println("Ugyldigt ID. Prøv igen.");
                keyboard.nextLine();
                continue;       // Ask for the member ID again
            }

            memberId = keyboard.nextInt();
            keyboard.nextLine();
            boolean membernotfoundonce = false;
            //This loop is looking for matching ID with the arraylist.
            for (Member m : UpdateList) {
                if (m.memberId == memberId) {           // If the ID's match with arraylist.
                    System.out.println("Medlem fundet. Er det, det rigtige medlem?");
                    System.out.println(m);              // Makes sure the program is user-friendly and asks if the user wants to continue.
                    System.out.println("Ja / Nej");
                    answer = keyboard.nextLine();

                    if (answer.equalsIgnoreCase("ja")){
                        memberfound = true;
                        String answer2 = keyboard.nextLine();
                        if(m.isCompeting == false){
                            System.out.println("Skal personen starte på et svømmehold?");
                            System.out.println("Ja / Nej");

                            if (answer2.equalsIgnoreCase("ja") && m.memberAge < 18){
                                exerciseteam.remove(m);
                                competitiveU18.add(m);
                            }
                            if (answer2.equalsIgnoreCase("ja") && m.memberAge >= 18){
                                exerciseteam.remove(m);
                                competitiveO18.add(m);
                            }
                        }
                        if (m.isCompeting == true && m.memberAge < 18){
                            System.out.println("Skal personen ud af deres svømmehold?");
                            System.out.println("Ja / Nej");
                            if (answer2.equalsIgnoreCase("ja")){
                                competitiveU18.remove(m);
                                exerciseteam.add(m);
                            }

                        }
                        if (m.isCompeting == true && m.memberAge >= 18){
                            System.out.println("Skal personen ud af deres svømmehold?");
                            System.out.println("Ja / Nej");
                            if (answer2.equalsIgnoreCase("ja")){
                                competitiveO18.remove(m);
                                exerciseteam.add(m);
                            }
                        }
                        if (answer2.equalsIgnoreCase("nej")) break;
                    }
                    if (answer.equalsIgnoreCase("nej")) break;
                } else if(m.memberId != memberId && membernotfoundonce == false) {
                    membernotfoundonce = true;
                    System.out.println("Medlemmet kunne ikke findes.");
                }
            }
        }
    }

}
import java.util.ArrayList;
import java.util.Scanner;

public class Team {
    static ArrayList<Member> membersList2;

    static ArrayList<Member> competitiveO18 = new ArrayList<>();
    static ArrayList<Member> competitiveU18 = new ArrayList<>();
    static ArrayList<Member> exerciseteam = new ArrayList<>();

    public static void main(String[] args) {
        membersList2= MemberHandler.loadMembersFromTextFile();
        assignTeams(membersList2);
        updateTeams(membersList2);
    }

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

        //This loop is looking for matching ID with the arraylist.
        for (Member m : UpdateList) {
            if (m.memberId == memberId) {           // If the ID's match with arraylist.
                System.out.println("Medlem fundet. Er det, det rigtige medlem?");
                System.out.println(m);              // Makes sure the program is user-friendly and asks if the user wants to continue.
                System.out.println("Ja / Nej");
                answer = keyboard.nextLine();

                if (answer.equalsIgnoreCase("ja")){
                    memberfound = true;

                    if(m.isCompeting == false){
                        System.out.println("Skal personen starte på et svømmehold?");
                        System.out.println("Ja / Nej");
                        answer = keyboard.nextLine();
                        if (answer.equalsIgnoreCase("ja") && m.memberAge < 18){
                            m.isCompeting = true;
                            exerciseteam.remove(m);
                            competitiveU18.add(m);
                            System.out.println(m);              // Makes sure the program is user-friendly and asks if the user wants to continue.
                        }
                        if (answer.equalsIgnoreCase("ja") && m.memberAge >= 18){
                            m.isCompeting = true;
                            exerciseteam.remove(m);
                            competitiveO18.add(m);
                            System.out.println(m);              // Makes sure the program is user-friendly and asks if the user wants to continue.
                        }
                    }
                    if (m.isCompeting == true && m.memberAge < 18){
                        System.out.println("Skal personen ud af deres svømmehold?");
                        System.out.println("Ja / Nej");
                        answer = keyboard.nextLine();

                        if (answer.equalsIgnoreCase("ja")){
                            m.isCompeting = false;
                            competitiveU18.remove(m);
                            exerciseteam.add(m);
                            System.out.println(m);              // Makes sure the program is user-friendly and asks if the user wants to continue.
                        }

                    }
                    if (m.isCompeting == true && m.memberAge >= 18){
                        System.out.println("Skal personen ud af deres svømmehold?");
                        System.out.println("Ja / Nej");
                        answer = keyboard.nextLine();

                        if (answer.equalsIgnoreCase("ja")){
                            m.isCompeting = false;
                            competitiveO18.remove(m);
                            exerciseteam.add(m);
                            System.out.println(m);              // Makes sure the program is user-friendly and asks if the user wants to continue.
                        }
                    }
                    if (answer.equalsIgnoreCase("nej")) break;
                }
                if (answer.equalsIgnoreCase("nej")) break;
            }
        }
            if (memberId > UpdateList.size()) {         // The system assigns a member with id++.
                System.out.println("Medlemmet kunne ikke findes.");
                memberfound = false;    // The member is not found and the loop will continue.
            }
        }
    }
}
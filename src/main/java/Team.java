import java.sql.SQLOutput;
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

    static void showMembersinTeams() {

        System.out.println("Exerciseteam");
        for (Member m : Team.exerciseteam) {
            System.out.println(m);
        }
        System.out.println("CompetitiveU18");
        for (Member m : Team.competitiveU18) {
            System.out.println(m);
        }
        System.out.println("CompetitiveO18");
        for (Member m : Team.competitiveO18) {
            System.out.println(m);
        }
    }

    static void updateTeams(ArrayList<Member> templist) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Hvilket ID har medlemet");
        int memberID = Main.checkIntFromUser(keyboard);
        boolean membernotfound = true;
        for (Member m : templist) {
            if (m.memberId == memberID && m.isActiveMember == true && m.isCompeting == false) {
                membernotfound = false;
            System.out.println("Er det det rigtige medlem?" + m.getProfile());
            System.out.println("ja/nej");
            String answer = keyboard.nextLine();

            if (answer.equalsIgnoreCase("Ja")) {
                System.out.println("Vil personen starte på et svømmehold?");
                System.out.println("ja/nej");
                answer = keyboard.nextLine();

                if (answer.equalsIgnoreCase("Ja")) {
                    if (m.memberAge < 18) {
                        exerciseteam.remove(m);
                        competitiveU18.add(m);
                        m.isCompeting = true;
                        FileHandler.writeListToJson(templist);

                    }

                    if (m.memberAge >= 18) {
                        exerciseteam.remove(m);
                        competitiveO18.add(m);
                        m.isCompeting = true;
                        FileHandler.writeListToJson(templist);
                    }

                }
                if(answer.equalsIgnoreCase("nej")){
                    break;
                }
            }
            if (answer.equalsIgnoreCase("nej")) {
                return;
            }
            }
        } if(membernotfound == true) {
            System.out.println("Kunne ikke finde medlemmet, prøv igen");
            System.out.println("");
        }

    }
}


    /* public static void updateTeams(ArrayList<Member> UpdateList) {
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
                                m.isCompeting = true;
                            }
                            if (answer2.equalsIgnoreCase("ja") && m.memberAge >= 18){
                                exerciseteam.remove(m);
                                competitiveO18.add(m);
                                m.isCompeting = true;
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
    }*/


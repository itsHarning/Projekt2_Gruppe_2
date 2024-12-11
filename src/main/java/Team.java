import java.util.ArrayList;
import java.util.Scanner;

public class Team {
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

        System.out.println("Motions hold:");
        for (Member m : Team.exerciseteam) {
            System.out.println(m.getProfile());
        }
        System.out.println();
        System.out.println("Under 18:");
        for (Member m : Team.competitiveU18) {
            System.out.println(m.getProfile());
        }
        System.out.println();
        System.out.println("Over 18:");
        for (Member m : Team.competitiveO18) {
            System.out.println(m.getProfile());
        }
        System.out.println();
    }

    static void updateTeams(ArrayList<Member> templist) {
        System.out.println("Her kan du ændre competetiv status");
        while (true) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Hvilket ID har medlemet");
            int memberID = Main.checkIntFromUser(keyboard);
            boolean membernotfound = true;
            for (Member m : templist) {
                if (m.memberId == memberID && m.isActiveMember == true && m.isCompeting == false) {
                    membernotfound = false;
                    System.out.println("Vil du melde dette medlem til stævner? " + m.getProfile());
                    System.out.println("ja/nej");
                    String answer = keyboard.nextLine();

                        if (answer.equalsIgnoreCase("Ja")) {
                            if (m.memberAge < 18) {
                                exerciseteam.remove(m);
                                competitiveU18.add(m);
                                m.isCompeting = true;
                                FileHandler.writeListToJson(templist);
                                return;
                            }

                            if (m.memberAge >= 18) {
                                exerciseteam.remove(m);
                                competitiveO18.add(m);
                                m.isCompeting = true;
                                FileHandler.writeListToJson(templist);
                                return;
                            }

                        }

                    if (answer.equalsIgnoreCase("nej")) {
                        break;
                    }
                }
                if(m.memberId == memberID && m.isActiveMember == true && m.isCompeting == true){
                    membernotfound = false;
                    System.out.println("Vil du melde dette medlem ud af stævner? " + m.getProfile());
                    System.out.println("ja/nej");
                    String answer = keyboard.nextLine();

                        if (answer.equalsIgnoreCase("Ja")) {
                            if (m.memberAge < 18) {
                                exerciseteam.add(m);
                                competitiveU18.remove(m);
                                m.isCompeting = false;
                                FileHandler.writeListToJson(templist);
                                return;
                            }

                            if (m.memberAge >= 18) {
                                exerciseteam.add(m);
                                competitiveO18.remove(m);
                                m.isCompeting = false;
                                FileHandler.writeListToJson(templist);
                                return;
                            }

                        }
                    if (answer.equalsIgnoreCase("nej")) {
                        break;
                    }
                }
            }
            if (membernotfound == true) {
                System.out.println("Kunne ikke finde medlemmet, prøv igen");
            }
        }

    }
}
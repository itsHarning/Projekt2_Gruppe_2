import java.util.ArrayList;
import java.util.Scanner;

public class ChangeActivityStatus {
    public static void main(String[] args) {

        ArrayList<Member> testlist = new ArrayList<>();
        Member a1 = new Member(1, "j1", Gender.OTHER, 16, true, true, false, false);
        Member a2 = new Member(2, "j2", Gender.OTHER, 20, false, true, false, false);
        Member a3 = new Member(3, "j3", Gender.OTHER, 61, true, true, false, false);
        Member a4 = new Member(4, "j4", Gender.OTHER, 18, true, true, false, false);
        Member a5 = new Member(5, "j5", Gender.OTHER, 18, true, true, false, false);

        testlist.add(a1);
        testlist.add(a2);
        testlist.add(a3);
        testlist.add(a4);
        testlist.add(a5);


        Member member = new Member(1, "Test", Gender.OTHER, 18, true, true, true, true);
        System.out.println(member.isActiveMember);
        // member=changeActivityStatus(member);
        System.out.println(member.isActiveMember);
    }


    public static void changeActivityStatus(ArrayList<Member> memberList) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Skriv ID på medlemmet");
        int memberID = keyboard.nextInt();
        keyboard.nextLine();
        for (Member m : memberList) {
            if (m.memberId == memberID) {
                System.out.println("Hvad skal medlemmets status rettet til? (aktiv/passiv)");
                String answer = keyboard.nextLine();
                if (answer.equals("aktiv")) {
                    m.isActiveMember = true;
                    System.out.println(m.memberName + "s" + " aktivitetsstatus er nu ændret til aktiv");
                    System.out.println();
                    break;
                } else if (answer.equals("passiv")) {
                    m.isActiveMember = false;
                    System.out.println(m.memberName + "s" + " medlemsstatus er nu ændret til passiv");
                    break;
                } else {
                    System.out.println("Dette er ikke et gyldigt svar, prøv igen");
                    System.out.println();
                }
            }
        }
        FileHandler.writeListToJson(memberList);
    }

    public static void changeCompetitiveStatus(ArrayList<Member> memberList) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Skriv ID på medlemmet");
        int memberId = keyboard.nextInt();
        keyboard.nextLine();
        for (Member m : memberList) {
            if (m.memberId == memberId) {
                while (true) {
                    System.out.println("Skal " + m.memberName + " stille op til stævner? (Ja / Nej)");

                    String answer = keyboard.nextLine();
                    if (answer.equalsIgnoreCase("nej")) {
                        m.isCompeting = false;
                        System.out.println(m.memberName + " er nu registreret som motionssvømmer");
                        break;
                    } else if (answer.equalsIgnoreCase("ja")) {
                        System.out.println(m.memberName + " er nu registreret som konkurrencesvømmer");
                        m.isCompeting = true;
                        Team.assignTeams(memberList);
                        CreateCompObject.createCompObject(memberList);
                        break;
                    } else {
                        System.out.println("Dette er ikke et gyldigt svar, prøv igen");
                        System.out.println();
                    }
                }
            }

        }
        FileHandler.writeListToJson(memberList);
    }
}
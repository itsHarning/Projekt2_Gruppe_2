import java.util.ArrayList;
import java.util.Scanner;

public class MemberHandler {
    static ArrayList<Member> membersList; // creates the main list of members
    static Scanner keyboard = new Scanner(System.in);

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


    // prints the given list in a nicely formatted way
    public static void printList(ArrayList<Member> tempList) {
        for (Member m:tempList){
            System.out.println(
                    "ID: "+m.memberId+"\t\t" +
                            "NAVN: "+m.memberName+" ".repeat(18-m.memberName.length())+
                            "KØN: "+m.memberGender+" ".repeat(7-m.memberGender.name().length())+"\t"+
                            "ALDER: "+m.memberAge);
            if (m.isActiveMember) {
                System.out.print("Medlemskabet er aktivt, ");
                if (m.isCompeting)
                    System.out.println("og de stiller op i stævner");
                else
                    System.out.println("og de er motionister");
            }
            else
                System.out.println("Ikke aktivt medlemskab");
            if (m.hasPaid)
                System.out.println("Medlemmet har betalt");
            else {
                System.out.format("Medlemmet har ikke betalt, og skylder %.2f DKK\n",PaymentHandler.getAmount(m));
            }
            System.out.println();
        }
    }

    public static Member getMemberFromId(ArrayList<Member> memberList) {
        // this loop will run as long as a member is not found.
        while (true) {
            System.out.println("Indtast IDet på medlemmet");
            int memberId = CompMemberHandler.checkIntFromUser();
            if (memberId == 0)
                break;

            int finalMemberId = memberId;
            if (finalMemberId > memberList.getLast().memberId){
                System.out.println("Dette er ikke et gyldigt medlemsnummer\nDer er kun "+Member.numOfMembers+" medlemmer i klubben");
                continue;
            } else if (memberList.stream().noneMatch(member -> member.memberId == finalMemberId)){
                System.out.println("Kunne ikke finde et medlem med IDet '"+finalMemberId+"'! Prøv igen");
                continue;
            }

                // loops through the member list to find the member with a matching ID given
                while (true) {
                    for (Member member : memberList) {
                        if (member.memberId == memberId) {
                            while (true) {
                                // double checks that you've gotten the member you intended
                                System.out.println("Er dette det rigtige medlem?");
                                System.out.println(member.memberName + "?");
                                System.out.println("Ja / Nej");
                                String answer = keyboard.nextLine();
                                if (answer.equalsIgnoreCase("0") || answer.equalsIgnoreCase("q"))
                                    break;
                                if (answer.equalsIgnoreCase("ja")) {
                                    return member;

                                    // handles if you wrote the incorrect ID, and need to write a new one
                                } else if (answer.equalsIgnoreCase("nej")) {
                                    System.out.println("Prøv igen med et nyt ID.");
                                    memberId = CompMemberHandler.checkIntFromUser();
                                    if (memberId == 0)
                                        return member;
                                    break;

                                    // handles if what's written isn't a valid ID
                                } else {
                                    System.out.println("Ugyldigt svar. Prøv igen (Ja / Nej)");
                                }
                            }
                        }
                    }
                }
            }
        return null;
    }
}

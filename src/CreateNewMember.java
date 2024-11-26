import java.util.ArrayList;
import java.util.Scanner;

public class CreateNewMember {
    static Scanner keyboard = new Scanner(System.in);
    static ArrayList<Member> membersList;
    public static Member createNewMember() {
        membersList= MemberHandler.loadMembersFromTextFile();

        System.out.println("Hvad er dit navn?");
        String memberName = keyboard.nextLine();
        if (memberName.equals("0")) System.exit(0);


        System.out.println("Hvor gammel er du?");
        int memberAge = getIntFromUser(keyboard);
        if (memberAge == 0) System.exit(0);

        System.out.println("Er medlemmet aktiv eller passiv?");
        String answerStatus = keyboard.nextLine();
        boolean isActiveMember;
        while (true) {
            if (answerStatus.equalsIgnoreCase("aktiv")) {
                isActiveMember = true;
                break;
            } else if (answerStatus.equalsIgnoreCase("passiv")) {
                isActiveMember = false;
                break;
            } else {
                System.out.println("Det er ikke et gyldigt svar, prøv igen (aktivt/passivt)");
                answerStatus = keyboard.nextLine();
            }
        }
        System.out.println("Vil du stille op i stævner? ja eller nej");
        boolean isCompeting;

        String answerCompeting = keyboard.nextLine();
        if (answerCompeting.equals("0")) System.exit(0);

        while (true) {
            if (answerCompeting.equalsIgnoreCase("ja")) {
                isCompeting = true;
                break;
            } else if (answerCompeting.equalsIgnoreCase("nej")) {
                isCompeting = false;
                break;
            } else {
                System.out.println("Det er ikke et gyldigt svar, prøv igen (ja/nej)");
                answerCompeting = keyboard.nextLine();
            }
        }
        boolean hasPaid = false;
        boolean automatikPaid = false;
        System.out.println("Du har skrevet, navn: " + memberName + ". Alder: " + memberAge +" . Hans status: "+isActiveMember+ ". Hans kompetetiv status er: " + isCompeting);
        membersList.add(new Member(memberName, memberAge, isActiveMember, isCompeting, hasPaid, automatikPaid));
        MemberHandler.updateTextFile(membersList);
        return new Member(memberName, memberAge, isActiveMember, isCompeting, hasPaid, automatikPaid);
        // TODO: Member kommer ind i member list

    }

    public static int getIntFromUser(Scanner keyboard) {
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
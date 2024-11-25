import java.util.Scanner;

public class CreateNewMember {
    static Scanner keyboard = new Scanner(System.in);

    public static Member createNewMember() {
        System.out.println("Hvad er dit navn?");
        String memberName = keyboard.nextLine();
        if (memberName.equals("0")) System.exit(0);


        System.out.println("Hvor gammel er du?");
        int memberAge = getIntFromUser(keyboard);
        if (memberAge == 0) System.exit(0);

        boolean isActiveMember = true;
        System.out.println("Vil du stille op i stævner? ja eller nej");
        boolean isCompeting = false;
        String answer = keyboard.nextLine();
        if (answer.equals("0")) System.exit(0);

        while (true) {
            if (answer.equalsIgnoreCase("ja")) {
                isCompeting = true;
                break;
            } else if (answer.equalsIgnoreCase("nej")) {
                isCompeting = false;
                break;
            } else {
                System.out.println("Det er ikke et gyldigt svar, prøv igen (ja/nej)");
                answer = keyboard.nextLine();
            }
        }
        boolean hasPaid = false;
        boolean automatikPaid = false;
        System.out.println("Du har skrevet, navn: " + memberName + ". Alder: " + memberAge + ". Hans kompetetiv status er: " + isCompeting);
        return new Member(memberName, memberAge, isActiveMember, isCompeting, hasPaid, automatikPaid);
    }

    public static int getIntFromUser(Scanner keyboard) {
        int result = 0;
        boolean validInput = false;

        // Fortsæt indtil vi får et gyldigt input
        while (!validInput) {
            try {
                result = Integer.parseInt(keyboard.nextLine()); // Læs input som en int
                validInput = true; // Hvis input er gyldigt, afslut løkken
            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt input! Indtast venligst et helt tal.");
            }
        }

        return result;
    }
}
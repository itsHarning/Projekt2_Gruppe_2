import java.util.ArrayList;
import java.util.Scanner;

public class CreateNewMember {
    static Scanner keyboard = new Scanner(System.in);

    public static void createNewMember(ArrayList<Member> membersList) {

                System.out.println("Hvad er medlemmets navn?");
        String Name = keyboard.nextLine();
        if (Name.equals("0")) System.exit(0);

        System.out.println("Hvilen køn er medlemmet? (Mand/Kvinde/Andet)");

        String Valg = keyboard.nextLine();
        Gender gender;

        while (true) {
            if (Valg.equalsIgnoreCase("Mand")) {
                gender = Gender.MALE;
                break;
            }
            if (Valg.equalsIgnoreCase("Kvinde")) {
                gender = Gender.FEMALE;
                break;
            }
            if (Valg.equalsIgnoreCase("Andet")) {
                gender = Gender.OTHER;
                break;
            } else {
                System.out.println("Du skal vælge en af mulighederne");
                Valg = keyboard.nextLine();
            }
        }

        System.out.println("Hvor gammel er medlemmet?");
        int Age = getIntFromUser(keyboard);
        if (Age == 0) System.exit(0);

        System.out.println("Er medlemskabet aktivt? (Ja / Nej)");
        String answerStatus = keyboard.nextLine();
        boolean isActiveMember;
        while (true) {
            if (answerStatus.equalsIgnoreCase("ja")) {
                isActiveMember = true;
                break;
            } else if (answerStatus.equalsIgnoreCase("nej")) {
                isActiveMember = false;
                break;
            } else {
                System.out.println("Det er ikke et gyldigt svar, prøv igen (aktivt/passivt)");
                answerStatus = keyboard.nextLine();
            }
        }
        System.out.println("Vil medlemmet stille op i stævner? (Ja / Nej)");
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
                System.out.println("Det er ikke et gyldigt svar, prøv igen (Ja / Nej)");
                answerCompeting = keyboard.nextLine();
            }
        }
        boolean hasPaid = false;
        boolean automaticPayment = false;
        System.out.println("Du har skrevet, navn: "+Name+", Køn:"+gender+", Alder: "+Age+", Medlemskabs status status: "+isActiveMember+". Kompetetiv status: "+isCompeting);

        membersList.add(new Member(Name, gender,  Age, isActiveMember, isCompeting, hasPaid, automaticPayment));
        Team.assignTeams(membersList);
        CreateCompObject.createCompObject(membersList);
        FileHandler.writeListToJson(membersList);
        FileHandler.writeListToJson(membersList);
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
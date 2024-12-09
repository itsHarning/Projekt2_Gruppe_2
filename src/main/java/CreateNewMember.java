import java.util.ArrayList;
import java.util.Scanner;

public class CreateNewMember {
    static Scanner keyboard = new Scanner(System.in);


    // In this method you can make a new member witch is then set in memberList, json and creates a compobject for them
    public static void createNewMember(ArrayList<Member> membersList) {

        // Here you can input name as a string
        System.out.println("Hvad er medlemmets navn?");
        String Name = keyboard.nextLine();
        if (Name.equals("0")) return;                   // if you press 0 you get send back to the menu

        // here you enter the gender
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

        // here you enter you're age
        System.out.println("Hvor gammel er medlemmet?");
        int Age = getIntFromUser(keyboard);
        // tjeks if the age is over 100
        while(Age >= 100){
            System.out.println("Du skal melde dig på pensionist swømmeklubben hvis du er over 100 (Du kan gå tilbage ved at taste 0)");
            Age = getIntFromUser(keyboard);
        }
        if (Age == 0) return;

        // Ask if the member is aktive or not
        System.out.println("Er medlemskabet aktivt? (Ja / Nej)");
        String answerStatus = keyboard.nextLine();
        boolean isActiveMember;
        if (answerStatus.equalsIgnoreCase("0")) return;
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

        // Ask if the member is competing
        boolean isCompeting;
        if(isActiveMember) {                                                        // tjeks if the member is aktive and if not goes to the next kode
            System.out.println("Vil medlemmet stille op i stævner? (Ja / Nej)");

            String answerCompeting = keyboard.nextLine();
            if (answerCompeting.equals("0")) return;

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
        }
        else {
            isCompeting = false;
        }
        boolean hasPaid = false;
        boolean automaticPayment = false;
        // gives message of what you have ridden
        System.out.println("Du har skrevet, navn: "+Name+", Køn: "+gender+", Alder: "+Age+", Medlemskabs status status: "+isActiveMember+". Kompetetiv status: "+isCompeting);

        // here it adds the information to memberList, jsonfile and creates a new object
        membersList.add(new Member(Name, gender,  Age, isActiveMember, isCompeting, hasPaid, automaticPayment));
        // Team.assignTeams(membersList);
        FileHandler.writeListToJson(membersList);
    }

    // this method tjeks if the int is valid
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
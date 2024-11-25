import java.util.Scanner;

public class CreateNewMember {
    static Scanner keyboard = new Scanner(System.in);
    public static Member createNewMember() {
        //int memberId=1;
        System.out.println("Hvad er dit navn?");
        String memberName= keyboard.nextLine();
        System.out.println("Hvor gammel er du?");
        int memberAge = keyboard.nextInt();
        keyboard.nextLine();

        boolean isActiveMember = true;
        System.out.println("Vil du stille op i stævner? ja eller nej");
        String ja = "ja";
        String nej = "nej";
        boolean isCompeting = false;
        String answer = keyboard.nextLine();
        // TODO: add function for if the answer is not viable (something other than ja/nej is said)

        while (true) {
            if (answer.equalsIgnoreCase(ja)) {
                isCompeting = true;
                break;
            } else if (answer.equalsIgnoreCase(nej)) {
                isCompeting = false;
                break;
            }
            else {
                System.out.println("Det er ikke et gyldigt svar, prøv igen (ja/nej)");
                answer = keyboard.nextLine();
            }
        }
        boolean hasPaid = false;
        System.out.println("Du har skrevet, navn: "+memberName+" alder: "+memberAge+" hans kompetetiv status er: "+isCompeting);
        return new Member(memberName, memberAge, isActiveMember, isCompeting, hasPaid);


    }

    public static void main(String[] args) {
        createNewMember();
    }
}



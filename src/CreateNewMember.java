import java.util.Scanner;

public class CreateNewMember {
    static Scanner keyboard = new Scanner(System.in);
    public static Member createNewMember()
    {
            System.out.println("Hvad er dit navn?");
            String memberName = keyboard.nextLine();
            if (memberName.equals("0")) System.exit(0);


            System.out.println("Hvor gammel er du?");
                int memberAge = keyboard.nextInt();
                if(memberAge == 0) System.exit(0);
            keyboard.nextLine();

            boolean isActiveMember = true;
            System.out.println("Vil du stille op i stævner? ja eller nej");
            boolean isCompeting = false;
            // todo: tjek for om det er en int
            String answer = keyboard.nextLine();
            if(answer.equals("0")) System.exit(0);

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
}
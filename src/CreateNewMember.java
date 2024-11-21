import java.util.Scanner;

public class CreateNewMember {
    static Scanner keyboard = new Scanner(System.in);
    public static Member createNewMember() {
        int memberId=1;
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
        if(keyboard.nextLine().equals(ja)) {
            isCompeting = true;
        } else if (keyboard.nextLine().equals(nej))
        {
            isCompeting = false;
        }

        return new Member(memberId, memberName, memberAge, isActiveMember, isCompeting);

    }

    public static void main(String[] args) {
        CreateNewMember test = new CreateNewMember();
        test.createNewMember();
    }
}



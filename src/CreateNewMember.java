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
        if(answer.equals(ja)) {
            isCompeting = true;
        } else if (answer.equals(nej)) {
            isCompeting = false;
        }
        boolean hasPaid = false;

        return new Member(memberName, memberAge, isActiveMember, isCompeting, hasPaid);

    }

    public static void main(String[] args) {
        createNewMember();
    }
}



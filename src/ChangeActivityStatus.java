import java.util.Scanner;

public class ChangeActivityStatus {
    public static void main(String[] args) {


        Member member = new Member(1, "Test", 18, true, true, true);
        System.out.println(member.isActiveMember);
        member=changeActivityStatus(member);
        System.out.println(member.isActiveMember);
    }


    public static Member changeActivityStatus (Member member){
        Scanner keyboard = new Scanner(System.in);
        while (true) {
        System.out.println("Hvad skal medlemmets status rettet til? (aktiv/passiv)");
        String answer = keyboard.nextLine();
        if(answer.equals("aktiv")) {
            member.isActiveMember = true;
            System.out.println(member+"s"+" aktivitetsstatus er nu ændret til aktiv");
            break;
        } else if (answer.equals("passiv")) {
            member.isActiveMember = false;
            System.out.println(member+"s"+" medlemsstatus er nu ændret til passiv");
            break;
        } else {
            System.out.println("Dette er ikke et gyldigt svar, prøv igen");
            System.out.println("");
        }
        }
        return member;
    }
}
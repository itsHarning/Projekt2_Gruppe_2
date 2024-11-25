import java.util.ArrayList;
import java.util.Scanner;

public class ChangeActivityStatus {
    public static void main(String[] args) {

        ArrayList<Member> testlist = new ArrayList<>();
        Member a1 = new Member(1, "j1", 16, true, true, false, false);
        Member a2 = new Member(2, "j2", 20, false, true, false, false);
        Member a3 = new Member(3, "j3", 61, true, true, false, false);
        Member a4 = new Member(4, "j4", 18, true, true, false, false);
        Member a5 = new Member(5, "j5", 18, true, true, false, false);

        testlist.add(a1);
        testlist.add(a2);
        testlist.add(a3);
        testlist.add(a4);
        testlist.add(a5);


        Member member = new Member(1, "Test", 18, true, true, true,true);
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
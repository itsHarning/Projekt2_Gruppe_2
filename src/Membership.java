import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedMap;

public class Membership {
    public static void main(String[] args) {
        //Imidlertidig liste
        ArrayList<Member> testlist = new ArrayList<>();
        Member a1 = new Member(1, "j1" , 16, true, true, true);
        Member a2 = new Member(2, "j2" , 20, true, true, true);
        Member a3 = new Member(3, "j3" , 61, true, true, true);
        Member a4 = new Member(4, "j4" , 18, true, true, true);
        Member a5 = new Member(5, "j5" , 18, true, true, true);

        testlist.add(a1);
        testlist.add(a2);
        testlist.add(a3);
        testlist.add(a4);
        testlist.add(a5);

        payMembership(testlist);
    }

    public static void payMembership(ArrayList<Member> testlist) {
        Scanner keyboard = new Scanner(System.in);
        int under18 = 1000;
        int over18 = 1600;
        int senior = 1600;
        double seniorDiscount = 0.75;
        boolean memberfound = false;
        boolean hasPaid = false;

        System.out.println(testlist);

        while (!memberfound) {
            System.out.println("Skriv ID på medlemmet.");
            int memberId = keyboard.nextInt();
            keyboard.nextLine();

            //Tjekker om ID'et matcher overens med arraylisten.
            for (Member member : testlist) {
                if (member.memberId == memberId) {
                    memberfound = true;
                    System.out.println("Medlem fundet. Er det, det rigtige medlem?");
                    System.out.println(member);
                    System.out.println("Ja / Nej");

                    String answer = keyboard.nextLine();

                    if (answer.equalsIgnoreCase("ja")) {
                        // skal tjekke hvis allerede betalt, skal ind i constructor
                        // if (member.hasPaid == true) {
                          // System.out.println("Medlemmet har allerede betalt kontigent");
                        if (member.memberAge < 18) {
                            System.out.println("Medlemmet under 18 har nu betalt sit kontingent på: " + under18 + "kr");

                        } else if (member.memberAge >= 18 && member.memberAge <= 59) {
                            System.out.println("Medlemmet over 18 har nu betalt sit kontingent på: " + over18 + "kr");

                        } else if (member.memberAge >= 60) {
                            System.out.println("Senior medlemmet har nu betalt sit kontingent på: " + senior * seniorDiscount + "kr");
                        }
                        //Status skal ændres til betalt.
                        //member.setHasPaid(true);

                    } else if (answer.equalsIgnoreCase("nej")) {
                        System.out.println("Prøv igen med et nyt ID.");
                        memberfound = false;
                        break;
                    }
                }
            }
            if (!memberfound) {
                System.out.println("Medlem findes ikke. Prøv igen");
            }
        }
    }

    public static void getPaymentStatus(){
        Scanner keyboard = new Scanner(System.in);

    }
}


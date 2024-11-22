import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedMap;

public class Membership {
    public static void main(String[] args) {
        ArrayList<Member> testlist = new ArrayList<>();
        Member a1 = new Member(1, "j1" , 18, true, true);
        Member a2 = new Member(2, "j2" , 18, true, true);
        Member a3 = new Member(3, "j3" , 18, true, true);
        Member a4 = new Member(4, "j4" , 18, true, true);
        Member a5 = new Member(5, "j5" , 18, true, true);

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
        double discount = 0.75;
        boolean memberfound = false;
        boolean haspaid = false;

        while (!memberfound) {
            System.out.println("Skriv ID på medlemmet.");
            int memberId = keyboard.nextInt();
            keyboard.nextLine();

            for (Member member : testlist) {
                if (member.getMemberId() == memberId) {
                    memberfound = true;
                    System.out.println("Medlem fundet. Er det, det rigtige medlem?");
                    System.out.println(member);
                    System.out.println("Ja / Nej");

                    String answer = keyboard.nextLine();

                    if (answer.equalsIgnoreCase("ja")) {
                        System.out.println("Vælg nu følgende.");
                        System.out.println("Tast 1: Beløb for medlem under 18: 1000kr. ");
                        System.out.println("Tast 2: Beløb for medlem over 18: 1600kr. ");
                        System.out.println("Tast 3: Beløb for senior medlem: 1600 med 25% rabat.");
                        System.out.println("Tast 4: Medlemmet har betalt.");

                        int choice = keyboard.nextInt();
                        keyboard.nextLine();

                        switch (choice) {
                            case 1:
                                System.out.println("Medlemmet under 18 har nu betalt sit kontigent på: " + under18 + "kr");
                                break;
                            case 2:
                                System.out.println("Medlemmet over 18 har nu betalt sit kontigent på: " + over18 + "kr");
                                break;
                            case 3:
                                System.out.println("Senior medlemmet har nu betalt sit kontigent på: " + senior * discount + "kr");
                                break;
                            case 4:
                                System.out.println("Medlemmet har allerede betalt kontigent");
                                haspaid = true;
                                break;
                            default:
                                System.out.println("Ugyldigt valg. Prøv igen.");
                        }
                        break;
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


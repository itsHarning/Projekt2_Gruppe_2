import java.util.ArrayList;
import java.util.Scanner;

public class PaymentHandler {
    public static void main(String[] args) {
        //Temporary list
        ArrayList<Member> testlist = new ArrayList<>();
        Member a1 = new Member(1, "j1", 16, true, true, false, false);
        Member a2 = new Member(2, "j2", 20, false, true, false, false);
        Member a3 = new Member(3, "j3", 61, true, true, false, false);
        Member a4 = new Member(4, "j4", 18, true, true, false, false);
        Member a5 = new Member(5, "j5", 18, true, true, true, false);

        testlist.add(a1);
        testlist.add(a2);
        testlist.add(a3);
        testlist.add(a4);
        testlist.add(a5);

        getPaymentStatus(testlist);
        payMembership1(testlist);
        getPaymentStatus(testlist);

    }

    public static ArrayList<Member> payMembership(ArrayList<Member> tempList) {
        Scanner keyboard = new Scanner(System.in);
        boolean memberfound = false;

        while (!memberfound) {
            System.out.println("Skriv ID på medlemmet.");
            int memberId = keyboard.nextInt();
            keyboard.nextLine();

            //This loop is looking for matching ID with the arraylist.
            for (Member member : tempList) {
                if (member.memberId == memberId) {           // If the ID's match.
                    memberfound = true;                      // The member is found.
                    System.out.println("Medlem fundet. Er det, det rigtige medlem?");
                    System.out.println(member);              // Makes sure the program is user-friendly and asks if the user wants to continue.
                    System.out.println("Ja / Nej");

                    String answer = keyboard.nextLine();

                    if (answer.equalsIgnoreCase("ja")) {
                        if (member.hasPaid) {       //
                            System.out.println("Medlemmet har allerede betalt kontingent");
                        } else {
                            double missingPayment = PaymentHandler.getAmount(member);
                            System.out.println(member.memberName + " på " + member.memberAge + " har nu betalt det manglende kontigent på: " + missingPayment + "kr.");
                            member.setHasPaid(true);
                        }
                        break;
                    } else if (answer.equalsIgnoreCase("nej")) {
                        System.out.println("Prøv igen med et nyt ID.");
                        memberfound = false;
                        break;
                    }
                }
            }
            if (memberId > tempList.size()) {
                System.out.println("Medlem findes ikke. Prøv igen");
            }
        }
        return tempList;
    }

    public static void getPaymentStatus(ArrayList<Member> tempList) {
        System.out.println("Disse medlemmer har ikke betalt deres kontingent endnu:");

        for (Member m : tempList) {
            if (!m.hasPaid) {
                System.out.println("ID: " + m.memberId + " Navn: " + m.memberName + ". Alder: " + m.memberAge + "år." + " Mangler at betale: " + getAmount(m) + "kr:");
            }
        }
    }

    public static double getAmount(Member m) {
        int under18 = 1000;
        int over18 = 1600;
        int senior = 1600;
        int passive = 500;
        double seniorDiscount = 0.75;
        double amount = 0;

        if (!m.isActiveMember) {
            return passive;
        }
        if (m.memberAge < 18) {
            amount = under18;
        } else if (m.memberAge >= 18 && m.memberAge <= 59) {
            amount = over18;
        } else if (m.memberAge >= 60) {
            amount = senior * seniorDiscount;
        }
        return amount;
    }

    // Forsøg på bedre payMembership metode.
    public static ArrayList<Member> payMembership1(ArrayList<Member> tempList) {
        Scanner keyboard = new Scanner(System.in);
        boolean memberfound = false;
        int memberId;

        // This loop will run as long as a member is not found.
        while (!memberfound) {
            System.out.println("Skriv ID på medlemmet.");

            if (!keyboard.hasNextInt()
            ) {
                System.out.println("Ugyldigt ID. Prøv igen.");
                keyboard.nextLine();
                continue;       // Ask for the member ID again
            }
            memberId = keyboard.nextInt();
            keyboard.nextLine();

            //This loop is looking for matching ID with the arraylist.
            for (Member member : tempList) {
                if (member.memberId == memberId) {           // If the ID's match with arraylist.
                    System.out.println("Medlem fundet. Er det, det rigtige medlem?");
                    System.out.println(member);              // Makes sure the program is user-friendly and asks if the user wants to continue.
                    System.out.println("Ja / Nej");

                    String answer = keyboard.nextLine();

                    if (answer.equalsIgnoreCase("ja")) {
                        if (member.hasPaid) {            // Makes sure a member can't overpay.
                            System.out.println("Medlemmet har allerede betalt kontingent");
                        } else {
                            double missingPayment = PaymentHandler.getAmount(member);        // Re-use the getAmount function from PaymentHandler. Makes it more clean and effective.
                            System.out.println(member.memberName + " på " + member.memberAge + " har nu betalt det manglende kontigent på: " + missingPayment + "kr.");
                            member.setHasPaid(true);            // Updates the member's payment status.
                            memberfound = true;                 // The member is found == the loop can end.
                        }
                    } else if (answer.equalsIgnoreCase("nej")) {
                        System.out.println("Prøv igen med et nyt ID.");     // Continue while loop

                    } else {
                        System.out.println("Ugyldigt svar. Prøv igen.");    // Continue while loop
                    }
                }
            }
        }
        return tempList;
    }


    public static void subscribtion() {
    }
}


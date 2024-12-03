import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.Scanner;

public class PaymentHandler {
    public static void main(String[] args) {
        // Temporary list
        ArrayList<Member> testlist = new ArrayList<>();
        Member a1 = new Member(1, "j1 under", Gender.MALE, 16, true, true, false, false);
        Member a2 = new Member(2, "j2", Gender.FEMALE, 20, false, true, false, false);
        Member a3 = new Member(3, "j3 senior", Gender.OTHER, 61, true, true, false, false);
        Member a4 = new Member(4, "j4", Gender.OTHER, 18, true, true, false, false);
        Member a5 = new Member(5, "j5 over", Gender.FEMALE, 18, true, true, true, false);
        Member a6 = new Member(6, "j6 auto", Gender.MALE, 18, true, true, true, true);

        testlist.add(a1);
        testlist.add(a2);
        testlist.add(a3);
        testlist.add(a4);
        testlist.add(a5);
        testlist.add(a6);

    }

    public static ArrayList<Member> payMembership(ArrayList<Member> tempList) {
        // This method is going to allow the user to select a member and help them pay their membership.
        Scanner keyboard = new Scanner(System.in);
        boolean memberfound = false;
        int memberId;

        // This loop will run as long as a member is not found.
        while (!memberfound) {
            System.out.println("Skriv ID på medlemmet.");

            if (!keyboard.hasNextInt()) {
                System.out.println("Ugyldigt ID. Prøv igen.");
                keyboard.nextLine();
                continue;       // Ask for the member ID again.
            }

            memberId = keyboard.nextInt();
            keyboard.nextLine();

            //This loop is looking for matching ID with the arraylist.
            for (Member member : tempList) {
                if (member.memberId == memberId) {           // If the ID's match with arraylist.
                    System.out.println("Medlem fundet. Er det, det rigtige medlem?");
                    System.out.println(member);              // Makes sure the program is user-friendly and asks if the user wants to continue.
                    System.out.println("Ja / Nej");

                    String answer = checkValidInput();  // This method will only allow yes/no answer.

                    if (answer.equalsIgnoreCase("ja")) {
                        if (member.hasPaid) {            // Makes sure a member can't pay twice.
                            System.out.println("Medlemmet har allerede betalt kontingent");
                        }
                        if (member.automaticPayment) {  // Makes sure a member can't pay, if subscription is active.
                            System.out.println("Medlemmet har en aktiv abonnementsaftale. Medlemmet skal ikke betale ekstra.");
                        } else {
                            double missingPayment = PaymentHandler.getAmount(member);        // Re-use the getAmount function from PaymentHandler. Makes it more clean and effective.
                            System.out.println(member.memberName + " på " + member.memberAge + " har nu betalt det manglende kontigent på: " + missingPayment + "kr.");
                            member.hasPaid = true;            // Updates the member's payment status.
                            memberfound = true;                 // The member is found == the loop can end.
                        }
                    } else if (answer.equalsIgnoreCase("nej")) {
                        System.out.println("Prøv igen med et nyt ID.");     // Continue while loop.
                    }
                }
            }
            if (memberId > tempList.size()) {         // The system assigns a member with id++.
                System.out.println("Medlemmet kunne ikke findes.");
                memberfound = false;    // The member is not found and the loop will continue.
            }
        }
        // Remember to return the list.
        return tempList;
    }

    public static void getPaymentStatus(ArrayList<Member> tempList) {
        System.out.println("Disse medlemmer har ikke betalt deres kontingent endnu:");
        // The purpose of this method is to print out a list of members who hasn't paid the subscription.
        for (Member m : tempList) {
            if (!m.hasPaid) {   // Members who hasn't paid.
                System.out.println("ID: " + m.memberId + " Navn: " + m.memberName + ". Alder: " + m.memberAge + "år." + " Mangler at betale: " + getAmount(m) + "kr:");
            }
        }
    }

    public static double getAmount(Member m) {
        // We need this method to get the right amount of money.
        // This is necessary because the method will secure the correct payment.
        int under18 = 1000;
        int over18 = 1600;
        int senior = 1600;
        int passive = 500;
        double seniorDiscount = 0.75;
        double amount = 0;

        if (!m.isActiveMember) {
            // This need to return. It will not interfere with the other integers.
            return passive;
        }
        if (m.memberAge < 18) {
            amount = under18;
        } else if (m.memberAge >= 18 && m.memberAge <= 59) {
            amount = over18;
        } else if (m.memberAge >= 60) {
            amount = senior * seniorDiscount;
        }
        // Return the correct amount of money.
        return amount;
    }

    public static void viewActiveSubscription(ArrayList<Member> tempList) {
        System.out.println("Disse medlemmer har en aktiv abonnementsaftale:");
        // This method will print out a list of members with an active subscription.
        for (Member m : tempList) {
            if (m.automaticPayment) {
                System.out.println("ID: " + m.memberId + " Navn: " + m.memberName + ". Alder: " + m.memberAge + "år.");
            }
        }
    }

    public static void viewNoSubscription(ArrayList<Member> tempList) {
        System.out.println("Disse medlemmer har en ikke aktiv abonnementsaftale:");
        // This method will print out a list with members without a subscription.
        for (Member m : tempList) {
            if (!m.automaticPayment) {
                System.out.println("ID: " + m.memberId + " Navn: " + m.memberName + ". Alder: " + m.memberAge + "år.");
            }
        }
    }

    public static void changeSubscription(ArrayList<Member> tempList) {
        // This method is going to allow the user to select a member and change their membership to active/inactive.
        Scanner keyboard = new Scanner(System.in);
        int memberId;

        // Makes it user-friendly and allows the user to see a list of members beforehand.
        viewActiveSubscription(tempList);
        System.out.println();
        viewNoSubscription(tempList);
        System.out.println();

        System.out.println("Hvilket medlem skal ændre sit abonnement?");
        System.out.println("Skriv ID på medlemmet");
        memberId = Main.tjekIntFromUser(keyboard); //This method will allow an integer.

        for (Member m : tempList) {
            // Loop will go through the list.
            // If statements: if the member-id match with the right member.
            if (m.memberId == memberId && !m.automaticPayment) {         // If the member already has an active subscription.
                System.out.println(m.memberName + " har ikke en aktiv abonnementsaftale");
                System.out.println("Vil du gerne oprette et abonnement på " + m.memberName + "? Ja/Nej");

                String answer = checkValidInput();      // This method will only allow yes/no answer.

                if (answer.equalsIgnoreCase("ja")) {
                    System.out.println(m.memberName + "'s abonnement er ændret til aktiv");
                    m.automaticPayment = true;      // Give the member an active subscription.
                    m.hasPaid = true;         // Makes sure the members pays with the subscription.
                    // Breaks out of the loop. Makes sure exit the method.
                    break;
                } else if (answer.equalsIgnoreCase("nej")) {
                    System.out.println("Abonnement vil ikke ændres");
                }
            }

            if (m.memberId == memberId && m.automaticPayment) {         // If the member doesn't have an active subscription.
                System.out.println(m.memberName + " har en aktiv abonnementsaftale");
                System.out.println("Vil du stoppe " + m.memberName + "'s abonnement? Ja/Nej");

                String answer = checkValidInput();      // This method will only allow yes/no answer.

                if (answer.equalsIgnoreCase("ja")) {
                    System.out.println(m.memberName + "'s har nu stoppet sit abonnement");
                    m.automaticPayment = false;      // The member doesn't want a subscription.
                } else if (answer.equalsIgnoreCase("nej")) {
                    System.out.println("Abonnement vil ikke ændres");
                }
            }
        }
    }

    public static String checkValidInput() {
        //This method will only allow a yes/no answer.
        Scanner keyboard = new Scanner(System.in);
        String answer;
        while (true) {
            answer = keyboard.nextLine();
            if (answer.equalsIgnoreCase("ja") || answer.equalsIgnoreCase("nej")) {
                break;
            } else {
                System.out.println("Indtast korrekt input. Ja/Nej");
            }
        }
        //Remember to return answer.
        return answer;
    }
}
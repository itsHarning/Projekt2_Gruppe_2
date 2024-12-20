import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class PaymentHandler {
    public static ArrayList<Member> payMembership(ArrayList<Member> memberList) {
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

            if (memberId == 0) {
                return null;
            }

            keyboard.nextLine();
            boolean existingMemberFound = false;
            //boolean memberFound will make sure the right member is found.
            //boolean existingMemberFound will make sure a real existing member is found.


            //This loop is looking for matching ID with the arraylist.
            for (Member member : memberList) {
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
                            System.out.println(member.memberName + " på " + member.memberAge + " år har nu betalt det manglende kontigent på: " + missingPayment + " DKK.");
                            member.hasPaid = true;              // Updates the member's payment status.
                            memberfound = true;                 // The member is found == the loop can end.
                        }
                        existingMemberFound = true;             // an existing member is found, but not the one the user is looking for.
                        break;
                    } else if (answer.equalsIgnoreCase("nej")) {
                        System.out.println("Prøv igen med et nyt ID.");     // Continue while loop.
                        existingMemberFound = true;
                        break;
                    }
                    else if (answer.equalsIgnoreCase("0")) {
                        return null;
                    }
                }
            }
            if (!existingMemberFound) {         //MemberId doesn't match with the arraylist and an existing member is not found.
                System.out.println("Medlemmet kunne ikke findes.");
                memberfound = false;    // The member is not found and the loop will continue.
            }
        }
        // Remember to return the list.
        return memberList;
    }

    public static void getPaymentStatus(ArrayList<Member> memberList) {
        System.out.println("Disse medlemmer har ikke betalt deres kontingent endnu:");
        // The purpose of this method is to print out a list of members who hasn't paid the subscription.
        for (Member m : memberList) {
            if (!m.hasPaid) {   // Members who hasn't paid.
                // TODO: fix formatting .00
                System.out.println("ID: " + m.memberId + " Navn: " + m.memberName + ". Alder: " + m.memberAge + "år." + " Mangler at betale: " + getAmount(m) + "0 DKK.");
            }
        }
    }

    public static double getAmount(Member m) {
        // We need this method to get the right amount of money.
        // This is necessary because the method will secure the correct payment.
        double under18 = 1000.00;
        double over18 = 1600.00;
        double senior = 1600.00;
        double passive = 500.00;
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

    public static void viewActiveSubscription(ArrayList<Member> memberList) {
        System.out.println("Disse medlemmer har en aktiv abonnementsaftale:");
        // This method will print out a list of members with an active subscription.
        for (Member m : memberList) {
            if (m.automaticPayment) {
                System.out.println("ID: " + m.memberId + " Navn: " + m.memberName + ". Alder: " + m.memberAge + "år.");
            }
        }
    }

    public static void viewNoSubscription(ArrayList<Member> memberList) {
        System.out.println("Disse medlemmer har en ikke aktiv abonnementsaftale:");
        // This method will print out a list with members without a subscription.
        for (Member m : memberList) {
            if (!m.automaticPayment) {
                System.out.println("ID: " + m.memberId + " Navn: " + m.memberName + ". Alder: " + m.memberAge + "år.");
            }
        }
    }

    public static void changeSubscription(ArrayList<Member> memberList) {
        // This method is going to allow the user to select a member and change their membership to active/inactive.
        Scanner keyboard = new Scanner(System.in);
        int memberId;

        // Makes it user-friendly and allows the user to see a list of members beforehand.
        viewActiveSubscription(memberList);
        System.out.println();
        viewNoSubscription(memberList);
        System.out.println();

        System.out.println("Hvilket medlem skal ændre sit abonnement?");
        System.out.println("Skriv ID på medlemmet.");
        memberId = Main.checkIntFromUser(keyboard); //This method will allow an integer.

        if (memberId == 0){
            return;
        }

        for (Member m : memberList) {
            // Loop will go through the list.
            // If statements: if the member-id match with the right member.
            if (m.memberId == memberId && !m.automaticPayment) {         // If the member already has an active subscription.
                System.out.println(m.memberName + " har ikke en aktiv abonnementsaftale.");
                System.out.println("Vil du gerne oprette et abonnement på " + m.memberName + "? Ja/Nej");

                String answer = checkValidInput();      // This method will only allow yes/no answer.

                if (answer.equalsIgnoreCase("ja")) {
                    System.out.println(m.memberName + "'s abonnement er ændret til aktiv.");
                    m.automaticPayment = true;      // Give the member an active subscription.
                    m.hasPaid = true;         // Makes sure the members pays with the subscription.
                    // Breaks out of the loop. Makes sure exit the method.
                    break;
                } else if (answer.equalsIgnoreCase("nej")) {
                    System.out.println("Abonnement vil ikke ændres.");
                }
                else  if (answer.equalsIgnoreCase("0")){
                    return;
                }
            }

            if (m.memberId == memberId && m.automaticPayment) {         // If the member doesn't have an active subscription.
                System.out.println(m.memberName + " har en aktiv abonnementsaftale.");
                System.out.println("Vil du stoppe " + m.memberName + "'s abonnement? Ja/Nej");

                String answer = checkValidInput();      // This method will only allow yes/no answer.

                if (answer.equalsIgnoreCase("ja")) {
                    System.out.println(m.memberName + "'s har nu stoppet sit abonnement.");
                    m.automaticPayment = false;      // The member doesn't want a subscription.
                } else if (answer.equalsIgnoreCase("nej")) {
                    System.out.println("Abonnement vil ikke ændres.");
                } else if (answer.equalsIgnoreCase("0")){
                    return;
                }
            }
        }
    }

    public static String checkValidInput() {
        //This method will only allow a yes/no answer (or 0).
        Scanner keyboard = new Scanner(System.in);
        String answer;
        while (true) {
            answer = keyboard.nextLine();
            if (answer.equalsIgnoreCase("ja") || answer.equalsIgnoreCase("nej")) {
                break;
            }
            if (answer.equalsIgnoreCase("0")) {
                break;
            } else {
                System.out.println("Indtast korrekt input. Ja/Nej");
            }
        }
        //Remember to return answer.
        return answer;
    }

    public static void subscriberPayment(ArrayList<Member> memberList) {
        // The purpose of this method is making all members pay with their subscription.
        // This is a yearly subscription, and it has a fixed start and end date. (Would be nice if
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        LocalDate currentDate = LocalDate.now();    // The exact date for the current date today.

        // This loop will only run, if it's the correct date.
        if (currentDate.isAfter(endDate)) {
            for (Member m : memberList) {
                if (m.automaticPayment) {       // if any members has a subscription.
                    m.hasPaid = true;           // Makes sure they'll pay.
                }
                else {
                    m.hasPaid = false;          // Makes sure members without a subscription isn't charged.
                }
            }
            System.out.println("Medlemmer med et aktivt abonnement har nu betalt deres kontingent.");
            viewActiveSubscription(memberList);   // Print list with active subscribers.
            viewNoSubscription(memberList);       // Print list with non-active subscribers.
            startDate = LocalDate.of(2025,1,1);     // Remember to make new start and end date.
            endDate = LocalDate.of(2025,12,31);     // Would be awful for members paying more than once
            System.out.println("Den nye abonnements periode er således: " + startDate + " " + endDate); // Show the user the new dates.

        // If this instance is true, it isn't the correct date .
        } else if (currentDate.isBefore(endDate)) {
            System.out.println("Medlemmer med abonnement betaler årligt. Medlemmer skal først betale igen " + endDate);     // Tell the user when it's time.
        }
    }
}
import javax.management.StandardEmitterMBean;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberHandler {
    static ArrayList<Member> membersList; // creates the main list of members
    static Scanner keyboard = new Scanner(System.in);

        /*
        // needs to be used when you first run the program to get the list of members
        public static ArrayList loadMembersFromTextFile() {
                ArrayList<Member> tempList = new ArrayList<>();
                try {
                        FileReader fil = new FileReader("src/main/resources/MemberList.txt");
                        BufferedReader ind = new BufferedReader(fil);
                        String line = ind.readLine(); // converts the read lines to string
                        while (line !=null) {
                                String[]bites = line.split(",");

                                String id = bites [0];
                                String name = bites[1];
                                Gender gender = Gender.valueOf(bites[2]);
                                String age = bites[3];

                                boolean active = Boolean.parseBoolean(bites[4]);
                                boolean competitive = Boolean.parseBoolean(bites[5]);
                                boolean paid = Boolean.parseBoolean(bites[6]);
                                boolean autoPay = Boolean.parseBoolean(bites[7]);

                                try {
                                        int parsedId = Integer.parseInt(id);
                                        int parsedAge = Integer.parseInt(age);

                                        tempList.add(new Member(parsedId,name,gender,parsedAge,active,competitive,paid,autoPay));

                                } catch (NumberFormatException e){
                                        System.out.println("Not a number");
                                }
                                line = ind.readLine();
                        }
                } catch (IOException e) {
                        System.out.println("Could not find file");;
                }
                return tempList;
        }

        // updates the MemberList.txt to comply with the current status of the membersList
        public static void updateTextFile(ArrayList<Member> tempList) {
                try {
                        FileWriter file = new FileWriter("src/main/resources/MemberList.txt", false);
                        PrintWriter out = new PrintWriter(file);
                        for (Member m: tempList){
                                int id = m.memberId;
                                String name = m.memberName;
                                Gender gender = m.memberGender;
                                int age = m.memberAge;
                                boolean active = m.isActiveMember;
                                boolean competing = m.isCompeting;
                                boolean paid = m.hasPaid;
                                boolean autoPay = m.automaticPayment;

                                out.println(id+","+name+","+gender+","+age+","+active+","+competing+","+paid+","+autoPay);
                        }
                        out.close(); // Closes so all data gets written to the Textfile
                } catch (IOException e) {
                        System.out.println("could not write to file");
                }
        }
         */

    // prints the given list in a nicely formatted way
    public static void printList(ArrayList<Member> tempList) {
        for (Member m:tempList){
            System.out.println(
                    "ID: "+m.memberId+"\t\t" +
                            "NAVN: "+m.memberName+" ".repeat(18-m.memberName.length())+
                            "KØN: "+m.memberGender+" ".repeat(7-m.memberGender.name().length())+"\t"+
                            "ALDER: "+m.memberAge);
            if (m.isActiveMember) {
                System.out.print("Medlemskabet er aktivt, ");
                if (m.isCompeting)
                    System.out.println("og de stiller op i stævner");
                else
                    System.out.println("og de er motionister");
            }
            else
                System.out.println("Ikke aktivt medlemskab");
            if (m.hasPaid)
                System.out.println("Medlemmet har betalt");
            else {
                System.out.format("Medlemmet har ikke betalt, og skylder %.2f DKK\n",PaymentHandler.getAmount(m));
            }
            System.out.println();
        }
    }

    public static Member getMemberFromId(ArrayList<Member> memberList) {
        // this loop will run as long as a member is not found.
        while (true) {
            System.out.println("Indtast IDet på medlemmet");
            int memberId = CompMemberHandler.checkIntFromUser();
            if (memberId == 0)
                break;

            if (memberId > memberList.getLast().memberId){
                System.out.println("Dette er ikke et gyldigt medlems nummer\nDer er kun "+Member.numOfMembers+" medlemmer i klubben");
                continue;
            }

            // loops through the member list to find the member with a matching ID given
            while (true) {
                for (Member member : memberList) {
                    if (member.memberId == memberId) {
                        while (true) {
                            // double checks that you've gotten the member you intended
                            System.out.println("Er dette det rigtige medlem?");
                            System.out.println(member.memberName + "?");
                            System.out.println("Ja / Nej");
                            String answer = keyboard.nextLine();
                            if (answer.equalsIgnoreCase("0") || answer.equalsIgnoreCase("q"))
                                break;
                            if (answer.equalsIgnoreCase("ja")) {
                                return member;

                                // handles if you wrote the incorrect ID, and need to write a new one
                            } else if (answer.equalsIgnoreCase("nej")) {
                                System.out.println("Prøv igen med et nyt ID.");
                                memberId = CompMemberHandler.checkIntFromUser();
                                if (memberId == 0)
                                    return member;
                                break;

                                // handles if what's written isn't a valid ID
                            } else {
                                System.out.println("Ugyldigt svar. Prøv igen (Ja / Nej)");
                            }
                        }
                    }
                }
            }
        }
        return new Member();
    }
}

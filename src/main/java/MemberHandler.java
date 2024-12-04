import javax.management.StandardEmitterMBean;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MemberHandler {
        static ArrayList<Member> membersList; // creates the main list of members

        public static void main(String[] args){
                membersList=loadMembersFromTextFile(); // loads the members on the text file onto the list
                printList(membersList);
                //membersList.add(CreateNewMember.createNewMember()); // creates a new member and adds it to the list
                //updateTextFile(membersList); // updates the text file so that it's up to date with the new member
                membersList = PaymentHandler.payMembership(membersList);
                printList(membersList);
                updateTextFile(membersList);

        }
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

        // prints the given list in a nicely formatted way
        public static void printList(ArrayList<Member> tempList) {
                for (Member m:tempList){
                        System.out.println("ID: "+m.memberId+"\t\tNAVN: "+m.memberName+"\t\tKØN: "+m.memberGender+"\t\tALDER: "+m.memberAge);
                        if (m.isActiveMember) {
                                System.out.print("Medlemskabet er aktivt, ");
                                if (m.isCompeting) System.out.println("og de stiller op i stævner");
                                else System.out.println("og de er motionister");
                        }
                        else System.out.println("Ikke aktivt medlemskab");
                        if (m.hasPaid) System.out.println("Medlemmet har betalt");
                        else {
                                System.out.format("Medlemmet har ikke betalt, og skylder %.2f DKK\n",PaymentHandler.getAmount(m));
                        }
                        System.out.println();
                }
        }



        // Textfile metode for Time class
        /*
        //Load from textfile
        public static ArrayList loadMembersFromTextFileTime() {
                ArrayList<Time> tempList = new ArrayList<>();
                try {
                        FileReader fil = new FileReader("src//MemberList.txt");
                        BufferedReader ind = new BufferedReader(fil);
                        DateTimeFormatter Datoformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");            // Her laver jeg en variabel som kan oversætte en String om til en Dato format


                        String line = ind.readLine(); // converts the read lines to string
                        while (line !=null) {
                                String[]bites = line.split(",");

                                String discipline = bites [0];
                                String time = bites[1];
                                String dateSet = bites[2];
                                String isOfficial = bites[3];
                                String meetName = bites[4];
                                boolean cisOfficial = Boolean.parseBoolean(bites[3]);

                                try {
                                        double ctime = Double.parseDouble(time);
                                        LocalDate cdateset = LocalDate.parse(dateSet, Datoformatter);

                                        tempList.add(new Time(discipline,ctime,cdateset,isOfficial,meetName,cisOfficial));

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

        //Update textfile
        public static void updateTextFileTime(ArrayList<Member> tempList) {
                try {
                        FileWriter file = new FileWriter("src//TimeList.txt", false);
                        PrintWriter out = new PrintWriter(file);
                        for (Time t: tempList){
                                String discipline = t.discipline;
                                double time = t.time;
                                LocalDate dateSet = t.dateSet;
                                boolean isOfficial = t.isOfficial;
                                String meetName = t.meetName;
                                out.println(discipline+","+time+","+dateSet+","+isOfficial+","+meetName);
                        }
                        out.close(); // Closes so all data gets written to the Textfile
                } catch (IOException e) {
                        System.out.println("could not write to file");
                }
        }

         */

}

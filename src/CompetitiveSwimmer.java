import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class CompetitiveSwimmer{
    static Scanner keyboard = new Scanner(System.in);
    ArrayList<Double> freestyle;
    ArrayList<Double> backstroke;
    ArrayList<Double> breaststroke;
    ArrayList<Double> butterfly;
    ArrayList<Double> medley;

    CompetitiveSwimmer(){
        freestyle=new ArrayList<>();
        backstroke=new ArrayList<>();
        breaststroke=new ArrayList<>();
        butterfly=new ArrayList<>();
        medley=new ArrayList<>();
    }

    public static TimeClass createNewTime(ArrayList<Member> memberList){
        String discipline = "";
        int distance = 50;
        double time = 0.00;
        LocalDate dateSet = LocalDate.now();
        boolean isOfficial = false;
        String meetName = "";


        // This loop will run as long as a member is not found.
        boolean memberFound = false;
        while (true) {
            System.out.println("Indtast IDet på det medlem der skal registrere tider");
            int memberId = checkIntFromUser(keyboard);
            if (memberId == 0) System.exit(0);

            if (memberId > Member.numOfMembers){
                System.out.println("Dette er ikke et gyldigt medlems nummer\nDer er kun "+Member.numOfMembers+" medlemmer i klubben");
                continue;
            }

            for (Member member: memberList){
                if (member.memberId == memberId){
                    System.out.println("Er dette det rigtige medlem?");
                    System.out.println(member.memberName+"?");
                    System.out.println("Ja / Nej");
                    String answer = keyboard.nextLine();
                    if (answer.equalsIgnoreCase("0") || answer.equalsIgnoreCase("q")) System.exit(0);
                    if (answer.equalsIgnoreCase("ja")){
                        while (true) {
                            System.out.println("I hvilken disciplin skal der registreres en ny tid?");
                            discipline = keyboard.nextLine();
                            if (discipline.equalsIgnoreCase("0") || discipline.equalsIgnoreCase("q")) System.exit(0);
                            switch (discipline) {
                                case ("freestyle"):
                                    System.out.println("Hvilken distance blev der svømmet freestyle?");
                                    System.out.println("Gyldige distancer er 50, 100, 200, 400, 800, 1500");
                                    while (true) {
                                        distance = checkIntFromUser(keyboard);
                                        if (distance == 0) System.exit(0);
                                        switch (distance) {
                                            case (50):
                                            case (100):
                                            case (200):
                                            case (400):
                                            case (800):
                                            case (1500):
                                                break;
                                            default:
                                                System.out.println("Distancen du har indtastet er ikke gyldig");
                                                System.out.println("Venligst prøv igen og indtast en gyldig distance (50, 100, 200, 400, 800, og 1500)");
                                                continue;
                                        }
                                        break;
                                    }
                                    break;
                                case ("backstroke"):
                                case ("breaststroke"):
                                case ("butterfly"):
                                case ("medley"):
                                    System.out.println("Hvilken distance blev der svømmet ?"+discipline);
                                    System.out.println("Gyldige distancer er 100 og 200");
                                    while (true) {
                                        distance = checkIntFromUser(keyboard);
                                        if (distance == 0) System.exit(0);
                                        switch (distance) {
                                            case (100):
                                            case (200):
                                                break;
                                            default:
                                                System.out.println("Distancen du har indtastet er ikke gyldig");
                                                System.out.println("Venligst prøv igen og indtast en gyldig distance (100 og 200)");
                                                continue;
                                        }
                                        break;
                                    }
                                    break;
                                case("open water"):
                                    distance = 10000;
                                    break;
                                default:
                                    System.out.println("Disciplinen du har indtastet er ikke gyldig");
                                    System.out.println("Venligst prøv igen, og indtast en gyldig disciplin (freestyle, backstroke, breaststroke, butterfly, medley, og open water)");
                                    continue;
                            }
                            break;
                        }
                        // memberFound=true;
                    } else if (answer.equalsIgnoreCase("nej")){
                        System.out.println("Prøv igen med et nyt ID.");
                    } else {
                        System.out.println("Ugyldigt svar. Prøv igen");
                    }
                }
            }
            break;
        }
        return new TimeClass(discipline, distance, time, dateSet, false);
    }

    public ArrayList<Double> updateFastestTime(ArrayList<Double> fastestTimes){
        fastestTimes.sort(null);
        System.out.println(fastestTimes);
        int listSize = fastestTimes.size();
        System.out.println(listSize);
        if (listSize > 5) fastestTimes.subList(5,listSize).clear();
        System.out.println(fastestTimes);
        return fastestTimes;
    }

    public void printMemberTimes(Member member){
        System.out.println(member.memberName+" hurtigeste tider");
        System.out.println(member.competitiveSwimmer.freestyle);
        System.out.println(member.competitiveSwimmer.backstroke);
        System.out.println(member.competitiveSwimmer.breaststroke);
        System.out.println(member.competitiveSwimmer.butterfly);
        System.out.println(member.competitiveSwimmer.medley);
    }

    public static int checkIntFromUser(Scanner keyboard) {
        int result = 0;
        boolean validInput = false;

        // Continue until you get a valid input
        while (!validInput) {
            try {
                result = Integer.parseInt(keyboard.nextLine()); // Read input as an int
                validInput = true; // If input is invalid, it ends the loop
            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt input! Indtast venligst en gyldig ID");
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ArrayList<Member> membersList = MemberHandler.loadMembersFromTextFile();
        // MemberHandler.printList(membersList);
        /*
        Member member = new Member(2,"test navn",20,true,true,true,true);
        member.competitiveSwimmer=new CompetitiveSwimmer();
        member.competitiveSwimmer.freestyle.add(5.33);
        member.competitiveSwimmer.freestyle.add(7.88);
        member.competitiveSwimmer.freestyle.add(6.66);
        member.competitiveSwimmer.freestyle.add(10.20);
        member.competitiveSwimmer.freestyle.add(4.44);
        member.competitiveSwimmer.freestyle.add(9.99);
        member.competitiveSwimmer.freestyle.add(8.45);
        member.competitiveSwimmer.freestyle.add(8.24);
        member.competitiveSwimmer.freestyle.add(7.38);
        member.competitiveSwimmer.freestyle.add(11.45);
        System.out.println(member.competitiveSwimmer.freestyle);
        member.competitiveSwimmer.updateFastestTime(member.competitiveSwimmer.freestyle);
        System.out.println("new"+member.competitiveSwimmer.freestyle);
        member.competitiveSwimmer.printMemberTimes(member); */
        createNewTime(membersList);
    }

}

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class CompetitiveMember extends ArrayList<TimeHolder>{
    static Scanner keyboard = new Scanner(System.in);
    Coach coach;
    @JsonProperty("TimeHolder")
    ArrayList<TimeHolder> personalTimes = new ArrayList<>();

    CompetitiveMember(Coach coach){
        this.coach = coach;
    }

    public static void createNewTime(ArrayList<Member> memberList){
        String stringDiscipline = "";
        Discipline discipline;
        int distance = 0;
        Duration swimTime = Duration.parse("pt0s"); // parses duration from string in correct format, such as "1h33m7.69s"
        LocalDate dateSet = LocalDate.now();
        boolean isOfficial = false;
        String meetName = "";

        // This loop will run as long as a member is not found.
        boolean infoGotten = false;
        while (!infoGotten) {
            Member member = getMemberFromId(memberList);

            // loop runs until a valid discipline and distance has been given
            while (true) {
                System.out.println("I hvilken disciplin skal der registreres en ny tid?");
                stringDiscipline = keyboard.nextLine();
                if (stringDiscipline.equalsIgnoreCase("0") || stringDiscipline.equalsIgnoreCase("q"))
                    System.exit(0);

                // handles getting valid distance from each discipline
                switch (stringDiscipline) {
                    case ("freestyle"):
                        discipline = Discipline.FREESTYLE;
                        System.out.println("Hvilken distance blev der svømmet freestyle?");
                        System.out.println("de mulige distancer er 50, 100, 200, 400, 800, 1500");
                        while (true) {
                            distance = checkIntFromUser();
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
                        discipline = Discipline.valueOf(stringDiscipline.toUpperCase());
                        System.out.println("Hvilken distance blev der svømmet " + stringDiscipline + "?");
                        System.out.println("De mulige distancer er 100 og 200");
                        while (true) {
                            distance = checkIntFromUser();
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
                    case ("medley"):
                        discipline = Discipline.MEDLEY;
                        System.out.println("Hvilken distance blev der svømmet medley?");
                        System.out.println("De mulige distancer er 200 og 400");
                        while (true) {
                            distance = checkIntFromUser();
                            if (distance == 0) System.exit(0);
                            switch (distance) {
                                case (200):
                                case (400):
                                    break;
                                default:
                                    System.out.println("Distancen du har indtastet er ikke gyldig");
                                    System.out.println("Venligst prøv igen og indtast en gyldig distance (200 og 400)");
                                    continue;
                            }
                            break;
                        }
                        break;
                    case ("open water"):
                        discipline = Discipline.OPEN_WATER;
                        distance = 10_000;
                        break;
                    default:
                        System.out.println("Disciplinen du har indtastet er ikke gyldig");
                        System.out.println("Venligst prøv igen, og indtast en gyldig disciplin (freestyle, backstroke, breaststroke, butterfly, medley, og open water)");
                        continue;
                }
                break;
            }

            // gets the swimmers time
            System.out.println("Hvad er svømmerens tid?");
            swimTime = parseDuration();

            // gets if the time was set at a meet or not
            System.out.println("Blev tiden sat til et stævne? (Ja / Nej)");
            while (true) {
                String meetAnswer = keyboard.nextLine();
                if (meetAnswer.equalsIgnoreCase("0") || meetAnswer.equalsIgnoreCase("q"))
                    System.exit(0);

                // gets the name of the meet, if the time was set at one
                switch (meetAnswer) {
                    case ("ja"):
                        isOfficial = true;
                        System.out.println("Hvilke stævne blev tiden sat til?");
                        meetName = keyboard.nextLine();
                        break;
                    case ("nej"):
                        break;
                    default:
                        System.out.println("Ugyldigt svar. Prøv igen (Ja / Nej)");
                        continue;
                }
                break;
            }

            // double checks if you've gotten all the information correct
            System.out.println("Er dette den korrekte information?");
            String formattedTime = durationFormatter(swimTime);
            if (isOfficial) {
                System.out.println("ID: " + member.memberId + "\tNavn: " + member.memberName + "\tDisciplin: " + stringDiscipline + "\tDistance: " + distance + "\tTid: " + formattedTime + "\tDato: " + dateSet + "\tSat til stævnet " + meetName);
            } else {
                System.out.println("ID: " + member.memberId + "\tNavn: " + member.memberName + "\tDisciplin: " + stringDiscipline + "\tDistance: " + distance + "\tTid: " + formattedTime + "\tDato: " + dateSet + "\tTiden blev sat til en træning");
            }
            System.out.println("Ja / Nej");
            while (true) {
                String infoAnswer = keyboard.nextLine();
                if (infoAnswer.equalsIgnoreCase("0") || infoAnswer.equalsIgnoreCase("q"))
                    System.exit(0);

                // either continues if correct, or restarts method if incorrect
                switch (infoAnswer) {
                    case ("ja"):
                        // TODO: would be cool if you could go "I want to give another time to the same member" but that would seemingly require a second return which isn't possible?
                        if (!isOfficial) {
                            member.competitiveSwimmer.personalTimes.add(new TimeHolder(discipline, distance, swimTime, dateSet, isOfficial));
                            return;
                            // return new TimeHolder(discipline, distance, swimTime, dateSet, isOfficial);
                        } else {
                            member.competitiveSwimmer.personalTimes.add(new TimeHolder(discipline, distance, swimTime, dateSet, isOfficial, meetName));
                            return;
                            // return new TimeHolder(discipline, distance, swimTime, dateSet, isOfficial, meetName);
                        }
                    case ("nej"):
                        break;
                    default:
                        System.out.println("Ugyldigt svar. Prøv igen (Ja / Nej)");
                        continue;
                }
                break;
            }
            break;
        }
        // return new TimeHolder(discipline, distance, swimTime, dateSet, isOfficial);
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
    }

    public static int checkIntFromUser() {
        int result = 0;
        boolean validInput = false;

        // Continue until you get a valid input
        while (!validInput) {
            try {
                result = Integer.parseInt(keyboard.nextLine()); // Read input as an int
                validInput = true; // If input is invalid, it ends the loop
            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt input! Indtast venligst et helt tal.");
            }
        }

        return result;
    }

    public static Member getMemberFromId(ArrayList<Member> memberList) {
        // This loop will run as long as a member is not found.
        while (true) {
            System.out.println("Indtast IDet på det medlem der skal registrere tider");
            int memberId = checkIntFromUser();
            if (memberId == 0) System.exit(0);

            if (memberId > Member.numOfMembers){
                System.out.println("Dette er ikke et gyldigt medlems nummer\nDer er kun "+Member.numOfMembers+" medlemmer i klubben");
                continue;
            }

            while (true) {
                for (Member member : memberList) {
                    if (member.memberId == memberId) {
                        while (true) {

                            // double checks that you've gotten the member you intended
                            System.out.println("Er dette det rigtige medlem?");
                            System.out.println(member.memberName + "?");
                            System.out.println("Ja / Nej");
                            String answer = keyboard.nextLine();
                            if (answer.equalsIgnoreCase("0") || answer.equalsIgnoreCase("q")) System.exit(0);
                            if (answer.equalsIgnoreCase("ja")) {
                                return member;
                                // handles if you wrote the incorrect ID, and need to write a new one
                            } else if (answer.equalsIgnoreCase("nej")) {
                                System.out.println("Prøv igen med et nyt ID.");
                                memberId = checkIntFromUser();
                                if (memberId == 0) System.exit(0);
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
    }

    // loops until a valid duration is given
    public static Duration parseDuration() {
        Duration duration = Duration.parse("pt0s");   // parses duration from a string based in the ISO-8601 duration format PnDTnHnMn.nS.
        while (true) {
            try {
                duration = Duration.parse("pt"+keyboard.nextLine());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ugyldig tid, prøv igen med korrekt formatering (eksempelvis 4m20.6s)");
            }
        }
        return duration;
    }

    // gets the duration, and returns a nicely formatted string that's easier to read than the original formatting
    public static String durationFormatter(Duration duration) {
        String minutes = String.format("%02d", duration.toMinutesPart());
        String seconds = String.format("%02d.%02d", duration.toSecondsPart(), duration.toMillisPart());
        if (duration.getSeconds() >= 3600) {
            String hours = String.format("%d", duration.toHours());
            return hours+":"+minutes+":"+seconds;
        }
        else if (duration.getSeconds() > 60) return minutes+":"+seconds;
        else return seconds;
    }

    public static void main(String[] args) {
        ArrayList<Member> membersList = MemberHandler.loadMembersFromTextFile();

        // MemberHandler.printList(membersList);
        // createNewTime(membersList);
        System.out.println(membersList);
        MemberHandler.printList(membersList);
    }

}


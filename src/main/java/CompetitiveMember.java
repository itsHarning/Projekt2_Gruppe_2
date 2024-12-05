import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class CompetitiveMember{
    static Scanner keyboard = new Scanner(System.in);
    public Coach coach;
    @JsonProperty("TimeHolder")
    ArrayList<TimeHolder> personalTimes = new ArrayList<>();

    CompetitiveMember(Coach coach){
        this.coach = coach;
    }

    CompetitiveMember(){}

    public String toString(){
        return "Træner: "+coach+"\tTider: "+personalTimes;
    }

    public static void createNewTime(ArrayList<Member> memberList){
        String stringDiscipline = "";
        Discipline discipline;
        int distance = 0;
        Duration swimTime = Duration.parse("pt0s"); // parses duration from string in correct format, such as "1h33m7.69s"
        LocalDate dateSet = LocalDate.now();
        boolean isOfficial = false;
        String meetName = "";
        Member member = new Member();
        boolean newMember = true;

        // This loop will run until method is finished
        while (true) {
            if (newMember) {
                member = getMemberFromId(memberList);
                if (member.memberId == 0) return;
            }

            while (true) {
                if (member.competitiveSwimmer == null) {
                    System.out.println(member.memberName + " er ikke oprettet som konkurrence svømmer");
                    System.out.println("Vil du oprette dem, og registrere deres tid?");
                    String answer = keyboard.nextLine();
                    if (answer.equalsIgnoreCase("0") || answer.equalsIgnoreCase("q")) return;
                    if (answer.equalsIgnoreCase("ja")) {
                        System.out.println(member.memberName + " er nu registreret som konkurrencesvømmer");
                        member.isCompeting = true;
                        CreateCompObject.createCompObject(memberList);
                        break;
                    } else if (answer.equalsIgnoreCase("nej")) {
                        System.out.println("Medlemmet skal være, eller tidligere have været konkurrence svømmer for at få registreret tider");
                        return;
                    } else System.out.println("Ikke et gyldigt svar, prøv igen! (Ja / Nej)");
                } else break;
            }

            // loop runs until a valid discipline and distance has been given
            while (true) {
                System.out.println("I hvilken disciplin skal der registreres en ny tid?");
                System.out.println("Freestyle, Backstroke, Breaststroke, Butterfly, Medley, eller Open Water");
                stringDiscipline = keyboard.nextLine();
                if (stringDiscipline.equalsIgnoreCase("0") || stringDiscipline.equalsIgnoreCase("q"))
                    return;

                // handles getting valid distance from each discipline
                switch (stringDiscipline) {
                    case ("freestyle"):
                        discipline = Discipline.FREESTYLE;
                        System.out.println("Hvilken distance blev der svømmet freestyle?");
                        System.out.println("de mulige distancer er 50, 100, 200, 400, 800, 1500");
                        while (true) {
                            distance = checkIntFromUser();
                            if (distance == 0) return;
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
                            if (distance == 0) return;
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
                            if (distance == 0) return;
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

            System.out.println("Hvilken dato blev tiden sat? (yyyy-mm-dd)");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // sets up a formatter for how LocalDateTime should parse strings
            String givenDate = keyboard.nextLine();
            if (givenDate.equalsIgnoreCase("0") || givenDate.equalsIgnoreCase("q")) return;
            else if (givenDate.equalsIgnoreCase("i dag")) dateSet = LocalDate.now();
            else dateSet = LocalDate.parse(givenDate, formatter);

            // gets if the time was set at a meet or not
            System.out.println("Blev tiden sat til et stævne? (Ja / Nej)");
            while (true) {
                String meetAnswer = keyboard.nextLine();
                if (meetAnswer.equalsIgnoreCase("0") || meetAnswer.equalsIgnoreCase("q")) return;

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
                if (infoAnswer.equalsIgnoreCase("0") || infoAnswer.equalsIgnoreCase("q")) return;

                // either continues if correct, or restarts method if incorrect
                switch (infoAnswer) {
                    case ("ja"):
                        if (!isOfficial) {
                            member.competitiveSwimmer.personalTimes.add(new TimeHolder(discipline, distance, swimTime, dateSet, isOfficial));
                            // return new TimeHolder(discipline, distance, swimTime, dateSet, isOfficial);
                        } else {
                            member.competitiveSwimmer.personalTimes.add(new TimeHolder(discipline, distance, swimTime, dateSet, isOfficial, meetName));
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
            member.competitiveSwimmer.personalTimes = autoUpdateFastestTime(member.competitiveSwimmer.personalTimes, discipline, distance);
            while (true) {
                System.out.println("Vil du gerne oprette endnu en tid? (Ja / Nej)");
                String answer = keyboard.nextLine();
                if (answer.equalsIgnoreCase("0") || answer.equalsIgnoreCase("q")) return;
                if (answer.equalsIgnoreCase("ja")) {
                    while (true) {
                        System.out.println("Samme medlem? (Ja / Nej)");
                        String secondAnswer = keyboard.nextLine();
                        if (secondAnswer.equalsIgnoreCase("0") || secondAnswer.equalsIgnoreCase("q")) return;
                        if (secondAnswer.equalsIgnoreCase("ja")) {
                            newMember = false;
                            break;
                        } else if (secondAnswer.equalsIgnoreCase("nej")) break;
                        else System.out.println("Ikke et gyldigt svar, prøv igen! (Ja / Nej)");
                    }
                    break;
                } else if (answer.equalsIgnoreCase("nej")) return;
                else System.out.println("Ikke et gyldigt svar, prøv igen! (Ja / Nej)");
            }
            FileHandler.writeListToJson(memberList);
        }
    }

    public static ArrayList<TimeHolder> autoUpdateFastestTime(ArrayList<TimeHolder> listOfTimes, Discipline discipline, int distance){
        ArrayList<TimeHolder> filteredTimeList = new ArrayList<>();
        ArrayList<TimeHolder> dumpList = new ArrayList<>();
        for (TimeHolder time: listOfTimes){
            if (time.discipline == discipline && time.distance == distance) filteredTimeList.add(time);
            else dumpList.add(time);
        }
        filteredTimeList.sort(Comparator.comparing(TimeHolder::getDuration));
        if (filteredTimeList.size() > 5) filteredTimeList.subList(5, filteredTimeList.size()).clear();
        filteredTimeList.addAll(dumpList);
        return filteredTimeList;
    }

    public static void getTopFiveSwimmers(ArrayList<Member> memberList){
        ArrayList<TimeHolder> filteredTimeList = new ArrayList<>();
        System.out.println("Vælg disciplin");
        String stringDiscipline = keyboard.nextLine();
        Discipline discipline = Discipline.valueOf(stringDiscipline.toUpperCase());
        System.out.println("Vælg distance");
        int distance = keyboard.nextInt();
        keyboard.nextLine();
        for (Member member: memberList){
            if (member.competitiveSwimmer != null){
                for (TimeHolder time: member.competitiveSwimmer.personalTimes){
                    if (time.discipline == discipline && time.distance == distance)filteredTimeList.add(time);
                }
                if (!member.competitiveSwimmer.personalTimes.isEmpty()){
                    filteredTimeList.sort(Comparator.comparing(TimeHolder::getDuration));
                    System.out.println(member.memberName + "s hurtigeste tid i " + distance + "m " + discipline);
                    System.out.println(filteredTimeList.getFirst());
                    filteredTimeList.clear();
                }
            }
        }
    }

    public static void printMemberTimes(ArrayList<Member> memberList){
        Member member = getMemberFromId(memberList);
        System.out.println(member.competitiveSwimmer);
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
            System.out.println("Indtast IDet på medlemmet");
            int memberId = checkIntFromUser();
            if (memberId == 0) break;

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
                            if (answer.equalsIgnoreCase("0") || answer.equalsIgnoreCase("q")) break;
                            if (answer.equalsIgnoreCase("ja")) {
                                return member;
                                // handles if you wrote the incorrect ID, and need to write a new one
                            } else if (answer.equalsIgnoreCase("nej")) {
                                System.out.println("Prøv igen med et nyt ID.");
                                memberId = checkIntFromUser();
                                if (memberId == 0) return member;
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
        ArrayList<Member> memberList = new ArrayList<>(FileHandler.getListFromJson());

        // MemberHandler.printList(membersList);
        createNewTime(memberList);
        System.out.println(memberList);
        MemberHandler.printList(memberList);
        FileHandler.writeListToJson(memberList);
    }

}


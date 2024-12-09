import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class CompetitiveMember{
    static Scanner keyboard = new Scanner(System.in);
    public Coach coach;
    @JsonProperty("TimeHolder")
    ArrayList<TimeHolder> personalTimes = new ArrayList<>();

    CompetitiveMember(Coach coach){
        this.coach = coach;
    }

    // used when loading everything from .json
    CompetitiveMember(){}

    public String toString(){
        return "Træner: "+coach+"\tTider: "+personalTimes;
    }

    public static void createNewTime(ArrayList<Member> memberList){
        Discipline discipline;
        int distance;
        Duration swimTime = Duration.parse("pt0s"); // parses duration from string in correct format, such as "1h33m7.69s"
        LocalDate dateSet = LocalDate.now();
        boolean isOfficial = false;
        String meetName = "";
        Member member = new Member();
        boolean newMember = true;

        System.out.println("Du har valgt at oprette en ny tid.");

        // This loop will run until method is finished
        while (true) {
            if (newMember) {
                member = MemberHandler.getMemberFromId(memberList);
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

            // gets the discipline and distance with the method getDisciplineAndDistance
            TimeHolder disciplineAndDistance = getDisciplineAndDistance();
            if (disciplineAndDistance == null) return;
            else {
                discipline = disciplineAndDistance.discipline;
                distance = disciplineAndDistance.distance;
            }

            // gets the swimmers time
            System.out.println("Hvad er svømmerens tid? (0h0m0.0s)");
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
                System.out.println("ID: " + member.memberId + "\tNavn: " + member.memberName + "\tDisciplin: " + discipline + "\tDistance: " + distance + "\tTid: " + formattedTime + "\tDato: " + dateSet + "\tSat til stævnet " + meetName);
            } else {
                System.out.println("ID: " + member.memberId + "\tNavn: " + member.memberName + "\tDisciplin: " + discipline + "\tDistance: " + distance + "\tTid: " + formattedTime + "\tDato: " + dateSet + "\tTiden blev sat til en træning");
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
                        } else {
                            member.competitiveSwimmer.personalTimes.add(new TimeHolder(discipline, distance, swimTime, dateSet, isOfficial, meetName));
                        }
                    case ("nej"):
                        break;
                    default:
                        System.out.println("Ugyldigt svar. Prøv igen (Ja / Nej)");
                        continue;
                }
                break;
            }

            // sorts the personalTimes list, and cuts it if it has more than five items
            member.competitiveSwimmer.personalTimes = autoUpdateFastestTime(member.competitiveSwimmer.personalTimes, discipline, distance);

            // checks if you want to create another time, and reruns the program if you do. Either with the same member, or a new one
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

    public static TimeHolder getDisciplineAndDistance(){
        Discipline discipline;
        TimeHolder returnObject;
        int distance;
        // loop runs until a valid discipline and distance has been given
        while (true) {
            System.out.println("I hvilken disciplin vil du bruge");
            System.out.println("Freestyle, Backstroke, Breaststroke, Butterfly, Medley, eller Open Water");
            String stringDiscipline = keyboard.nextLine().toUpperCase();
            if (stringDiscipline.equalsIgnoreCase("0") || stringDiscipline.equalsIgnoreCase("q"))
                return null;

            // handles getting valid distance from each discipline
            switch (stringDiscipline) {
                case ("FREESTYLE"):
                    discipline = Discipline.FREESTYLE;
                    System.out.println("Hvilken distance freestyle?");
                    System.out.println("de mulige distancer er 50, 100, 200, 400, 800, 1500");
                    while (true) {
                        distance = checkIntFromUser();
                        if (distance == 0) return null;
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
                case ("BACKSTROKE"):
                case ("BREASTSTROKE"):
                case ("BUTTERFLY"):
                    discipline = Discipline.valueOf(stringDiscipline);
                    System.out.println("Hvilken distance " + stringDiscipline + "?");
                    System.out.println("De mulige distancer er 100 og 200");
                    while (true) {
                        distance = checkIntFromUser();
                        if (distance == 0) return null;
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
                case ("MEDLEY"):
                    discipline = Discipline.MEDLEY;
                    System.out.println("Hvilken distance medley?");
                    System.out.println("De mulige distancer er 200 og 400");
                    while (true) {
                        distance = checkIntFromUser();
                        if (distance == 0) return null;
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
                case ("OPENWATER"):
                case ("OPEN WATER"):
                case ("OPEN_WATER"):
                    discipline = Discipline.OPEN_WATER;
                    distance = 10_000;
                    break;
                default:
                    System.out.println("Disciplinen du har indtastet er ikke gyldig");
                    System.out.println("Venligst prøv igen, og indtast en gyldig disciplin (freestyle, backstroke, breaststroke, butterfly, medley, og open water)");
                    continue;
            }
            returnObject = new TimeHolder(discipline,distance);
            return returnObject;
        }
    }

    public static ArrayList<TimeHolder> autoUpdateFastestTime(ArrayList<TimeHolder> timeList, Discipline discipline, int distance){
        ArrayList<TimeHolder> filteredTimeList = new ArrayList<>();     // a list for the recorded times that match the filter parameters
        ArrayList<TimeHolder> dumpList = new ArrayList<>();             // a list for the rest of the times

        // goes through the timeList and sorts each TimeHolder object into the correct ArrayList
        for (TimeHolder time: timeList){
            if (time.discipline == discipline && time.distance == distance) filteredTimeList.add(time);
            else dumpList.add(time);
        }

        // sorts the TimeHolder objects in filteredTimeList after the duration
        filteredTimeList.sort(Comparator.comparing(TimeHolder::getDuration));

        // trims the filteredTimeList to at most show the top five swimmers
        if (filteredTimeList.size() > 5) filteredTimeList.subList(5, filteredTimeList.size()).clear();
        filteredTimeList.addAll(dumpList);  // the times that didn't match the parameters are added back into the list
        return filteredTimeList;            // the list with all the times are returned
    }

    public static void getTopFiveSwimmers(ArrayList<Member> memberList){
        Discipline discipline;
        int distance;

        // gets the discipline and distance with the method getDisciplineAndDistance
        TimeHolder disciplineAndDistance = getDisciplineAndDistance();
        if (disciplineAndDistance == null) return;
        else {
            discipline = disciplineAndDistance.discipline;
            distance = disciplineAndDistance.distance;
        }

        // stream through the memberList, filters through based on the given parameters, and adds the members that match to filteredMemberList
        List<Member> filteredMemberList = memberList
            .stream()
            .filter(
                member -> member.competitiveSwimmer != null     // makes sure member has competitiveSwimmer initialised to avoid errors
                    && member.competitiveSwimmer.personalTimes
                    .stream()                                       // streams through the personalTimes list of each member
                    .anyMatch(
                        time -> time.discipline == discipline   // makes sure it has given discipline
                            && time.distance == distance))  // and distance
            .toList();

        // goes through all the filtered members, and makes sure their personal time list is sorted so the fastest relevant time appears first
        for (Member member: filteredMemberList) {
            autoUpdateFastestTime(member.competitiveSwimmer.personalTimes, discipline, distance);
        }

        // sorts filteredMemberList based off of the first item in each members personalTimes list
        filteredMemberList = filteredMemberList
            .stream()
            .sorted(
                Comparator.comparing(
                    member -> member.competitiveSwimmer.personalTimes
                        .getFirst()
                        .duration))
            .toList();

        // trims the filteredMemberList to at most show the top five swimmers
        if (filteredMemberList.size() > 5) filteredMemberList.subList(5, filteredMemberList.size()).clear();

        // TODO: make prettier print
        for (Member member: filteredMemberList){
            System.out.println("Navn: "+member.memberName+"\t"+member.competitiveSwimmer.personalTimes.getFirst());
        }
        System.out.println();
    }

    public static void printMemberTimes(ArrayList<Member> memberList){
        Member member = MemberHandler.getMemberFromId(memberList);
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

    // method to make sure a valid duration input is given as it needs to follow a specific format
    public static Duration parseDuration() {
        Duration duration = Duration.parse("pt0s");   // parses duration from a string based in the ISO-8601 duration format PnDTnHnMn.nS.
        while (true) {
            try {
                duration = Duration.parse("pt"+keyboard.nextLine());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ugyldig tid, prøv igen med korrekt formatering (0h0m0.0s)");
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

}
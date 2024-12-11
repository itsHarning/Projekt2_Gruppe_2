import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CompMemberHandler {
    static Scanner keyboard = new Scanner(System.in);

    public static void createNewTime(ArrayList<Member> memberList){
        Discipline discipline;
        int distance;
        Duration swimTime = Duration.parse("pt0s"); // parses duration from string in correct format, such as "1h33m7.69s"
        LocalDate dateSet;
        boolean isOfficial = false;
        String meetName = "";
        Member member = new Member();
        boolean newMember = true;

        System.out.println("Du har valgt at oprette en ny tid.");

        // This loop will run until method is finished
        while (true) {
            if (newMember) {
                member = MemberHandler.getMemberFromId(memberList);
                if (member.memberId == 0) {
                    return;
                }
            }

            while (true) {
                if (member.personalTimes.isEmpty()) {
                    System.out.println(member.memberName + " er ikke oprettet som konkurrence svømmer");
                    System.out.println("Vil du oprette dem, og registrere deres tid? (Ja / Nej)");
                    String answer = keyboard.nextLine();
                    if (answer.equalsIgnoreCase("0") || answer.equalsIgnoreCase("q"))
                        return;
                    if (answer.equalsIgnoreCase("ja")) {
                        System.out.println(member.memberName + " er nu registreret som konkurrencesvømmer");
                        member.isCompeting = true;
                        break;
                    } else if (answer.equalsIgnoreCase("nej")) {
                        System.out.println("Medlemmet skal være, eller tidligere have været konkurrence svømmer for at få registreret tider");
                        return;
                    } else
                        System.out.println("Ikke et gyldigt svar, prøv igen! (Ja / Nej)");
                } else break;
            }

            // gets the discipline and distance with the method getDisciplineAndDistance
            RecordedTime disciplineAndDistance = getDisciplineAndDistance();
            if (disciplineAndDistance == null)
                return;
            else {
                discipline = disciplineAndDistance.discipline;
                distance = disciplineAndDistance.distance;
            }

            // gets the swimmers time
            System.out.println("Hvad er svømmerens tid? (0h0m0.0s)");
            swimTime = parseDuration();

            dateSet = parseDate();
            if (dateSet == null)
                return;

            // gets if the time was set at a meet or not
            System.out.println("Blev tiden sat til et stævne? (Ja / Nej)");
            while (true) {
                String meetAnswer = keyboard.nextLine();
                if (meetAnswer.equalsIgnoreCase("0") || meetAnswer.equalsIgnoreCase("q"))
                    return;

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
            String formattedTime = durationToStringFormatter(swimTime);
            if (isOfficial) {
                System.out.println("ID: "+member.memberId+
                    "\tNavn: " + member.memberName+
                    "\tDisciplin: "+discipline+
                    "\tDistance: "+distance+
                    "\tTid: "+formattedTime+
                    "\tDato: "+dateSet+
                    "\tSat til stævnet \""+
                    meetName+"\"");
            } else {
                System.out.println("ID: "+
                    member.memberId+
                    "\tNavn: "+member.memberName+
                    "\tDisciplin: "+discipline+
                    "\tDistance: "+distance+
                    "\tTid: "+formattedTime
                    + "\tDato: "+dateSet+
                    "\tTiden blev sat til en træning");
            }
            System.out.println("Ja / Nej");
            while (true) {
                String infoAnswer = keyboard.nextLine();
                if (infoAnswer.equalsIgnoreCase("0") || infoAnswer.equalsIgnoreCase("q"))
                    return;

                // either continues if correct, or restarts method if incorrect
                switch (infoAnswer) {
                    case ("ja"):
                        if (!isOfficial) {
                            member.personalTimes.add(new RecordedTime(discipline, distance, swimTime, dateSet, isOfficial));
                        } else {
                            member.personalTimes.add(new RecordedTime(discipline, distance, swimTime, dateSet, isOfficial, meetName));
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
            member.personalTimes = autoUpdateFastestTime(member.personalTimes, discipline, distance);

            // checks if you want to create another time, and reruns the program if you do. Either with the same member, or a new one
            while (true) {
                System.out.println("Vil du gerne oprette endnu en tid? (Ja / Nej)");
                String answer = keyboard.nextLine();
                if (answer.equalsIgnoreCase("0") || answer.equalsIgnoreCase("q"))
                    return;
                if (answer.equalsIgnoreCase("ja")) {
                    while (true) {
                        System.out.println("Samme medlem? (Ja / Nej)");
                        String secondAnswer = keyboard.nextLine();
                        if (secondAnswer.equalsIgnoreCase("0") || secondAnswer.equalsIgnoreCase("q"))
                            return;
                        if (secondAnswer.equalsIgnoreCase("ja")) {
                            newMember = false;
                            break;
                        } else if (secondAnswer.equalsIgnoreCase("nej"))
                            break;
                        else
                            System.out.println("Ikke et gyldigt svar, prøv igen! (Ja / Nej)");
                    }
                    break;
                } else if (answer.equalsIgnoreCase("nej"))
                    return;
                else
                    System.out.println("Ikke et gyldigt svar, prøv igen! (Ja / Nej)");
            }
            FileHandler.writeListToJson(memberList);
        }
    }

    public static RecordedTime getDisciplineAndDistance(){
        Discipline discipline;
        RecordedTime returnObject;
        int distance;
        System.out.println("I hvilken disciplin vil du bruge");
        System.out.println("Freestyle, Backstroke, Breaststroke, Butterfly, Medley, eller Open Water");

        // loop runs until a valid discipline and distance has been given
        while (true) {
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
                    System.out.println("Hvilken distance " + discipline + "?");
                    System.out.println("De mulige distancer er 100 og 200");
                    while (true) {
                        distance = checkIntFromUser();
                        if (distance == 0)
                            return null;
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
                        if (distance == 0)
                            return null;
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
            returnObject = new RecordedTime(discipline,distance);
            return returnObject;
        }
    }

    public static ArrayList<RecordedTime> autoUpdateFastestTime(ArrayList<RecordedTime> timeList, Discipline discipline, int distance){
        ArrayList<RecordedTime> filteredTimeList = new ArrayList<>();     // a list for the recorded times that match the filter parameters
        ArrayList<RecordedTime> dumpList = new ArrayList<>();             // a list for the rest of the times

        // goes through the timeList and sorts each TimeHolder object into the correct ArrayList
        for (RecordedTime time: timeList){
            if (time.discipline == discipline && time.distance == distance)
                filteredTimeList.add(time);
            else dumpList.add(time);
        }

        // sorts the TimeHolder objects in filteredTimeList after the duration
        filteredTimeList.sort(Comparator.comparing(RecordedTime::getDuration));

        // trims the filteredTimeList to at most show the top five swimmers
        if (filteredTimeList.size() > 5)
            filteredTimeList.subList(5, filteredTimeList.size()).clear();
        filteredTimeList.addAll(dumpList);  // the times that didn't match the parameters are added back into the list
        return filteredTimeList;            // the list with all the times are returned
    }

    public static void getTopFiveSwimmers(ArrayList<Member> memberList){
        Discipline discipline;
        int distance;
        Gender gender;

        System.out.println("Hvilket køn (Mand / Kvinde/ Andet)");
        while (true) {
            String genderString = keyboard.nextLine().toUpperCase();
            if (Gender.fromString(genderString) == null){
                System.out.println("Ikke et gyldigt køn, prøv igen (Mand / Kvinde / Andet)");
            }
            else {
                gender = Gender.fromString(genderString);
                break;
            }
        }

        // gets the discipline and distance with the method getDisciplineAndDistance
        RecordedTime disciplineAndDistance = getDisciplineAndDistance();
        if (disciplineAndDistance == null)
            return;
        else {
            discipline = disciplineAndDistance.discipline;
            distance = disciplineAndDistance.distance;
        }

        // stream through the memberList, filters through based on the given parameters, and adds the members that match to filteredMemberList
        List<Member> filteredMemberList = memberList
            .stream()
            .filter(
                member -> member.memberGender == gender
                    && member.personalTimes
                    .stream()                                       // streams through the personalTimes list of each member
                    .anyMatch(
                        time -> time.discipline == discipline   // makes sure it has given discipline
                            && time.distance == distance))  // and distance
            .toList();

        // goes through all the filtered members, and makes sure their personal time list is sorted so the fastest relevant time appears first
        for (Member member: filteredMemberList) {
            autoUpdateFastestTime(member.personalTimes, discipline, distance);
        }

        // sorts filteredMemberList based off of the first item in each members personalTimes list
        filteredMemberList = filteredMemberList
            .stream()
            .sorted(
                Comparator.comparing(
                    member -> member.personalTimes
                        .getFirst()
                        .duration))
            .toList();

        // trims the filteredMemberList to at most show the top five swimmers
        if (filteredMemberList.size() > 5)
            filteredMemberList.subList(5, filteredMemberList.size()).clear();

        int num = 0;
                System.out.println("#\tTid\t\t\tID\tNavn"+" ".repeat(16)+"\tDato\t\t\tStævne");
        for (Member member: filteredMemberList){
            num++;
            System.out.println(
                num+"\t"+
                CompMemberHandler.durationToStringFormatter(member.personalTimes.getFirst().duration)+"\t"+
                member.memberId+"\t"+
                member.memberName+" ".repeat(20-member.memberName.length())+"\t"+
                member.personalTimes.getFirst().dateSet+"\t\t"+
                member.personalTimes.getFirst().meetName);
        }
        System.out.println();
    }

    public static void printMemberTimes(ArrayList<Member> memberList){
        Member member = MemberHandler.getMemberFromId(memberList);
        if (member == null)
            return;
        List<RecordedTime> sortedTimeList = member.personalTimes
            .stream()
                .sorted(Comparator.comparing(RecordedTime::getDuration))
            .sorted(
                Comparator.comparing(time -> time.distance))
            .sorted(Comparator.comparing(
                    time -> time.discipline))
                .toList();
        System.out.println("Disciplin\t\t\t\tTid\t\t\t\tDato\t\tStævne");
        for (RecordedTime time: sortedTimeList){
            String disciplineString = time.discipline.toString();
            String durationString = CompMemberHandler.durationToStringFormatter(time.duration);
            if (!time.isOfficial)
                time.meetName = "-";
            System.out.println(
                time.distance+"m"+" ".repeat(7-Integer.toString(time.distance).length())+
                time.discipline+" ".repeat(15-disciplineString.length())+"\t"+
                durationString+" ".repeat(12-durationString.length())+"\t"+
                time.dateSet+"\t"+
                time.meetName);
        }
        System.out.println();
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

    public static LocalDate parseDate(){
        System.out.println("Hvilken dato blev tiden sat? (yyyy-mm-dd)");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // sets up a formatter for how LocalDateTime should parse strings
        while (true) {
            try {
                String givenDate = keyboard.nextLine();
                if (givenDate.equalsIgnoreCase("0") || givenDate.equalsIgnoreCase("q"))
                    return null;
                else if (givenDate.equalsIgnoreCase("i dag"))
                    return LocalDate.now();
                else
                    return LocalDate.parse(givenDate, formatter);
            } catch (RuntimeException e) {
                System.out.println("Ikke en gyldig dato, prøv igen i formatted \"yyyy-MM-dd\"");
            }
        }
    }

    // gets the duration, and returns a nicely formatted string that's easier to read than the original formatting
    public static String durationToStringFormatter(Duration duration) {
        String minutes = String.format("%02d", duration.toMinutesPart());
        String seconds = String.format("%02d.%02d", duration.toSecondsPart(), duration.toMillisPart());
        if (duration.getSeconds() >= 3600) {
            String hours = String.format("%d", duration.toHours());
            return hours+":"+minutes+":"+seconds;
        }
        else if (duration.getSeconds() > 60)
            return minutes+":"+seconds;
        else
            return seconds+"s";
    }
}

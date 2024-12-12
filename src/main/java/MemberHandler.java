import java.util.ArrayList;
import java.util.Scanner;

public class MemberHandler {
    static ArrayList<Member> membersList; // creates the main list of members
    static Scanner keyboard = new Scanner(System.in);

    // In this method you can make a new member witch is then set in memberList, json and creates a compobject for them
    public static void createNewMember(ArrayList<Member> membersList) {

        // Here you can input name as a string
        System.out.println("Hvad er medlemmets navn?");
        String Name = keyboard.nextLine();
        if (Name.equals("0")) return;                   // if you press 0 you get send back to the menu

        // here you enter the gender
        System.out.println("Hvilen køn er medlemmet? (Mand/Kvinde/Andet)");

        String Valg = keyboard.nextLine();
        Gender gender;

        while (true) {
            if (Valg.equalsIgnoreCase("Mand")) {
                gender = Gender.MALE;
                break;
            }
            if (Valg.equalsIgnoreCase("Kvinde")) {
                gender = Gender.FEMALE;
                break;
            }
            if (Valg.equalsIgnoreCase("Andet")) {
                gender = Gender.OTHER;
                break;
            } else {
                System.out.println("Du skal vælge en af mulighederne");
                Valg = keyboard.nextLine();
            }
        }

        // here you enter you're age
        System.out.println("Hvor gammel er medlemmet?");
        int Age = Main.checkIntFromUser(keyboard);
        // tjeks if the age is over 100
        while(Age >= 100){
            System.out.println("Du skal melde dig på pensionist swømmeklubben hvis du er over 100 (Du kan gå tilbage ved at taste 0)");
            Age = Main.checkIntFromUser(keyboard);
        }
        if (Age == 0) return;

        // Ask if the member is aktive or not
        System.out.println("Er medlemskabet aktivt? (Ja / Nej)");
        String answerStatus = keyboard.nextLine();
        boolean isActiveMember;
        if (answerStatus.equalsIgnoreCase("0")) return;
        while (true) {
            if (answerStatus.equalsIgnoreCase("ja")) {
                isActiveMember = true;
                break;
            } else if (answerStatus.equalsIgnoreCase("nej")) {
                isActiveMember = false;
                break;
            } else {
                System.out.println("Det er ikke et gyldigt svar, prøv igen (aktivt/passivt)");
                answerStatus = keyboard.nextLine();
            }
        }

        // Ask if the member is competing
        boolean isCompeting;
        if(isActiveMember) {                                                        // tjeks if the member is aktive and if not goes to the next kode
            System.out.println("Vil medlemmet stille op i stævner? (Ja / Nej)");

            String answerCompeting = keyboard.nextLine();
            if (answerCompeting.equals("0")) return;

            while (true) {
                if (answerCompeting.equalsIgnoreCase("ja")) {
                    isCompeting = true;
                    break;
                } else if (answerCompeting.equalsIgnoreCase("nej")) {
                    isCompeting = false;
                    break;
                } else {
                    System.out.println("Det er ikke et gyldigt svar, prøv igen (Ja / Nej)");
                    answerCompeting = keyboard.nextLine();
                }
            }
        }
        else {
            isCompeting = false;
        }
        boolean hasPaid = false;
        boolean automaticPayment = false;
        // gives message of what you have ridden
        String memberString = "";
        if (isActiveMember){
            memberString = "medlemmet er aktivt";
        } else {
            memberString = "medlemmet er passivt";
        }

        String competingString = "";
        if (isCompeting){
            competingString = "medlemmet stiller op til stævner";
        } else {
            competingString = "medlemmet er motionist";
        }
        System.out.println("Du har oprettet medlemmet: ID: "+(Member.numOfMembers+1)+", Navn: "+Name+", Køn: "+gender+", Alder: "+Age+", "+memberString+", "+competingString);

        // here it adds the information to memberList, jsonfile and creates a new object
        membersList.add(new Member(Name, gender,  Age, isActiveMember, isCompeting, hasPaid, automaticPayment));
        // Team.assignTeams(membersList);
        FileHandler.writeListToJson(membersList);
    }

    public static void changeActivityStatus(ArrayList<Member> memberList) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Skriv ID på medlemmet");
        boolean memberFound = false;
        while (!memberFound){
            int memberID = Main.checkIntFromUser(keyboard);
            String status;
            for (Member m : memberList){
                if (m.memberId == memberID){
                    if(m.isActiveMember=true){
                        status="aktiv";
                    }
                    else {
                        status = "passiv";
                    }
                    System.out.println(m.memberName+"s status er "+status+ ". Hvad skal medlemmets status være fremadrettet? (aktiv/passiv)");
                    String answer = keyboard.nextLine();
                    if (answer.equals("aktiv")) {
                        m.isActiveMember = true;
                        System.out.println(m.memberName + "s" + " aktivitetsstatus er nu registreret som aktiv");
                        System.out.println();
                        return;
                    } else if (answer.equals("passiv")) {
                        m.isActiveMember = false;
                        System.out.println(m.memberName + "s" + " medlemsstatus er nu registreret som passiv");
                        return;
                    } else {
                        System.out.println("Dette er ikke et gyldigt svar, prøv igen");
                        System.out.println();
                    }
                    memberFound=true;

                }

                if (!memberFound){
                    System.out.println("ID ikke fundet, prøv igen.");


                }


            }
            FileHandler.writeListToJson(memberList);
                }
            }









    // prints the given list in a nicely formatted way
    public static void printList(ArrayList<Member> tempList) {
        for (Member m:tempList){
            System.out.println(
                    "ID: "+m.memberId+"\t\t" +
                    "NAVN: "+m.memberName+" ".repeat(20-m.memberName.length())+
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

            int finalMemberId = memberId;
            if (finalMemberId > memberList.getLast().memberId){
                System.out.println("Dette er ikke et gyldigt medlemsnummer\nDer er kun "+Member.numOfMembers+" medlemmer i klubben");
                continue;
            } else if (memberList.stream().noneMatch(member -> member.memberId == finalMemberId)){
                System.out.println("Kunne ikke finde et medlem med IDet '"+finalMemberId+"'! Prøv igen");
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
        return null;
    }

    static void changeCompetitiveStatus(ArrayList<Member> tempList) {
        System.out.println("Her kan du ændre competetiv status");
        while (true) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Hvilket ID har medlemet");
            int memberID = Main.checkIntFromUser(keyboard);
            boolean memberNotFound = true;
            for (Member m : tempList) {
                if (m.memberId == memberID && m.isActiveMember == true && m.isCompeting == false) {
                    memberNotFound = false;
                    System.out.println("Vil du melde dette medlem til stævner? " + m.getProfile());
                    System.out.println("ja/nej");
                    String answer = keyboard.nextLine();

                    if (answer.equalsIgnoreCase("Ja")) {
                        if (m.memberAge < 18) {
                            Team.exerciseTeam.remove(m);
                            Team.competitiveU18.add(m);
                            m.isCompeting = true;
                            FileHandler.writeListToJson(tempList);
                            return;
                        }

                        if (m.memberAge >= 18) {
                            Team.exerciseTeam.remove(m);
                            Team.competitiveO18.add(m);
                            m.isCompeting = true;
                            FileHandler.writeListToJson(tempList);
                            return;
                        }

                    }

                    if (answer.equalsIgnoreCase("nej")) {
                        break;
                    }
                }
                if(m.memberId == memberID && m.isActiveMember == true && m.isCompeting == true){
                    memberNotFound = false;
                    System.out.println("Vil du melde dette medlem ud af stævner? " + m.getProfile());
                    System.out.println("ja/nej");
                    String answer = keyboard.nextLine();

                    if (answer.equalsIgnoreCase("Ja")) {
                        if (m.memberAge < 18) {
                            Team.exerciseTeam.add(m);
                            Team.competitiveU18.remove(m);
                            m.isCompeting = false;
                            FileHandler.writeListToJson(tempList);
                            return;
                        }

                        if (m.memberAge >= 18) {
                            Team.exerciseTeam.add(m);
                            Team.competitiveO18.remove(m);
                            m.isCompeting = false;
                            FileHandler.writeListToJson(tempList);
                            return;
                        }

                    }
                    if (answer.equalsIgnoreCase("nej")) {
                        break;
                    }
                }
            }
            if (memberNotFound == true) {
                System.out.println("Kunne ikke finde medlemmet, prøv igen");
            }
        }

    }
}

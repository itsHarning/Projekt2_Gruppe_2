import java.util.ArrayList;

public class Team {
    static ArrayList<Member> competitiveO18 = new ArrayList<>();
    static ArrayList<Member> competitiveU18 = new ArrayList<>();
    static ArrayList<Member> exerciseTeam = new ArrayList<>();

    static void assignMembersToTeams(ArrayList<Member> SortedList) {
        for (Member m : SortedList) {
            if (m.isCompeting == false) {
                exerciseTeam.add(m);
            }
            if (m.isCompeting == true && m.memberAge < 18) {
                competitiveU18.add(m);
            }
            if (m.isCompeting == true && m.memberAge >= 18) {
                competitiveO18.add(m);
            }
        }
    }

    static void printMembersInTeams() {
        System.out.println("Motions hold:");
        for (Member m : Team.exerciseTeam) {
            System.out.println(m.getProfile());
        }
        System.out.println();
        System.out.println("Under 18:");
        for (Member m : Team.competitiveU18) {
            System.out.println(m.getProfile());
        }
        System.out.println();
        System.out.println("Over 18:");
        for (Member m : Team.competitiveO18) {
            System.out.println(m.getProfile());
        }
        System.out.println();
    }
}
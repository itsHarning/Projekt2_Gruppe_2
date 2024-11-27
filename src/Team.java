import java.util.ArrayList;

public class Team {
    static ArrayList<Member> competitiveO18 = new ArrayList<>();
    static ArrayList<Member> competitiveU18 = new ArrayList<>();
    static ArrayList<Member> exerciseteam = new ArrayList<>();

    static void assignTeams(ArrayList<Member> SortedList) {

        for (Member m : SortedList) {
            if (m.isCompeting == false) {
                exerciseteam.add(m);
            }
            if (m.isCompeting == true && m.memberAge < 18) {
                competitiveU18.add(m);
            }
            if (m.isCompeting == true && m.memberAge >= 18) {
                competitiveO18.add(m);
            }
        }
    }
}
import java.util.ArrayList;

public class CreateCompobject {

    public static void main(String[] args) {
        createCompobject();

    }

    static public void createCompobject() {
        ArrayList<Member> membersList = MemberHandler.loadMembersFromTextFile();
        ArrayList<Coach> coachlist = CoachHandler.loadMembersFromTextFile();
        Team team = new Team();
        team.assignTeams(membersList);

        for (Coach c: coachlist) {
            if(c.team == Team.competitiveO18){
                for (Member m: Team.competitiveO18)
                {
                    CompetitiveMember compO18 = new CompetitiveMember(c);
                    m.competitiveSwimmer = compO18;
                }
            }

            if(c.team == Team.competitiveU18) {
                for (Member m: Team.competitiveU18) {
                    CompetitiveMember compU18 = new CompetitiveMember(c);
                    m.competitiveSwimmer = compU18;

                }
            }
        }
    }
}

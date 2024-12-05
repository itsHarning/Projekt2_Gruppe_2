import java.util.ArrayList;

public class CreateCompObject {
    static public void createCompObject(ArrayList<Member> memberList) {
        ArrayList<Coach> coachlist = CoachHandler.loadMembersFromTextFile();
        Team.assignTeams(memberList);

        for (Coach c: coachlist) {
            if(c.team == Team.competitiveO18){
                for (Member m: Team.competitiveO18)
                {
                    if (m.competitiveSwimmer == null) {
                        CompetitiveMember compO18 = new CompetitiveMember(c);
                        m.competitiveSwimmer = compO18;
                    }
                }
            }

            if(c.team == Team.competitiveU18) {
                for (Member m: Team.competitiveU18) {
                    if (m.competitiveSwimmer == null) {
                        CompetitiveMember compU18 = new CompetitiveMember(c);
                        m.competitiveSwimmer = compU18;
                    }
                }
            }
        }
    }
}

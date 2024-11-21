import java.util.ArrayList;

public class MemberHandler {
        public static void main(String[] args) {
                ArrayList<Member> membersList = new ArrayList<>();
                membersList.add(CreateNewMember.createNewMember());
                System.out.println(membersList);
        }
}

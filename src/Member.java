public class Member {
    int memberId;
    String memberName;
    int memberAge;
    boolean isActiveMember;
    boolean isCompeting;

    Member(int Id, String name, int age, boolean active, boolean competing){
        memberId = Id;
        memberName = name;
        memberAge = age;
        isActiveMember = active;
        isCompeting = competing;
    }

    public String toString() {
        return memberName;
    }
}

